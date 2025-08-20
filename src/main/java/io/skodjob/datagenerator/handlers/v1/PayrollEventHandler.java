/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.v1;

import io.skodjob.datagenerator.handlers.AbstractHandler;
import io.skodjob.datagenerator.helpers.Utils;
import io.skodjob.datagenerator.models.v1.payroll.Deduction;
import io.skodjob.datagenerator.models.v1.payroll.PayrollEvent;
import io.skodjob.datagenerator.models.v1.payroll.PayrollStatus;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates stateful {@link PayrollEvent} records:
 */
public final class PayrollEventHandler extends AbstractHandler {

    /**
     * Template name announced to the CLI / REST interface.
     */
    public static final String TEMPLATE_NAME = "payroll_event";

    private static final Faker FAKER = new Faker();
    private static final Map<String, PayrollEvent> OPEN_PAYROLL = new ConcurrentHashMap<>();

    private static final int PROB_TO_PAID = 85;
    private static final int PROB_TO_ADJUST = 15;

    private PayrollEventHandler() {
    }

    /**
     * Returns a newly generated {@link PayrollEvent}.
     * May create a fresh CALCULATED or a follow-up status for an existing one.
     *
     * @return new or updated event
     */
    public static PayrollEvent generateData() {
        if (OPEN_PAYROLL.isEmpty()) {
            return createCalculated();
        }

        // This makes sure that OPEN_FLIGHTS won't be too big
        int pNew = Math.max(10, 100 * OPEN_EVENTS_MAX / (OPEN_PAYROLL.size() + 1));

        return ThreadLocalRandom.current()
            .nextInt(100) < pNew
            ? createCalculated()
            : updateExisting();
    }

    private static PayrollEvent createCalculated() {
        Instant now = Instant.now();
        String payrollId = UUID.randomUUID()
            .toString();
        LocalDate start = LocalDate.now(ZoneOffset.UTC)
            .withDayOfMonth(1);
        LocalDate end = start.plusMonths(1)
            .minusDays(1);

        BigDecimal gross = Utils.money(FAKER.number()
            .numberBetween(2000, 8000));
        List<Deduction> deds = randomDeductions(gross);
        BigDecimal net = gross.subtract(total(deds));

        PayrollEvent e = PayrollEvent.newBuilder()
            .setSchemaVersion(SCHEMA_VERSION_V1)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setSource(GENERATOR_SOURCE)
            .setIngestedTs(now)

            .setEmployeeId("emp-" + FAKER.number()
                .digits(6))
            .setPayrollId(UUID.fromString(payrollId))

            .setPeriodStart(LocalDate.ofEpochDay((int) start.toEpochDay()))
            .setPeriodEnd(LocalDate.ofEpochDay((int) end.toEpochDay()))

            .setCurrency(FAKER.money()
                .currencyCode())
            .setSalaryGross(buf(gross))
            .setDeductions(deds)
            .setNetPay(buf(net))

            .setStatus(PayrollStatus.CALCULATED)
            .build();

        OPEN_PAYROLL.put(payrollId, e);
        return e;
    }

    private static PayrollEvent updateExisting() {
        String id = pickOne(OPEN_PAYROLL.keySet());
        PayrollEvent prev = OPEN_PAYROLL.get(id);
        PayrollStatus next = nextStatus(prev.getStatus());

        Instant now = Instant.now();
        PayrollEvent.Builder b = PayrollEvent.newBuilder(prev)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setIngestedTs(now)
            .setStatus(next);

        if (next == PayrollStatus.ADJUSTED) {
            // add an extra deduction (e.g. late tax)
            List<Deduction> deds = new ArrayList<>(prev.getDeductions());
            BigDecimal extra = Utils.money(FAKER.number()
                .numberBetween(10, 200));
            deds.add(buildDeduction("ADJUST", extra));
            BigDecimal newNet = toDec(prev.getSalaryGross()).subtract(total(deds));
            b.setDeductions(deds)
                .setNetPay(buf(newNet));
        }

        PayrollEvent updated = b.build();

        if (next == PayrollStatus.ADJUSTED) {
            OPEN_PAYROLL.remove(id);
        } else {
            OPEN_PAYROLL.put(id, updated);
        }
        return updated;
    }

    private static PayrollStatus nextStatus(PayrollStatus s) {
        return switch (s) {
            case CALCULATED -> Utils.rand(100) < PROB_TO_PAID ? PayrollStatus.PAID : PayrollStatus.CALCULATED;
            case PAID -> Utils.rand(100) < PROB_TO_ADJUST ? PayrollStatus.ADJUSTED : PayrollStatus.PAID;
            default -> s;
        };
    }

    private static List<Deduction> randomDeductions(BigDecimal gross) {
        List<Deduction> list = new ArrayList<>();
        list.add(buildDeduction("TAX", gross.multiply(BigDecimal.valueOf(0.18))));
        list.add(buildDeduction("PENSION", gross.multiply(BigDecimal.valueOf(0.05))));
        if (Utils.randBool()) {
            list.add(buildDeduction("INSURANCE", Utils.money(FAKER.number()
                .numberBetween(20, 80))));
        }
        return list;
    }

    private static Deduction buildDeduction(String type, BigDecimal amt) {
        return Deduction.newBuilder()
            .setType(type)
            .setAmount(buf(amt))
            .build();
    }

    private static BigDecimal total(List<Deduction> deds) {
        return deds.stream()
            .map(d -> toDec(d.getAmount()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static ByteBuffer buf(BigDecimal v) {
        return ByteBuffer.wrap(v.unscaledValue()
            .toByteArray());
    }

    private static BigDecimal toDec(ByteBuffer b) {
        return new BigDecimal(new java.math.BigInteger(b.array()), 2);
    }

    private static <T> T pickOne(Collection<T> c) {
        return c.stream()
            .skip(Utils.rand(c.size()))
            .findFirst()
            .orElseThrow();
    }
}

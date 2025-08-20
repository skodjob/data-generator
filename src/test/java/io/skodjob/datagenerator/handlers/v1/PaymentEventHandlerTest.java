/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0
 */
package io.skodjob.datagenerator.handlers.v1;

import io.skodjob.datagenerator.models.v1.payment.PaymentEvent;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies that the fraud-burst logic in PaymentEventHandler
 * actually emits each rule at least once over N iterations.
 */
class PaymentEventHandlerTest {

    private static final int ITERATIONS = 80_000;

    @Test
    void everyFraudRuleAppears() {
        Map<String, Integer> ruleHits = new HashMap<>();
        int fraudEvents = 0;

        for (int i = 0; i < ITERATIONS; i++) {
            PaymentEvent ev = PaymentEventHandler.generateData();

            if (ev.getRiskScore() != null) {
                fraudEvents++;

                if (ev.getRulesTriggered() != null) {
                    ev.getRulesTriggered()
                        .forEach(r ->
                            ruleHits.merge(r.toString(), 1, Integer::sum));
                }
            }
        }

        assertTrue(fraudEvents > 0, "No fraud events at all");
        assertTrue(ruleHits.getOrDefault("VELOCITY", 0) > 0, "VELOCITY missing");
        assertTrue(ruleHits.getOrDefault("HIGH_AMOUNT", 0) > 0, "HIGH_AMOUNT missing");
        assertTrue(ruleHits.getOrDefault("IMPOSSIBLE_TRAVEL", 0) > 0, "IMPOSSIBLE_TRAVEL missing");
    }

    @Test
    void fraudClustersAreConsistent() {
        /* state per payer for impossible-travel check */
        Map<String, String> lastCountry = new HashMap<>();
        Map<String, Instant> lastTs = new HashMap<>();

        /* velocity aggregation */
        Map<String, Set<String>> velPayees = new HashMap<>();

        /* high-amount aggregation */
        Map<String, Integer> highHits = new HashMap<>();

        for (int i = 0; i < ITERATIONS; i++) {
            PaymentEvent ev = PaymentEventHandler.generateData();

            String payerId = ev.getParties()
                .get(0)
                .getPartyId()
                .toString();

            // Save potential template
            if (ev.getRulesTriggered() == null || ev.getRulesTriggered().isEmpty()) {
                lastCountry.put(payerId, ev.getIpCountry().toString());
                lastTs.put(payerId, ev.getTransactionTs());

            }

            // Check that VELOCITY events
            if (hasRule(ev, "VELOCITY")) {
                String payee = ev.getParties()
                    .get(1)
                    .getPartyId()
                    .toString();
                velPayees.computeIfAbsent(payerId, k -> new HashSet<>())
                    .add(payee);
            }

            /* ---- HIGH_AMOUNT checks ------------------------------- */
            if (hasRule(ev, "HIGH_AMOUNT")) {
                BigDecimal amt = bytesToBig(ev.getAmount());
                assertTrue(amt.compareTo(BigDecimal.valueOf(10_000)) > 0,
                    "HIGH_AMOUNT flagged but amount ≤ 10k");
                highHits.merge(payerId, 1, Integer::sum);
            }

            /* ---- IMPOSSIBLE_TRAVEL checks ------------------------- */
            if (hasRule(ev, "IMPOSSIBLE_TRAVEL")) {
                String prevCountry = lastCountry.get(ev.getParties().get(0).getPartyId().toString());
                if (prevCountry != null) {
                    String curCountry = ev.getIpCountry()
                        .toString();

                    Instant prevTs = lastTs.get(ev.getParties().get(0).getPartyId().toString());

                    assertNotEquals(prevCountry, curCountry,
                        "IMPOSSIBLE_TRAVEL but ip_country not changed");
                    assertTrue(Duration.between(
                                prevTs,
                                ev.getTransactionTs()
                            )
                            .toHours() < 1,
                        "IMPOSSIBLE_TRAVEL but more than 1h apart");
                }
            }
        }

        /* ---------- final aggregation assertions -------------------- */
        boolean velocityOk = velPayees.values()
            .stream()
            .allMatch(set -> set.size() >= 5);
        assertTrue(velocityOk, "VELOCITY clusters did not hit ≥5 distinct payees");

        boolean highAmountOk = highHits.values()
            .stream()
            .allMatch(cnt -> cnt >= 3);
        assertTrue(highAmountOk, "No payer emitted ≥3 HIGH_AMOUNT events");
    }

    /* helper */
    private static boolean hasRule(PaymentEvent ev, String rule) {
        return ev.getRulesTriggered() != null &&
            ev.getRulesTriggered()
                .toString()
                .contains(rule);
    }

    private static BigDecimal bytesToBig(ByteBuffer buf) {
        return new BigDecimal(new BigInteger(buf.array()), 2);
    }
}
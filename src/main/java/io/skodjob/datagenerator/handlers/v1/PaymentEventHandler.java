/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.v1;

import io.skodjob.datagenerator.handlers.AbstractHandler;
import io.skodjob.datagenerator.helpers.IpInfo;
import io.skodjob.datagenerator.helpers.Utils;
import io.skodjob.datagenerator.models.v1.payment.PaymentEvent;
import io.skodjob.datagenerator.models.v1.payment.Party;
import io.skodjob.datagenerator.models.v1.payment.PaymentMethod;
import io.skodjob.datagenerator.models.v1.payment.PaymentStatus;
import io.skodjob.datagenerator.models.v1.payment.PartyRole;
import io.skodjob.datagenerator.models.v1.payment.PartyType;

import net.datafaker.Faker;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates {@link PaymentEvent} records following the v1 Avro schema.
 * The generator is stateful: it may emit follow-up events (e.g. SETTLED)
 * for previously created transactionIds.
 */
public final class PaymentEventHandler extends AbstractHandler {

    /**
     * Template name announced to the CLI / REST interface.
     */
    public static final String TEMPLATE_NAME = "payment_event";

    private static final Faker FAKER = new Faker();
    private static final Map<String, PaymentEvent> OPEN_PAYMENTS = new ConcurrentHashMap<>();

    /**
     * Probabilities (0-100) for generator behaviour.
     */
    private static final int PROB_TO_SETTLED = 80;
    private static final int PROB_TO_FAILED = 15;
    // remaining 5 % → REVERSED (after SETTLED)

    private static final List<String> BANKS = Arrays.asList(
        "JPMorgan Chase", "Bank of America", "Wells Fargo", "Citigroup", "Goldman Sachs",
        "Morgan Stanley", "HSBC", "BNP Paribas", "UBS", "Credit Suisse",
        "Barclays", "Deutsche Bank", "Standard Chartered", "Santander", "Societe Generale",
        "ING Group", "Mitsubishi UFJ Financial Group", "Mizuho Financial Group", "Sumitomo Mitsui Financial Group",
        "Bank of China", "Industrial and Commercial Bank of China", "China Construction Bank",
        "Agricultural Bank of China", "Banco Bradesco", "Itau Unibanco",
        "Royal Bank of Canada", "Toronto-Dominion Bank", "National Australia Bank", "Westpac", "ANZ"
    );

    private static final List<String> METHODS = Arrays.asList(
        "SEPA", "ACH", "SWIFT");

    private static final List<String> E_WALLET = Arrays.asList(
        "PayPal", "ApplePay", "GooglePay", "AliPay", "AmazonPay", "Venmo");

    private static final List<String> CRYPTO_EXCHANGE = Arrays.asList(
        "Binance", "Coinbase", "Kraken", "Anycoin", "Bitstamp", "Coinmate", "Revolut");

    private enum Mode {
        NORMAL,
        BURST
    }

    private static Mode mode = Mode.NORMAL;
    private static int remainingInBurst = 0;

    /**
     * Chance to start a burst on any NORMAL tick (0-100)
     */
    private static final int BURST_PROB = 1;
    /**
     * How many events a burst contains
     */
    private static final int BURST_MIN = 30;
    private static final int BURST_MAX = 70;
    /**
     * Inside a burst, this share become obvious frauds (0-100)
     */
    private static final int BURST_FRAUD_P = 1;

    private enum FraudType {
        VELOCITY,
        HIGH_AMOUNT,
        IMPOSSIBLE_TRAVEL
    }

    /**
     * Counter for fraud events generated during burts
     */
    private static FraudType fraudType = FraudType.VELOCITY;
    private static int velocityLeft = 0;
    private static int highAmtLeft = 0;
    private static int travelLeft = 0;

    private static PaymentEvent fraudTemplate = null;

    private PaymentEventHandler() {
    }

    /**
     * Returns a newly generated {@link PaymentEvent}.
     * May create a fresh PENDING or a follow-up status for an existing one.
     *
     * @return new or updated event
     */
    public static PaymentEvent generateData() {
        if (mode == Mode.BURST && remainingInBurst == 0) {
            mode = Mode.NORMAL;
            velocityLeft = highAmtLeft = travelLeft = 0;
        }

        // Check if BURST shouldn't happen
        if (mode == Mode.NORMAL && Utils.rand(2000) < BURST_PROB) {
            mode = Mode.BURST;
            remainingInBurst = Utils.rand(BURST_MIN, BURST_MAX);

            // Init template for the frauds
            fraudTemplate = createNewPayment();
            // Pick which fraud should be created
            fraudType = pickFraud();
            // Seed counters for fraud transactions
            velocityLeft = Utils.rand(5, 8);
            highAmtLeft = Utils.rand(3, 5);
            travelLeft = Utils.rand(3, 4);

            return fraudTemplate;
        }

        // Inject burst that contains fraud transactions
        if (mode == Mode.BURST) {
            remainingInBurst--;
            return createFraudPayment();
        }

        if (OPEN_PAYMENTS.isEmpty()) {
            return createNewPayment();
        }

        // This makes sure that OPEN_PAYMENTS won't be too big
        int pNew = Math.max(10, 100 * OPEN_EVENTS_MAX / (OPEN_PAYMENTS.size() + 1));

        return ThreadLocalRandom.current()
            .nextInt(100) < pNew
            ? createNewPayment()
            : createUpdateForExisting();
    }

    /**
     * Create new payment with all random data
     *
     * @return payment event
     */
    private static PaymentEvent createNewPayment() {
        Instant now = Instant.now();
        String transactionId = UUID.randomUUID()
            .toString();

        // choose method & processor
        PaymentMethod method = randomPaymentMethod();
        String processor = chooseProcessor(method);

        // parties
        List<Party> parties = randomParties();

        // optional quick lookup
        String merchantId = (String) parties.stream()
            .filter(p -> p.getPartyType() == PartyType.MERCHANT)
            .findFirst()
            .map(Party::getPartyId)
            .orElse(null);

        IpInfo ipInfo = IpInfo.randomIpInfo();

        PaymentEvent event = PaymentEvent.newBuilder()
            .setSchemaVersion(SCHEMA_VERSION_V1)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setSource(GENERATOR_SOURCE)
            .setIngestedTs(now)

            .setTransactionId(UUID.fromString(transactionId))
            .setStatus(PaymentStatus.PENDING)
            .setTransactionTs(now)

            .setAmount(Utils.decimalToBuffer(Utils.randomAmount()))
            .setCurrency(FAKER.money()
                .currencyCode())

            .setMethod(method)
            .setProcessor(processor)

            .setParties(parties)

            .setMerchantId(merchantId)
            .setCardLast4(method == PaymentMethod.CARD ? FAKER.number()
                .digits(4) : null)
            .setIpAddress(ipInfo.ipAddress())
            .setIpCountry(ipInfo.country())
            .build();

        // track until settled/failed/reversed
        OPEN_PAYMENTS.put(transactionId, event);
        return event;
    }

    /**
     * Update existing payment event and return new one with new values
     *
     * @return new event with updated values
     */
    private static PaymentEvent createUpdateForExisting() {
        // pick random open transaction
        List<String> keys = new ArrayList<>(OPEN_PAYMENTS.keySet());
        String txId = keys.get(ThreadLocalRandom.current()
            .nextInt(keys.size()));
        PaymentEvent previous = OPEN_PAYMENTS.get(txId);

        PaymentStatus nextStatus = nextStatus(previous.getStatus());

        Instant now = Instant.now();
        PaymentEvent updated = PaymentEvent.newBuilder(previous)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setIngestedTs(now)
            .setStatus(nextStatus)
            .setTransactionTs(now)
            .build();

        if (isTerminal(nextStatus)) {
            OPEN_PAYMENTS.remove(txId);
        } else {
            OPEN_PAYMENTS.put(txId, updated);
        }
        return updated;
    }

    /**
     * Generates fraud payment based on current configuration and previous events.
     *
     * @return fraud payment event
     */
    private static PaymentEvent createFraudPayment() {
        switch (fraudType) {
            case VELOCITY:
                if (velocityLeft-- > 0) {
                    return flaggedVelocityPayment();
                }
            case HIGH_AMOUNT:
                if (highAmtLeft-- > 0) {
                    return flaggedHighAmountPayment();
                }
            case IMPOSSIBLE_TRAVEL:
                if (travelLeft-- > 0) {
                    return flaggedTravelPayment();
                }
            default:
                return createNewPayment();
        }
    }

    /* velocity follow-up (5-100) */
    private static PaymentEvent flaggedVelocityPayment() {
        PaymentEvent.Builder b = PaymentEvent.newBuilder(fraudTemplate)
            .setTransactionId(UUID.randomUUID())
            .setEventId(UUID.randomUUID())
            .setTransactionTs(Instant.now())
            .setIngestedTs(Instant.now())
            .setAmount(Utils.decimalToBuffer(Utils.money(Utils.rand(5, 100))))
            .setRiskScore(90)
            .setRulesTriggered(List.of("VELOCITY"));

        /* replace PAYEE party with a new merchant */
        List<Party> parties = new ArrayList<>(fraudTemplate.getParties());
        parties.set(1, randomParty(PartyType.MERCHANT, PartyRole.PAYEE));
        b.setParties(parties)
            .setMerchantId(parties.get(1)
                .getPartyId()
                .toString());

        return b.build();
    }

    /* high-amount 10k-50k, new merchant each time */
    private static PaymentEvent flaggedHighAmountPayment() {
        PaymentEvent.Builder b = PaymentEvent.newBuilder(fraudTemplate)
            .setTransactionId(UUID.randomUUID())
            .setEventId(UUID.randomUUID())
            .setTransactionTs(Instant.now())
            .setIngestedTs(Instant.now())
            .setAmount(Utils.decimalToBuffer(Utils.money(Utils.rand(10_000, 50_000))))
            .setRiskScore(95)
            .setRulesTriggered(List.of("HIGH_AMOUNT"));

        List<Party> parties = new ArrayList<>(fraudTemplate.getParties());
        parties.set(1, randomParty(Utils.pick(Arrays.asList(PartyType.MERCHANT, PartyType.PERSON)), PartyRole.PAYEE));
        b.setParties(parties)
            .setMerchantId(parties.get(1)
                .getPartyId()
                .toString());

        return b.build();
    }

    /* impossible travel */
    private static PaymentEvent flaggedTravelPayment() {
        IpInfo ipInfo = IpInfo.differentContinentIpInfo(fraudTemplate.getIpCountry().toString());

        PaymentEvent.Builder b = PaymentEvent.newBuilder(fraudTemplate)
            .setTransactionId(UUID.randomUUID())
            .setEventId(UUID.randomUUID())
            .setTransactionTs(Instant.now())
            .setIngestedTs(Instant.now())
            .setAmount(Utils.decimalToBuffer(Utils.randomAmount()))
            .setRiskScore(98)
            .setRulesTriggered(List.of("IMPOSSIBLE_TRAVEL"))
            .setIpAddress(ipInfo.ipAddress())
            .setIpCountry(ipInfo.country());

        List<Party> parties = new ArrayList<>(fraudTemplate.getParties());
        parties.set(1, randomParty(PartyType.MERCHANT, PartyRole.PAYEE));
        b.setParties(parties)
            .setMerchantId(parties.get(1)
                .getPartyId()
                .toString());

        return b.build();
    }

    /**
     * Determine next lifecycle status based on current one.
     */
    private static PaymentStatus nextStatus(PaymentStatus current) {
        switch (current) {
            case PENDING:
                int roll = ThreadLocalRandom.current()
                    .nextInt(100);
                if (roll < PROB_TO_SETTLED) {
                    return PaymentStatus.SETTLED;
                } else if (roll < PROB_TO_SETTLED + PROB_TO_FAILED) {
                    return PaymentStatus.FAILED;
                } else {
                    return PaymentStatus.REVERSED;
                }

            case SETTLED:
                return ThreadLocalRandom.current()
                    .nextInt(100) < 5
                    ? PaymentStatus.REVERSED
                    : PaymentStatus.SETTLED;

            default:
                return current;
        }
    }

    /**
     * Check whether the payment is in terminal state or not
     *
     * @param status current status
     * @return whether the payment is in terminal state or not
     */
    private static boolean isTerminal(PaymentStatus status) {
        return status == PaymentStatus.SETTLED
            || status == PaymentStatus.FAILED
            || status == PaymentStatus.REVERSED;
    }

    /**
     * Generates random payment method based on probability
     *
     * @return payment method
     */
    private static PaymentMethod randomPaymentMethod() {
        int r = ThreadLocalRandom.current()
            .nextInt(100);
        if (r < 60) return PaymentMethod.CARD;
        if (r < 80) return PaymentMethod.BANK_TRANSFER;
        if (r < 90) return PaymentMethod.E_WALLET;
        if (r < 95) return PaymentMethod.CRYPTO;
        return PaymentMethod.CASH;
    }

    /**
     * Determine value for payment processor based on used method
     *
     * @param method used payment method
     * @return used payment processor
     */
    private static String chooseProcessor(PaymentMethod method) {
        return switch (method) {
            case CARD -> FAKER.options()
                .nextElement(BANKS);
            case BANK_TRANSFER -> FAKER.options()
                .nextElement(METHODS);
            case E_WALLET -> FAKER.options()
                .nextElement(E_WALLET);
            case CRYPTO -> FAKER.options()
                .nextElement(CRYPTO_EXCHANGE);
            default -> null; // CASH
        };
    }

    /**
     * Generates random parties as part of the payment. It generates actors of the payment for source and target.
     *
     * @return generated parties
     */
    private static List<Party> randomParties() {
        List<Party> parties = new ArrayList<>(2);

        int variant = ThreadLocalRandom.current()
            .nextInt(4);
        switch (variant) {
            // company -> company
            case 0:
                parties.add(randomParty(PartyType.INSTITUTION, PartyRole.PAYER));
                parties.add(randomParty(PartyType.MERCHANT, PartyRole.PAYEE));
                break;
            // person-> company
            case 1:
                parties.add(randomParty(PartyType.PERSON, PartyRole.PAYER));
                parties.add(randomParty(PartyType.MERCHANT, PartyRole.PAYEE));
                break;
            // company -> person (refund)
            case 2:
                parties.add(randomParty(PartyType.MERCHANT, PartyRole.PAYER));
                parties.add(randomParty(PartyType.PERSON, PartyRole.PAYEE));
                break;
            // person-> person
            default:
                parties.add(randomParty(PartyType.PERSON, PartyRole.PAYER));
                parties.add(randomParty(PartyType.PERSON, PartyRole.PAYEE));
                break;
        }
        return parties;
    }

    /**
     * Generate single party for payment
     *
     * @param type party type
     * @param role party role
     * @return payment party
     */
    private static Party randomParty(PartyType type, PartyRole role) {
        String id = (type == PartyType.PERSON)
            ? "user-" + FAKER.number()
            .digits(6)
            : (type == PartyType.MERCHANT
            ? "merchant-" + FAKER.number()
            .digits(5)
            : "inst-" + FAKER.number()
            .digits(5));

        return Party.newBuilder()
            .setPartyId(id)
            .setPartyType(type)
            .setRole(role)
            .build();
    }

    private static FraudType pickFraud() {
        int prob = Utils.rand(100);

        if (prob < 60) {
            return FraudType.VELOCITY;
        } else if (prob < 80) {
            return FraudType.HIGH_AMOUNT;
        } else {
            return FraudType.IMPOSSIBLE_TRAVEL;
        }
    }
}

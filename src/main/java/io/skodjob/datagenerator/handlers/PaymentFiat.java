/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

import net.datafaker.Faker;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * This class is responsible for generating payment data using the Faker library.
 */
public class PaymentFiat {

    /**
     * Private constructor to make class static
     */
    private PaymentFiat() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "payment_data";

    // Constants for payment types
    private static final String PAYMENT_TYPE_CREDIT_CARD = "credit_card";
    private static final String PAYMENT_TYPE_BANK_TRANSFER = "bank_transfer";
    private static final String PAYMENT_TYPE_PAYPAL = "paypal";

    // Constants for payment statuses
    private static final String STATUS_COMPLETED = "completed";
    private static final String STATUS_PENDING = "pending";
    private static final String STATUS_FAILED = "failed";

    // Constants for credit card types
    private static final String CARD_TYPE_VISA = "Visa";
    private static final String CARD_TYPE_MASTERCARD = "MasterCard";
    private static final String CARD_TYPE_AMERICAN_EXPRESS = "American Express";
    private static final String CARD_TYPE_REVOLUT = "Revolut";
    private static final String CARD_TYPE_WISE = "Wise";
    private static final String CARD_TYPE_CITYGROUP = "CityGroup";
    private static final String CARD_TYPE_BARCLAYS = "Barclays";

    // Constants for payer/payee type
    private static final String COMPANY = "company";
    private static final String PERSON = "person";

    // List of world banks
    private static final List<String> BANKS = Arrays.asList(
        "JPMorgan Chase", "Bank of America", "Wells Fargo", "Citigroup", "Goldman Sachs",
        "Morgan Stanley", "HSBC", "BNP Paribas", "UBS", "Credit Suisse",
        "Barclays", "Deutsche Bank", "Standard Chartered", "Santander", "Societe Generale",
        "ING Group", "Mitsubishi UFJ Financial Group", "Mizuho Financial Group", "Sumitomo Mitsui Financial Group",
        "Bank of China", "Industrial and Commercial Bank of China", "China Construction Bank",
        "Agricultural Bank of China", "Banco Bradesco", "Itau Unibanco",
        "Royal Bank of Canada", "Toronto-Dominion Bank", "National Australia Bank", "Westpac", "ANZ"
    );

    /**
     * Generates payment data using the Faker library.
     *
     * @return the generated payment data as a JSON string
     */
    public static String generateData() {
        String transactionId = FAKER.regexify("txn_[0-9]{10}");
        String paymentType = FAKER.options()
            .option(PAYMENT_TYPE_CREDIT_CARD, PAYMENT_TYPE_BANK_TRANSFER, PAYMENT_TYPE_PAYPAL);
        double amount = FAKER.number().randomDouble(2, 10, 1000);
        String currency = FAKER.money().currencyCode();
        String date = FAKER.timeAndDate().past(1, java.util.concurrent.TimeUnit.DAYS)
            .atOffset(ZoneOffset.UTC).toString();
        String status = FAKER.options().option(STATUS_COMPLETED, STATUS_PENDING, STATUS_FAILED);

        String payerName, payerType, payeeName, payeeType;

        switch (FAKER.number().numberBetween(0, 4)) {
            case 0:
                payerName = FAKER.company().name();
                payerType = COMPANY;
                payeeName = FAKER.company().name();
                payeeType = COMPANY;
                break;
            case 1:
                payerName = FAKER.name().fullName();
                payerType = PERSON;
                payeeName = FAKER.company().name();
                payeeType = COMPANY;
                break;
            case 2:
                payerName = FAKER.company().name();
                payerType = COMPANY;
                payeeName = FAKER.name().fullName();
                payeeType = PERSON;
                break;
            default:
                payerName = FAKER.name().fullName();
                payerType = PERSON;
                payeeName = FAKER.name().fullName();
                payeeType = PERSON;
                break;
        }

        String payerAccountNumber = FAKER.number().digits(9);
        String payerBank = FAKER.options().nextElement(BANKS);
        String payerStreet = FAKER.address().streetAddress();
        String payerCity = FAKER.address().city();
        String payerState = FAKER.address().state();
        String payerCountry = FAKER.address().country();
        String payerPostalCode = FAKER.address().zipCode();

        String payeeAccountNumber = FAKER.number().digits(9);
        String payeeBank = FAKER.options().nextElement(BANKS);
        String payeeStreet = FAKER.address().streetAddress();
        String payeeCity = FAKER.address().city();
        String payeeState = FAKER.address().state();
        String payeeCountry = FAKER.address().country();
        String payeePostalCode = FAKER.address().zipCode();

        StringBuilder payerInfo = new StringBuilder(String.format(Locale.US,
            "\"name\":\"%s\"," +
                "\"payerType\":\"%s\"," +
                "\"account_number\":\"%s\"," +
                "\"bank\":\"%s\"," +
                "\"billing_address\":{" +
                "\"street\":\"%s\"," +
                "\"city\":\"%s\"," +
                "\"state\":\"%s\"," +
                "\"country\":\"%s\"," +
                "\"postal_code\":\"%s\"" +
                "}",
            payerName, payerType, payerAccountNumber, payerBank,
            payerStreet, payerCity, payerState, payerCountry, payerPostalCode));

        StringBuilder payeeInfo = new StringBuilder(String.format(Locale.US,
            "\"name\":\"%s\"," +
                "\"payeeType\":\"%s\"," +
                "\"account_number\":\"%s\"," +
                "\"bank\":\"%s\"," +
                "\"address\":{" +
                "\"street\":\"%s\"," +
                "\"city\":\"%s\"," +
                "\"state\":\"%s\"," +
                "\"country\":\"%s\"," +
                "\"postal_code\":\"%s\"" +
                "}",
            payeeName, payeeType, payeeAccountNumber, payeeBank,
            payeeStreet, payeeCity, payeeState, payeeCountry, payeePostalCode));

        if (PAYMENT_TYPE_CREDIT_CARD.equals(paymentType)) {
            String payerCardNumber = FAKER.finance().creditCard();
            String payerCardType = FAKER.options().option(CARD_TYPE_VISA, CARD_TYPE_MASTERCARD,
                CARD_TYPE_AMERICAN_EXPRESS, CARD_TYPE_REVOLUT, CARD_TYPE_WISE,
                CARD_TYPE_CITYGROUP, CARD_TYPE_BARCLAYS);
            String payerExpiryDate = FAKER.business().creditCardExpiry();

            payerInfo.append(String.format(Locale.US,
                "," +
                    "\"card_number\":\"%s\"," +
                    "\"card_type\":\"%s\"," +
                    "\"expiry_date\":\"%s\"",
                payerCardNumber, payerCardType, payerExpiryDate));
        }

        return String.format(Locale.US,
            "{\"payment\":{" +
                "\"transaction_id\":\"%s\"," +
                "\"type\":\"%s\"," +
                "\"amount\":%.2f," +
                "\"currency\":\"%s\"," +
                "\"date\":\"%s\"," +
                "\"status\":\"%s\"" +
                "}," +
                "\"payer\":{" +
                "%s" +
                "}," +
                "\"payee\":{" +
                "%s" +
                "}}",
            transactionId, paymentType, amount, currency, date, status,
            payerInfo,
            payeeInfo);
    }
}

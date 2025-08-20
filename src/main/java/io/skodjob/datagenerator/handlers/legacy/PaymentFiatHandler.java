/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.legacy;

import io.skodjob.datagenerator.models.paymentfiat.Address;
import io.skodjob.datagenerator.models.paymentfiat.Payee;
import io.skodjob.datagenerator.models.paymentfiat.Payer;
import io.skodjob.datagenerator.models.paymentfiat.PaymentDetails;
import io.skodjob.datagenerator.models.paymentfiat.PaymentFiat;
import net.datafaker.Faker;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible for generating payment data using the Faker library.
 */
public class PaymentFiatHandler {

    /**
     * Private constructor to make class static
     */
    private PaymentFiatHandler() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "payment_data";

    // Constants for payment types
    private static final String PAYMENT_TYPE_CREDIT_CARD = "creditCard";
    private static final String PAYMENT_TYPE_BANK_TRANSFER = "bankTransfer";
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

    private static PaymentFiat generatePaymentFiatData() {
        PaymentFiat paymentFiat = new PaymentFiat();

        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setTransactionId(FAKER.regexify("txn_[0-9]{10}"));
        paymentDetails.setType(FAKER.options().option(PAYMENT_TYPE_CREDIT_CARD,
            PAYMENT_TYPE_BANK_TRANSFER, PAYMENT_TYPE_PAYPAL));
        paymentDetails.setAmount(FAKER.number().randomDouble(2, 10, 1000));
        paymentDetails.setCurrency(FAKER.money().currencyCode());
        paymentDetails.setDate(FAKER.timeAndDate().past(1, java.util.concurrent.TimeUnit.DAYS)
            .atOffset(ZoneOffset.UTC).toString());
        paymentDetails.setStatus(FAKER.options().option(STATUS_COMPLETED, STATUS_PENDING, STATUS_FAILED));

        Payee payee = new Payee();
        Address payeeAddress = new Address();
        Payer payer = new Payer();
        Address payerAddress = new Address();

        switch (FAKER.number().numberBetween(0, 4)) {
            case 0:
                payer.setName(FAKER.company().name());
                payer.setPayerType(COMPANY);
                payee.setName(FAKER.company().name());
                payee.setPayeeType(COMPANY);
                break;
            case 1:
                payer.setName(FAKER.name().fullName());
                payer.setPayerType(PERSON);
                payee.setName(FAKER.company().name());
                payee.setPayeeType(COMPANY);
                break;
            case 2:
                payer.setName(FAKER.company().name());
                payer.setPayerType(COMPANY);
                payee.setName(FAKER.name().fullName());
                payee.setPayeeType(PERSON);
                break;
            default:
                payer.setName(FAKER.name().fullName());
                payer.setPayerType(PERSON);
                payee.setName(FAKER.name().fullName());
                payee.setPayeeType(PERSON);
                break;
        }

        payer.setAccountNumber(FAKER.number().digits(9));
        payer.setBank(FAKER.options().nextElement(BANKS));
        payerAddress.setStreet(FAKER.address().streetAddress());
        payerAddress.setCity(FAKER.address().city());
        payerAddress.setState(FAKER.address().state());
        payerAddress.setCountry(FAKER.address().country());
        payerAddress.setPostalCode(FAKER.address().zipCode());
        payer.setBillingAddress(payerAddress);

        if (PAYMENT_TYPE_CREDIT_CARD.contentEquals(paymentDetails.getType())) {
            payer.setCardNumber(FAKER.finance().creditCard());
            payer.setCardType(FAKER.options().option(CARD_TYPE_VISA, CARD_TYPE_MASTERCARD,
                CARD_TYPE_AMERICAN_EXPRESS, CARD_TYPE_REVOLUT, CARD_TYPE_WISE,
                CARD_TYPE_CITYGROUP, CARD_TYPE_BARCLAYS));
            payer.setExpiryDate(FAKER.business().creditCardExpiry());
        }

        payee.setAccountNumber(FAKER.number().digits(9));
        payee.setBank(FAKER.options().nextElement(BANKS));
        payeeAddress.setStreet(FAKER.address().streetAddress());
        payeeAddress.setCity(FAKER.address().city());
        payeeAddress.setState(FAKER.address().state());
        payeeAddress.setCountry(FAKER.address().country());
        payeeAddress.setPostalCode(FAKER.address().zipCode());
        payee.setAddress(payerAddress);

        paymentFiat.setPaymentDetails(paymentDetails);
        paymentFiat.setPayer(payer);
        paymentFiat.setPayee(payee);

        return paymentFiat;
    }

    /**
     * Generates payment data using the Faker library.
     *
     * @return the generated payment data as an Avro Object
     */
    public static Object generateData() {
        PaymentFiat paymentFiat = generatePaymentFiatData();

        return paymentFiat;
    }
}

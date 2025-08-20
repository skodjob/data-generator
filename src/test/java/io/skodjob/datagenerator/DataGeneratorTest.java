/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator;

import com.fasterxml.jackson.databind.JsonNode;
import io.skodjob.datagenerator.enums.ETemplateType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataGeneratorTest {
    private DataGenerator paymentEventGenerator;
    private DataGenerator flightEventGenerator;
    private DataGenerator iotDeviceTelemetryEventGenerator;
    private DataGenerator payrollEventGenerator;
    /// ///////////
    ///  Legacy ///
    /// ///////////
    private DataGenerator payrollDataGenerator;
    private DataGenerator iotDataGenerator;
    private DataGenerator stargateDataGenerator;
    private DataGenerator starwarsDataGenerator;
    private DataGenerator paymentFiatGenerator;
    private DataGenerator flightsGenerator;

    // reusable JSON-string pattern (allows escaped quotes)
    private static final String J = "\"(?:[^\"\\\\]|\\\\.)+\"";

    // ISO-8601 instant pattern
    private static final String ISO = "\"[0-9T:\\-\\.Z]+\"";

    // money bytes appear as random ISO-8859-1 chars → accept any non-quote sequence
    private static final String MONEY = J;

    @BeforeAll
    void setUp() {
        paymentEventGenerator = new DataGenerator(ETemplateType.PAYMENT_EVENT);
        flightEventGenerator = new DataGenerator(ETemplateType.FLIGHT_EVENT);
        iotDeviceTelemetryEventGenerator = new DataGenerator(ETemplateType.IOT_DEVICE_TELEMETRY_EVENT);
        payrollEventGenerator = new DataGenerator(ETemplateType.PAYROLL_EVENT);
        /// ///////////
        ///  Legacy ///
        /// ///////////
        payrollDataGenerator = new DataGenerator(ETemplateType.PAYROLL);
        iotDataGenerator = new DataGenerator(ETemplateType.IOT_DEVICE);
        stargateDataGenerator = new DataGenerator(ETemplateType.STARGATE);
        starwarsDataGenerator = new DataGenerator(ETemplateType.STARWARS);
        paymentFiatGenerator = new DataGenerator(ETemplateType.PAYMENT_FIAT);
        flightsGenerator = new DataGenerator(ETemplateType.FLIGHTS);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testRepeatedParameters")
    void testValidateGeneratedData(String testName, String regex, DataGenerator generator) {
        Pattern dataPattern = Pattern.compile(regex);

        String data = generator.generateJsonData()
            .toString();
        assertNotNull(data, "Generated string data should not be null for PAYROLL_EMPLOYEE");

        Matcher matcher = dataPattern.matcher(data);
        assertTrue(matcher.matches(), "Generated data doesn't match the expected format!");
    }

    @Test
    void testGenerateJsonData() throws IOException {
        checkJsonData(paymentEventGenerator);
        checkJsonData(flightEventGenerator);
        checkJsonData(iotDeviceTelemetryEventGenerator);
        checkJsonData(payrollEventGenerator);
        /// ///////////
        ///  Legacy ///
        /// ///////////
        checkJsonData(payrollDataGenerator);
        checkJsonData(iotDataGenerator);
        checkJsonData(stargateDataGenerator);
        checkJsonData(starwarsDataGenerator);
        checkJsonData(paymentFiatGenerator);
        checkJsonData(flightsGenerator);
    }

    void checkJsonData(DataGenerator generator) throws IOException {
        JsonNode jsonData = generator.generateJsonData();
        assertNotNull(jsonData, "Generated JSON data should not be null for " + generator.getTemplateType());
        assertTrue(jsonData.isObject(), "Generated JSON data should be a JSON object for " + generator.getTemplateType());
    }

    public Stream<Arguments> testRepeatedParameters() {
        String paymentEventRegex = "\\{"
            + "\"schemaVersion\":1,"
            + "\"event_id\":\"[0-9a-f\\-]{36}\","
            + "\"source\":\"datagen\","
            + "\"ingested_ts\"\\s*:\\s*\"[0-9T:\\-\\.Z]+\"\\s*,"
            + "\"transaction_id\":\"[0-9a-f\\-]{36}\","
            + "\"status\":\"(PENDING|SETTLED|FAILED|REVERSED)\","
            + "\"transaction_ts\"\\s*:\\s*\"[0-9T:\\-\\.Z]+\"\\s*,"
            + "\"amount\":\"(?:[^\"\\\\]|\\\\.)+\","
            + "\"currency\":\"[A-Z]{3}\","
            + "\"method\":\"(CARD|BANK_TRANSFER|E_WALLET|CRYPTO|CASH)\","
            + "\"processor\":(null|\"[^\"]+\"),"
            + "\"parties\":\\[\\{"
            + "\"party_id\":\"[^\"]+\","
            + "\"party_type\":\"(PERSON|MERCHANT|INSTITUTION)\","
            + "\"role\":\"PAYER\"\\},\\{"
            + "\"party_id\":\"[^\"]+\","
            + "\"party_type\":\"(PERSON|MERCHANT|INSTITUTION)\","
            + "\"role\":\"PAYEE\"\\}\\],"
            + "\"merchant_id\":(null|\"[^\"]+\"),"
            + "\"card_last4\":(null|\"\\d{4}\"),"
            + "\"risk_score\":(null|\\d+),"
            + "\"rules_triggered\":(null|\\[(?:\"[^\"]*\"(?:,\"[^\"]*\")*)?\\]),"
            + "\"ip_address\":(null|\"[^\"]+\"),"
            + "\"ip_country\":(null|\"[^\"]+\")"
            + "\\}";

        String flightEventRegex =
            "\\{"
                + "\"schemaVersion\":1,"
                + "\"event_id\":\"[0-9a-f\\-]{36}\","
                + "\"source\":\"datagen\","
                + "\"ingested_ts\":" + ISO + ","
                + "\"flight_id\":" + J + ","
                + "\"airline\":\"[A-Za-z0-9]+\","
                + "\"origin\":\"[A-Z0-9]{3,4}\","
                + "\"destination\":\"[A-Z0-9]{3,4}\","
                + "\"scheduled_departure_ts\":" + ISO + ","
                + "\"scheduled_arrival_ts\":" + ISO + ","
                + "\"actual_departure_ts\":(null|" + ISO + "),"
                + "\"actual_arrival_ts\":(null|" + ISO + "),"
                + "\"status\":\"(SCHEDULED|BOARDING|TAXI|AIRBORNE|LANDED|CANCELLED|DIVERTED)\","
                + "\"delay_minutes\":(null|\\d+),"
                + "\"gate\":(null|" + J + "),"
                + "\"aircraft_type\":(null|" + J + ")"
                + "\\}";

        String iotDevicePayload =
            "\\{\"button_state\":\"(PRESSED|RELEASED)\"\\}"
                + "|\\{\"light_state\":\"(ON|OFF)\",\"brightness_pct\":(null|\\d{1,3})\\}"
                + "|\\{\"gate_state\":\"(OPEN|CLOSED)\",\"position_pct\":(null|\\d{1,3})\\}"
                + "|\\{\"power_state\":\"(ON|OFF)\",\"watts\":(null|[-+]?[0-9]*\\.?[0-9]+)\\}"
                + "|\\{\"temperature_c\":[-+]?[0-9]*\\.?[0-9]+,\"humidity_pct\":(null|[-+]?[0-9]*\\.?[0-9]+)\\}"
                + "|\\{\"blob\":" + J + "\\}";

        String iotDeviceTelemetryRegex =
            "\\{"
                + "\"schemaVersion\":1,"
                + "\"event_id\":\"[0-9a-f\\-]{36}\","
                + "\"source\":\"datagen\","
                + "\"ingested_ts\":" + ISO + ","
                + "\"device_id\":" + J + ","
                + "\"ipv4\":\"\\d{1,3}(?:\\.\\d{1,3}){3}\","
                + "\"mac\":\"(?:[0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}\","
                + "\"geo\":\\{\"lat\":" + J + ",\"lon\":" + J + "\\},"
                + "\"sensor_type\":\"(LIGHT|BUTTON|THERMOMETER|PLUG|CUSTOM|GATE)\","
                + "\"battery_pct\":\\d{1,3},"
                + "\"reading_ts\":" + ISO + ","
                + "\"status\":\"(OK|WARN|FAIL)\","
                + "\"firmware\":(null|" + J + "),"
                + "\"payload\":(" + iotDevicePayload + ")"
                + "\\}";

        String payrollRegex =
            "\\{"
                + "\"schemaVersion\":1,"
                + "\"event_id\":\"[0-9a-f\\-]{36}\","
                + "\"source\":\"datagen\","
                + "\"ingested_ts\":" + ISO + ","
                + "\"employee_id\":" + J + ","
                + "\"payroll_id\":\"[0-9a-f\\-]{36}\","
                + "\"period_start\":\"\\d{4}-\\d{2}-\\d{2}\","
                + "\"period_end\":\"\\d{4}-\\d{2}-\\d{2}\","
                + "\"currency\":\"[A-Z]{3}\","
                + "\"salary_gross\":" + MONEY + ","
                + "\"deductions\":\\[\\{"
                + "\"type\":" + J + ",\"amount\":" + MONEY + "\\}"
                + "(,\\{\"type\":" + J + ",\"amount\":" + MONEY + "\\})*\\],"
                + "\"net_pay\":" + MONEY + ","
                + "\"status\":\"(CALCULATED|PAID|ADJUSTED)\""
                + "\\}";

        /// ///////////
        ///  Legacy ///
        /// ///////////
        String peopleRegex = "\\{"
            + "\"employeeId\":\"\\d+\","
            + "\"firstName\":\"[A-Za-z']+\","
            + "\"lastName\":\"[A-Za-z']+\","
            + "\"age\":\\d+,"
            + "\"ssn\":\"\\d+-\\d+-\\d+\","
            + "\"hourlyRate\":\\d+\\.\\d+,"
            + "\"gender\":\"(Male|Female)\","
            + "\"email\":\"[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\","
            + "\"company\":\"[A-Za-z-&\\.\\,\\-' ]+\""
            + "\\}";

        String iotRegex = "\\{"
            + "\"IPV4\":\"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\","
            + "\"MAC\":\"([0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}\","
            + "\"ID\":\"\\d+\","
            + "\"TYPE\":\"(light|button|thermometer|plug|custom|gate)\","
            + "\"LAST_UPDATE\":\"\\d+\","
            + "\"LINK_QUALITY\":\\d{1,3},"
            + "\"DATA\":\\{.*\\}"
            + "\\}";

        String starGateRegex = "\\{\"character_name\":\"[\\w\\s'-]+\"," +
            "\"source_planet\":\"[\\w\\s-]+\"," +
            "\"target_planet\":\"[\\w\\s-]+\"," +
            "\"quote\":\"[^\"]*\"," +
            "\"duration\":[0-9]+," +
            "\"duration_unit\":\"seconds\"," +
            "\"distance\":[0-9]+," +
            "\"distance_unit\":\"light_year\"\\}";

        String starWarsRegex = "\\{\"character_name\":\"[\\w\\s'-]+\"," +
            "\"source_planet\":\"[\\w\\s-]+\"," +
            "\"target_planet\":\"[\\w\\s-]+\"," +
            "\"quote\":\"[^\"]*\"," +
            "\"callSign\":\"[\\w\\s\\-]+\"," +
            "\"species\":\"[\\w\\s'-]+\"," +
            "\"vehicle\":\"[\\w\\s\\-']+\"," +
            "\"wookieWords\":\"[^\"]*\"," +
            "\"alternateCharacterSpelling\":\"[^\"]*\"\\}";

        String paymentFiatRegex = "\\{\"paymentDetails\":\\{\"transactionId\":\"txn_[0-9]{10}\"," +
            "\"type\":\"(creditCard|bankTransfer|paypal)\"," +
            "\"amount\":[0-9]+\\.[0-9]{1,}," +
            "\"currency\":\"[A-Z]{1,}\"," +
            "\"date\":\"[0-9T:\\-\\+\\.Z]+\"," +
            "\"status\":\"(completed|pending|failed)\"\\}," +
            "\"payer\":\\{\"name\":\"[^\"]{3,}\"," +
            "\"payerType\":\"(company|person)\"," +
            "\"accountNumber\":\"[0-9]{9}\"," +
            "\"bank\":\"[^\"]{3,}\"," +
            "\"billingAddress\":\\{\"street\":\"[^\"]{3,}\"," +
            "\"city\":\"[^\"]{3,}\"," +
            "\"state\":\"[^\"]{3,}\"," +
            "\"country\":\"[^\"]{3,}\"," +
            "\"postalCode\":\"[^\"]{3,}\"\\}" +
            "(,\"cardNumber\":(null|\"[0-9\\-]+\")," +
            "\"cardType\":(null|\"[^\"]{3,}\")," +
            "\"expiryDate\":(null|\"[0-9\\-]+\"))?\\}," +
            "\"payee\":\\{\"name\":\"[^\"]{3,}\"," +
            "\"payeeType\":\"(company|person)\"," +
            "\"accountNumber\":\"[0-9]{9}\"," +
            "\"bank\":\"[^\"]{3,}\"," +
            "\"address\":\\{\"street\":\"[^\"]{3,}\"," +
            "\"city\":\"[^\"]{3,}\"," +
            "\"state\":\"[^\"]{3,}\"," +
            "\"country\":\"[^\"]{3,}\"," +
            "\"postalCode\":\"[^\"]{3,}\"\\}\\}\\}";

        String flightRegex = "\\{" +
            "\"passenger\":\\{" +
            "\"id\":\"[A-Z0-9\\-]+\"," +
            "\"name\":\"[^\"]{4,}\"," +
            "\"passport_number\":\"[A-Z]{2}[0-9]{6}\"," +
            "\"nationality\":\"[^\"]{2,}\"\\}," +
            "\"flight\":\\{\"number\":\"[A-Z]{2}[0-9]{4}\"," +
            "\"departure_airport\":\"[\\w\\s]+\"," +
            "\"arrival_airport\":\"[\\w\\s]+\"," +
            "\"departure_time\":\"[0-9T:\\-\\+\\.Z]+\"," +
            "\"arrival_time\":\"[0-9T:\\-\\+\\.Z]+\"," +
            "\"seat_number\":\"[A-Z][0-9]{2}\"," +
            "\"gate\":\"[A-Z][0-9]{1,2}\"," +
            "\"boarding_group\":\"[A-F]\"," +
            "\"plane_model\":\"[^\"]{4,}\"," +
            "\"airline\":\"[^\"]{4,}\"\\}\\}";

        return IntStream.range(0, 10000)
            .mapToObj(i -> Stream.of(
                Arguments.of(ETemplateType.PAYMENT_EVENT.getTemplateName(), paymentEventRegex,
                    this.paymentEventGenerator),
                Arguments.of(ETemplateType.FLIGHT_EVENT.getTemplateName(), flightEventRegex,
                    this.flightEventGenerator),
                Arguments.of(ETemplateType.IOT_DEVICE_TELEMETRY_EVENT.getTemplateName(), iotDeviceTelemetryRegex,
                    this.iotDeviceTelemetryEventGenerator),
                Arguments.of(ETemplateType.PAYROLL_EVENT.getTemplateName(), payrollRegex,
                    this.payrollEventGenerator),
                /// ///////////
                ///  Legacy ///
                /// ///////////
                Arguments.of(ETemplateType.PAYROLL.getTemplateName(), peopleRegex,
                    this.payrollDataGenerator),
                Arguments.of(ETemplateType.IOT_DEVICE.getTemplateName(), iotRegex,
                    this.iotDataGenerator),
                Arguments.of(ETemplateType.STARGATE.getTemplateName(), starGateRegex,
                    this.stargateDataGenerator),
                Arguments.of(ETemplateType.STARWARS.getTemplateName(), starWarsRegex,
                    this.starwarsDataGenerator),
                Arguments.of(ETemplateType.PAYMENT_FIAT.getTemplateName(), paymentFiatRegex,
                    this.paymentFiatGenerator),
                Arguments.of(ETemplateType.FLIGHTS.getTemplateName(), flightRegex,
                    this.flightsGenerator)
            ))
            .flatMap(stream -> stream);
    }
}

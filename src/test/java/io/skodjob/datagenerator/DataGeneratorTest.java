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
    private DataGenerator payrollDataGenerator;
    private DataGenerator iotDataGenerator;
    private DataGenerator stargateDataGenerator;
    private DataGenerator starwarsDataGenerator;
    private DataGenerator paymentFiatGenerator;
    private DataGenerator flightsGenerator;

    @BeforeAll
    void setUp() {
        payrollDataGenerator = new DataGenerator(ETemplateType.PAYROLL);
        iotDataGenerator = new DataGenerator(ETemplateType.IOT_DEVICE);
        stargateDataGenerator = new DataGenerator(ETemplateType.STARGATE);
        starwarsDataGenerator = new DataGenerator(ETemplateType.STARWARS);
        paymentFiatGenerator = new DataGenerator(ETemplateType.PAYMENT_FIAT);
        flightsGenerator = new DataGenerator(ETemplateType.FLIGHTS);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testRepeatedParameters")
    void testValidateGeneratedData(String testName, String regex, DataGenerator generator) throws IOException {
        Pattern dataPattern = Pattern.compile(regex);

        String data = generator.generateJsonData().toString();
        assertNotNull(data, "Generated string data should not be null for PAYROLL_EMPLOYEE");

        Matcher matcher = dataPattern.matcher(data);
        assertTrue(matcher.matches(), "Generated data doesn't match the expected format!");
    }

    @Test
    void testGenerateJsonData() throws IOException {
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

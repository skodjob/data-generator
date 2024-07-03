/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator;

import com.fasterxml.jackson.databind.JsonNode;
import io.skodjob.loadgenerator.enums.ETemplateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DataGeneratorTest {
    private DataGenerator payrollDataGenerator;
    private DataGenerator iotDataGenerator;

    @BeforeEach
    void setUp() {
        payrollDataGenerator = new DataGenerator(ETemplateType.PAYROLL_EMPLOYEE);
        iotDataGenerator = new DataGenerator(ETemplateType.IOT_DEVICE);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testRepeatedParameters")
    void testValidateGeneratedData(String testName, String regex, DataGenerator generator) {
        Pattern dataPattern = Pattern.compile(regex);

        String data = generator.generateStringData();
        assertNotNull(data, "Generated string data should not be null for PAYROLL_EMPLOYEE");
        assertFalse(data.contains("\"@"), "Generated string data should not contain placeholders for PAYROLL_EMPLOYEE");

        Matcher matcher = dataPattern.matcher(data);
        assertTrue(matcher.matches(), "Generated data doesn't match the expected format!");
    }

    @Test
    void testGenerateJsonDataPayroll() throws IOException {
        JsonNode jsonData = payrollDataGenerator.generateJsonData();
        assertNotNull(jsonData, "Generated JSON data should not be null for PAYROLL_EMPLOYEE");
        assertTrue(jsonData.isObject(), "Generated JSON data should be a JSON object for PAYROLL_EMPLOYEE");
    }

    @Test
    void testGenerateJsonDataIot() throws IOException {
        JsonNode jsonData = iotDataGenerator.generateJsonData();
        assertNotNull(jsonData, "Generated JSON data should not be null for IOT_DEVICE");
        assertTrue(jsonData.isObject(), "Generated JSON data should be a JSON object for IOT_DEVICE");
    }

    @Test
    void testInvalidTemplateType() {
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            DataGenerator invalidDataGenerator = new DataGenerator(null);
            invalidDataGenerator.generateStringData();
        }, "Expected generateStringData() to throw an exception, but it didn't");

        assertTrue(thrown.getMessage().contains("TemplateType cannot be null!"), "Exception message should contain 'TemplateType cannot be null!'");
    }

    public static Stream<Arguments> testRepeatedParameters() {
        String peopleRegex = "\\{"
            + "\"employee_id\":\"\\d+\","
            + "\"first_name\":\"[A-Za-z']+\","
            + "\"last_name\":\"[A-Za-z']+\","
            + "\"age\":\\d+,"
            + "\"ssn\":\"\\d+-\\d+-\\d+\","
            + "\"hourly_rate\":\\d+\\.\\d+,"
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


        return IntStream.range(0, 10000)
            .mapToObj(i -> Stream.of(
                Arguments.of(ETemplateType.PAYROLL_EMPLOYEE.getTemplateName(), peopleRegex,
                    new DataGenerator(ETemplateType.PAYROLL_EMPLOYEE)),
                Arguments.of(ETemplateType.IOT_DEVICE.getTemplateName(), iotRegex,
                    new DataGenerator(ETemplateType.IOT_DEVICE))
            ))
            .flatMap(stream -> stream);
    }
}

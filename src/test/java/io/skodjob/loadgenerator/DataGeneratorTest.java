/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator;

import com.fasterxml.jackson.databind.JsonNode;
import io.skodjob.loadgenerator.enums.ETemplateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataGeneratorTest {
    private DataGenerator payrollDataGenerator;
    private DataGenerator iotDataGenerator;

    @BeforeEach
    void setUp() {
        payrollDataGenerator = new DataGenerator(ETemplateType.PAYROLL_EMPLOYEE);
        iotDataGenerator = new DataGenerator(ETemplateType.IOT_DEVICE);
    }

    @Test
    void testGenerateStringDataPayroll() {
        String data = payrollDataGenerator.generateStringData();
        assertNotNull(data, "Generated string data should not be null for PAYROLL_EMPLOYEE");
        assertFalse(data.contains("\"@"), "Generated string data should not contain placeholders for PAYROLL_EMPLOYEE");
    }

    @Test
    void testGenerateStringDataIot() {
        String data = iotDataGenerator.generateStringData();
        assertNotNull(data, "Generated string data should not be null for IOT_DEVICE");
        assertFalse(data.contains("\"@"), "Generated string data should not contain placeholders for IOT_DEVICE");
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
}

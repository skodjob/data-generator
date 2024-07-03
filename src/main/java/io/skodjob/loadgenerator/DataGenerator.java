/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.skodjob.loadgenerator.enums.ETemplateType;
import io.skodjob.loadgenerator.handlers.IotDevice;
import io.skodjob.loadgenerator.handlers.Payroll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * This class is responsible for generating data based on specified templates.
 */
public class DataGenerator {
    private static final Logger LOGGER = LogManager.getLogger(DataGenerator.class);

    private final ETemplateType templateType;

    /**
     * Constructor for DataGenerator.
     *
     * @param templateType the type of template to be used for data generation
     */
    public DataGenerator(ETemplateType templateType) {
        this.templateType = Objects.requireNonNull(templateType, "TemplateType cannot be null!");
        LOGGER.info("Initialized DataGenerator with template type {}", templateType.getTemplateName());
    }

    /**
     * Generates string data based on the template type.
     *
     * @return the generated string data
     */
    public String generateStringData() {
        switch (this.templateType) {
            case PAYROLL_EMPLOYEE:
                return Payroll.generateData();
            case IOT_DEVICE:
                return IotDevice.generateData();
            default:
                throw new IllegalArgumentException("Unknown template type: " + this.templateType);
        }
    }

    /**
     * Generates JSON data based on the template type.
     *
     * @return the generated JSON data
     */
    public JsonNode generateJsonData() {
        try {
            return new ObjectMapper().readTree(generateStringData());
        } catch (Exception e) {
            throw new RuntimeException("Error generating JSON data", e);
        }
    }
}

/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.skodjob.datagenerator.enums.ETemplateType;
import io.skodjob.datagenerator.handlers.FlightsHandler;
import io.skodjob.datagenerator.handlers.IotDeviceHandler;
import io.skodjob.datagenerator.handlers.PaymentFiatHandler;
import io.skodjob.datagenerator.handlers.PayrollHandler;
import io.skodjob.datagenerator.handlers.StarGateHandler;
import io.skodjob.datagenerator.handlers.StarWarsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * This class is responsible for generating data based on specified templates.
 */
public class DataGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataGenerator.class);

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
    public Object generateData() {
        switch (this.templateType) {
            case PAYROLL:
                return PayrollHandler.generateData();
            case IOT_DEVICE:
                return IotDeviceHandler.generateData();
            case STARGATE:
                return StarGateHandler.generateData();
            case STARWARS:
                return StarWarsHandler.generateData();
            case PAYMENT_FIAT:
                return PaymentFiatHandler.generateData();
            case FLIGHTS:
                return FlightsHandler.generateData();
            default:
                throw new IllegalArgumentException("Unknown template type: " + this.templateType);
        }
    }

    /**
     * Generates JSON data based on the template type.
     *
     * @return the generated JSON data
     * @throws IOException exception during JSON parsing
     */
    public JsonNode generateJsonData() throws IOException {
        try {
            return new ObjectMapper().readTree(generateData().toString());
        } catch (JsonProcessingException e) {
            throw new IOException("Error generating JSON data", e);
        }
    }

    /**
     * Return template type ued within the generator
     *
     * @return templateType
     */
    public ETemplateType getTemplateType() {
        return templateType;
    }
}

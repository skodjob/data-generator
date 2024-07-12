/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.skodjob.datagenerator.enums.ETemplateType;
import io.skodjob.datagenerator.handlers.Flights;
import io.skodjob.datagenerator.handlers.IotDevice;
import io.skodjob.datagenerator.handlers.PaymentFiat;
import io.skodjob.datagenerator.handlers.Payroll;
import io.skodjob.datagenerator.handlers.StarGate;
import io.skodjob.datagenerator.handlers.StarWars;
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
            case PAYROLL:
                return Payroll.generateData();
            case IOT_DEVICE:
                return IotDevice.generateData();
            case STARGATE:
                return StarGate.generateData();
            case STARWARS:
                return StarWars.generateData();
            case PAYMENT_FIAT:
                return PaymentFiat.generateData();
            case FLIGHTS:
                return Flights.generateData();
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

    /**
     * Return template type ued within the generator
     *
     * @return templateType
     */
    public ETemplateType getTemplateType() {
        return templateType;
    }
}

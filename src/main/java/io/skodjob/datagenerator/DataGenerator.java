/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.skodjob.datagenerator.enums.ETemplateType;
import io.skodjob.datagenerator.handlers.legacy.FlightsHandler;
import io.skodjob.datagenerator.handlers.legacy.IotDeviceHandler;
import io.skodjob.datagenerator.handlers.legacy.PaymentFiatHandler;
import io.skodjob.datagenerator.handlers.legacy.PayrollHandler;
import io.skodjob.datagenerator.handlers.legacy.StarGateHandler;
import io.skodjob.datagenerator.handlers.legacy.StarWarsHandler;
import io.skodjob.datagenerator.handlers.v1.FlightEventHandler;
import io.skodjob.datagenerator.handlers.v1.IotDeviceTelemetryEventHandler;
import io.skodjob.datagenerator.handlers.v1.PaymentEventHandler;
import io.skodjob.datagenerator.handlers.v1.PayrollEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * This class is responsible for generating data based on specified templates.
 */
public class DataGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataGenerator.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();
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
        return switch (this.templateType) {
            case PAYMENT_EVENT -> PaymentEventHandler.generateData();
            case FLIGHT_EVENT -> FlightEventHandler.generateData();
            case IOT_DEVICE_TELEMETRY_EVENT -> IotDeviceTelemetryEventHandler.generateData();
            case PAYROLL_EVENT -> PayrollEventHandler.generateData();
            /// ///////////
            ///  Legacy ///
            /// ///////////
            case PAYROLL -> PayrollHandler.generateData();
            case IOT_DEVICE -> IotDeviceHandler.generateData();
            case STARGATE -> StarGateHandler.generateData();
            case STARWARS -> StarWarsHandler.generateData();
            case PAYMENT_FIAT -> PaymentFiatHandler.generateData();
            case FLIGHTS -> FlightsHandler.generateData();
        };
    }

    /**
     * Generates JSON data based on the template type.
     *
     * @return the generated JSON data
     */
    public JsonNode generateJsonData() {
        try {
            return MAPPER.readTree(generateData().toString());
        } catch (IOException e) {
            throw new DataGeneratorException("Failed to serialise generator output", e);
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

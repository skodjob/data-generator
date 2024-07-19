/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.enums;

import io.skodjob.datagenerator.handlers.FlightsHandler;
import io.skodjob.datagenerator.handlers.IotDeviceHandler;
import io.skodjob.datagenerator.handlers.PaymentFiatHandler;
import io.skodjob.datagenerator.handlers.PayrollHandler;
import io.skodjob.datagenerator.handlers.StarGateHandler;
import io.skodjob.datagenerator.handlers.StarWarsHandler;

/**
 * Enum representing different template types for use in the load generator.
 */
public enum ETemplateType {
    /**
     * Template for People Payrol data
     */
    PAYROLL(PayrollHandler.TEMPLATE_NAME),

    /**
     * Template for IoT device data
     */
    IOT_DEVICE(IotDeviceHandler.TEMPLATE_NAME),

    /**
     * Template for StarGate data
     */
    STARGATE(StarGateHandler.TEMPLATE_NAME),

    /**
     * Template for StarWars data
     */
    STARWARS(StarWarsHandler.TEMPLATE_NAME),

    /**
     * Template for Payment data
     */
    PAYMENT_FIAT(PaymentFiatHandler.TEMPLATE_NAME),

    /**
     * Template for Flights data
     */
    FLIGHTS(FlightsHandler.TEMPLATE_NAME);

    private final String templateName;

    /**
     * Constructor for ETemplateType.
     *
     * @param templateName the name of the template
     */
    ETemplateType(String templateName) {
        this.templateName = templateName;
    }

    /**
     * Gets the name of the template.
     *
     * @return the name of the template
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * Gets the name of the template based on String name
     *
     * @param value string representation of template name
     * @return specific template
     */
    public static ETemplateType getFromString(String value) {
        for (ETemplateType type : values()) {
            if (type.toString().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}

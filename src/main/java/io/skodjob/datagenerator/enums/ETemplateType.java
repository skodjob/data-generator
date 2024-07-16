/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.enums;

import io.skodjob.datagenerator.handlers.Flights;
import io.skodjob.datagenerator.handlers.IotDevice;
import io.skodjob.datagenerator.handlers.PaymentFiat;
import io.skodjob.datagenerator.handlers.Payroll;
import io.skodjob.datagenerator.handlers.StarGate;
import io.skodjob.datagenerator.handlers.StarWars;

/**
 * Enum representing different template types for use in the load generator.
 */
public enum ETemplateType {
    /**
     * Template for People Payrol data
     */
    PAYROLL(Payroll.TEMPLATE_NAME),

    /**
     * Template for IoT device data
     */
    IOT_DEVICE(IotDevice.TEMPLATE_NAME),

    /**
     * Template for StarGate data
     */
    STARGATE(StarGate.TEMPLATE_NAME),

    /**
     * Template for StarWars data
     */
    STARWARS(StarWars.TEMPLATE_NAME),

    /**
     * Template for Payment data
     */
    PAYMENT_FIAT(PaymentFiat.TEMPLATE_NAME),

    /**
     * Template for Flights data
     */
    FLIGHTS(Flights.TEMPLATE_NAME);

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

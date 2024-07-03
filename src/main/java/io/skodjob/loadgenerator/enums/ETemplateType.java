/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator.enums;

/**
 * Enum representing different template types for use in the load generator.
 */
public enum ETemplateType {
    /**
     * Template for People Payrol data
     */
    PAYROLL_EMPLOYEE("payroll_employee"),

    /**
     * Template for IoT device data
     */
    IOT_DEVICE("iot_device");

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
}

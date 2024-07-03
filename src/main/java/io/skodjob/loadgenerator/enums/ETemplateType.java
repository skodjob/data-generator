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
    PAYROLL_EMPLOYEE("payroll_employee", "templates/payroll_employee.json"),

    /**
     * Template for IoT device data
     */
    IOT_DEVICE("iot_device", "templates/iot_device.json");

    private final String templateName;
    private final String templatePath;

    /**
     * Constructor for ETemplateType.
     *
     * @param templateName the name of the template
     * @param templatePath the path to the template file
     */
    ETemplateType(String templateName, String templatePath) {
        this.templateName = templateName;
        this.templatePath = templatePath;
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
     * Gets the path to the template file.
     *
     * @return the path to the template file
     */
    public String getTemplatePath() {
        return templatePath;
    }
}

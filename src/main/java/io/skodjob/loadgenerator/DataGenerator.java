/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.skodjob.loadgenerator.enums.ETemplateType;
import io.skodjob.loadgenerator.handlers.IotDevice;
import io.skodjob.loadgenerator.handlers.People;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * This class is responsible for generating data based on specified templates.
 */
public class DataGenerator {
    private static final Logger LOGGER = LogManager.getLogger(DataGenerator.class);

    private final String templateJson;
    private final ETemplateType templateType;

    /**
     * Constructor for DataGenerator.
     *
     * @param templateType the type of template to be used for data generation
     */
    // The suppression is needed due to false positive result
    @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
    public DataGenerator(ETemplateType templateType) {
        this.templateType = Objects.requireNonNull(templateType, "TemplateType cannot be null!");
        try {
            this.templateJson = loadTemplate(templateType.getTemplatePath());
        } catch (IOException e) {
            LOGGER.error("Error loading template", e);
            throw new RuntimeException("Failed to load template: " + templateType.getTemplatePath(), e);
        }

        LOGGER.info("Loaded {} template with location {}", templateType.getTemplateName(),
            templateType.getTemplatePath());
    }

    /**
     * Loads the template from the specified path.
     *
     * @param templateName the name of the template file
     * @return the content of the template file as a string
     * @throws IOException if an I/O error occurs while loading the template
     */
    private String loadTemplate(String templateName) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(templateName)) {
            if (inputStream == null) {
                throw new IOException("Template not found: " + templateName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    /**
     * Generates string data based on the template type.
     *
     * @return the generated string data
     */
    public String generateStringData() {
        switch (this.templateType) {
            case PAYROLL_EMPLOYEE:
                return Utils.stripWhitespace(People.fillTemplate(this.templateJson));
            case IOT_DEVICE:
                return Utils.stripWhitespace(IotDevice.fillTemplate(this.templateJson));
            default:
                throw new IllegalArgumentException("Unknown template type: " + this.templateType);
        }
    }

    /**
     * Generates JSON data based on the template type.
     *
     * @return the generated JSON data
     * @throws IOException if an I/O error occurs while generating JSON data
     */
    public JsonNode generateJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(generateStringData());
    }
}

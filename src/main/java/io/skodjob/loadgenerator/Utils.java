/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class providing common methods for data loading and JSON manipulation.
 */
public class Utils {

    private Utils() {}

    /**
     * Loads data from a file and returns it as a list of strings.
     *
     * @param filename the name of the file to load
     * @return a list of strings containing the data from the file
     * @throws IOException if an I/O error occurs
     */
    public static List<String> loadData(String filename) throws IOException {
        try (InputStream inputStream = Utils.class.getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    /**
     * Removes all whitespace characters from a JSON string.
     *
     * @param jsonString the original JSON string
     * @return the JSON string without whitespace characters
     */
    public static String stripWhitespace(String jsonString) {
        return jsonString.replaceAll("\\s+", "");
    }

    /**
     * Converts a JSON string into a pretty-printed JSON format.
     *
     * @param jsonString the original JSON string
     * @return the pretty-printed JSON string
     * @throws IOException if an I/O error occurs
     */
    public static String prettyPrintJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        JsonNode jsonNode = objectMapper.readTree(jsonString);
        return objectMapper.writeValueAsString(jsonNode);
    }
}

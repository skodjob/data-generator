/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

import net.datafaker.Faker;

import java.util.Locale;

/**
 * This class generates random StarGate-related data for use in templates.
 */
public class StarGate {

    /**
     * Private constructor to make class static
     */
    private StarGate() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "stargate";

    /**
     * Generates StarGate data using the Faker library.
     *
     * @return the generated StarGate data as a JSON string
     */
    public static String generateData() {
        String characterName = FAKER.stargate().characters();
        String sourcePlanet = FAKER.stargate().planets();
        String targetPlanet = FAKER.stargate().planets();
        String quote = FAKER.stargate().quotes();
        int duration = FAKER.number().numberBetween(1, 50);
        int distance = FAKER.number().numberBetween(20000, 999999);

        return String.format(Locale.US,
            "{\"character_name\":\"%s\"," +
                "\"source_planet\":\"%s\"," +
                "\"target_planet\":\"%s\"," +
                "\"quote\":\"%s\"," +
                "\"duration\":%d," +
                "\"duration_unit\":\"seconds\"," +
                "\"distance\":\"%s\"," +
                "\"distance_unit\":\"light_year\"}",
            characterName, sourcePlanet, targetPlanet, quote, duration, distance);
    }
}

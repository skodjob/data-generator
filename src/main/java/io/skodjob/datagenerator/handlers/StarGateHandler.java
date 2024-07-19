/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

import io.skodjob.datagenerator.models.stargate.StarGate;
import net.datafaker.Faker;

/**
 * This class generates random StarGate-related data for use in templates.
 */
public class StarGateHandler {

    /**
     * Private constructor to make class static
     */
    private StarGateHandler() {

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
        StarGate starGate = new StarGate();
        starGate.setCharacterName(FAKER.stargate().characters());
        starGate.setSourcePlanet(FAKER.stargate().planets());
        starGate.setTargetPlanet(FAKER.stargate().planets());
        starGate.setQuote(FAKER.stargate().quotes());
        starGate.setDistance(FAKER.number().numberBetween(1, 50));
        starGate.setDistanceUnit("light_year");
        starGate.setDuration(FAKER.number().numberBetween(20000, 999999));
        starGate.setDurationUnit("seconds");

        return starGate.toString();
    }
}

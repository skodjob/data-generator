/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

import net.datafaker.Faker;

import java.util.Locale;

/**
 * This class generates random StarWars-related data for use in templates.
 */
public class StarWars {

    /**
     * Private constructor to make class static
     */
    private StarWars() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "starwars";

    /**
     * Generates StarWars data using the Faker library.
     *
     * @return the generated StarWars data as a JSON string
     */
    public static String generateData() {
        String characterName = FAKER.starWars().character();
        String sourcePlanet = FAKER.starWars().planets();
        String targetPlanet = FAKER.starWars().planets();
        String quote = FAKER.starWars().quotes();
        String callSign = FAKER.starWars().callSign();
        String species = FAKER.starWars().species();
        String vehicle = FAKER.starWars().vehicles();
        String wookieWords = FAKER.starWars().wookieWords();
        String alternateCharacterSpelling = FAKER.starWars().alternateCharacterSpelling();

        return String.format(Locale.US,
            "{\"character_name\":\"%s\"," +
                "\"source_planet\":\"%s\"," +
                "\"target_planet\":\"%s\"," +
                "\"quote\":\"%s\"," +
                "\"callSign\":\"%s\"," +
                "\"species\":\"%s\"," +
                "\"vehicle\":\"%s\"," +
                "\"wookieWords\":\"%s\"," +
                "\"alternateCharacterSpelling\":\"%s\"}",
            characterName, sourcePlanet, targetPlanet, quote, callSign, species,
            vehicle, wookieWords, alternateCharacterSpelling);
    }
}

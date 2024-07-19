/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

import io.skodjob.datagenerator.models.starwars.StarWars;
import net.datafaker.Faker;

/**
 * This class generates random StarWars-related data for use in templates.
 */
public class StarWarsHandler {

    /**
     * Private constructor to make class static
     */
    private StarWarsHandler() {

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
        StarWars starWars = new StarWars();
        starWars.setCharacterName(FAKER.starWars().character());
        starWars.setSourcePlanet(FAKER.starWars().planets());
        starWars.setTargetPlanet(FAKER.starWars().planets());
        starWars.setQuote(FAKER.starWars().quotes());
        starWars.setCallSign(FAKER.starWars().callSign());
        starWars.setSpecies(FAKER.starWars().species());
        starWars.setVehicle(FAKER.starWars().vehicles());
        starWars.setWookieWords(FAKER.starWars().wookieWords());
        starWars.setAlternateCharacterSpelling(FAKER.starWars().alternateCharacterSpelling());

        return starWars.toString();
    }
}

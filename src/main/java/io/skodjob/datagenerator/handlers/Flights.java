/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

import net.datafaker.Faker;

import java.time.ZoneOffset;
import java.util.Locale;

/**
 * This class is responsible for generating flight data using the Faker library.
 */
public class Flights {

    /**
     * Private constructor to make class static
     */
    private Flights() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "flights";

    /**
     * Generates flight data using the Faker library.
     *
     * @return the generated flight data as a JSON string
     */
    public static String generateData() {
        String passengerId = FAKER.idNumber().valid();
        String passengerName = FAKER.name().fullName();
        String passportNumber = FAKER.regexify("[A-Z]{2}[0-9]{6}");
        String nationality = FAKER.nation().nationality();
        String flightNumber = FAKER.regexify("[A-Z]{2}[0-9]{4}");
        String departureAirport = FAKER.aviation().airport();
        String arrivalAirport = FAKER.aviation().airport();
        String departureTime = FAKER.timeAndDate().future(1, java.util.concurrent.TimeUnit.DAYS)
            .atOffset(ZoneOffset.UTC).toString();
        String arrivalTime = FAKER.timeAndDate().future(2, java.util.concurrent.TimeUnit.DAYS)
            .atOffset(ZoneOffset.UTC).toString();
        String seatNumber = FAKER.regexify("[A-Z][0-9]{2}");
        String gate = FAKER.regexify("[A-Z][0-9]{1,2}");
        String boardingGroup = FAKER.options().option("A", "B", "C", "D", "E", "F");
        String planeModel = FAKER.aviation().aircraft();
        String airline = FAKER.aviation().airline();

        return String.format(Locale.US,
            "{\"passenger\":{" +
                "\"id\":\"%s\"," +
                "\"name\":\"%s\"," +
                "\"passport_number\":\"%s\"," +
                "\"nationality\":\"%s\"" +
                "}," +
                "\"flight\":{" +
                "\"number\":\"%s\"," +
                "\"departure_airport\":\"%s\"," +
                "\"arrival_airport\":\"%s\"," +
                "\"departure_time\":\"%s\"," +
                "\"arrival_time\":\"%s\"," +
                "\"seat_number\":\"%s\"," +
                "\"gate\":\"%s\"," +
                "\"boarding_group\":\"%s\"," +
                "\"plane_model\":\"%s\"," +
                "\"airline\":\"%s\"" +
                "}}",
            passengerId, passengerName, passportNumber, nationality,
            flightNumber, departureAirport, arrivalAirport, departureTime, arrivalTime, seatNumber, gate, boardingGroup,
            planeModel, airline);
    }
}

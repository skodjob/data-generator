/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.legacy;

import io.skodjob.datagenerator.models.flights.Flight;
import io.skodjob.datagenerator.models.flights.FlightRecord;
import io.skodjob.datagenerator.models.flights.Passenger;
import net.datafaker.Faker;

import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for generating flight data using the Faker library.
 */
public class FlightsHandler {

    /**
     * Private constructor to make class static
     */
    private FlightsHandler() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "flights";

    /**
     * Generates flight data using the Faker library.
     *
     * @return the generated flight data as an Avro Object
     */
    public static Object generateData() {
        FlightRecord flightRecord = new FlightRecord();

        Passenger passenger = new Passenger();
        passenger.setId(FAKER.idNumber().valid());
        passenger.setName(FAKER.name().fullName());
        passenger.setPassportNumber(FAKER.regexify("[A-Z]{2}[0-9]{6}"));
        passenger.setNationality(FAKER.nation().nationality());

        Flight flight = new Flight();
        flight.setNumber(FAKER.regexify("[A-Z]{2}[0-9]{4}"));
        flight.setDepartureAirport(FAKER.aviation().airport());
        flight.setArrivalAirport(FAKER.aviation().airport());
        flight.setDepartureTime(FAKER.timeAndDate().future(1, TimeUnit.HOURS)
            .atOffset(ZoneOffset.UTC).toString());
        flight.setArrivalTime(FAKER.timeAndDate().future(2, TimeUnit.HOURS)
            .atOffset(ZoneOffset.UTC).toString());
        flight.setSeatNumber(FAKER.regexify("[A-Z][0-9]{2}"));
        flight.setGate(FAKER.regexify("[A-Z][0-9]{1,2}"));
        flight.setBoardingGroup(FAKER.options().option("A", "B", "C", "D", "E", "F"));
        flight.setPlaneModel(FAKER.aviation().aircraft());
        flight.setAirline(FAKER.aviation().airline());

        flightRecord.setPassenger(passenger);
        flightRecord.setFlight(flight);

        return flightRecord;
    }
}

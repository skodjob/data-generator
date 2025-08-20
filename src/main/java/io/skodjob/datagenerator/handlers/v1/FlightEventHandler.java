/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.v1;

import io.skodjob.datagenerator.handlers.AbstractHandler;
import io.skodjob.datagenerator.helpers.Utils;
import io.skodjob.datagenerator.models.v1.aviation.FlightEvent;
import io.skodjob.datagenerator.models.v1.aviation.FlightStatus;

import net.datafaker.Faker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates {@link FlightEvent} records (stateful – emits follow-up status updates).
 */
public final class FlightEventHandler extends AbstractHandler {

    /**
     * Template name announced to the CLI / REST interface.
     */
    public static final String TEMPLATE_NAME = "flight_event";

    private static final Faker FAKER = new Faker();
    private static final Map<String, FlightEvent> OPEN_FLIGHTS = new ConcurrentHashMap<>();

    // Probabilities for each state
    private static final int PROB_NEW = 65;
    private static final int PROB_TO_BOARD = 70;
    private static final int PROB_TO_TAXI = 80;
    private static final int PROB_TO_AIRBORNE = 95;

    private FlightEventHandler() {
    }

    /**
     * Public API – called by DataGenerator.
     *
     * @return new or updated event
     */
    public static FlightEvent generateData() {
        if (OPEN_FLIGHTS.isEmpty()) {
            return createNewFlight();
        }

        // This makes sure that OPEN_FLIGHTS won't be too big
        int pNew = Math.max(10, 100 * OPEN_EVENTS_MAX / (OPEN_FLIGHTS.size() + 1));

        return ThreadLocalRandom.current()
            .nextInt(100) < pNew
            ? createNewFlight()
            : updateExistingFlight();
    }

    private static FlightEvent createNewFlight() {
        Instant now = Instant.now();

        /* build flight keys */
        String airlineCode = FAKER.aviation()
            .flight("ICAO");
        String flightNumber = airlineCode + FAKER.number()
            .digits(4);
        LocalDate flightDate = LocalDate.now(ZoneOffset.UTC);
        String flightId = flightNumber + "-" + flightDate;

        /* pick a departure time anytime in the next 12 h */
        long depOffsetMinutes = ThreadLocalRandom.current()
            .nextLong(15, 12 * 60);
        Instant schedDep = now.plusSeconds(depOffsetMinutes * 60L);
        // Generate random duration of the flight
        int durationMin = getDuration();

        Instant schedArr = now.plusSeconds((depOffsetMinutes + durationMin) * 60L);

        FlightEvent event = FlightEvent.newBuilder()
            .setSchemaVersion(SCHEMA_VERSION_V1)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setSource(GENERATOR_SOURCE)
            .setIngestedTs(now)

            .setFlightId(flightId)
            .setAirline(airlineCode)
            .setOrigin(FAKER.aviation()
                .airport())
            .setDestination(FAKER.aviation()
                .airport())

            .setScheduledDepartureTs(schedDep)
            .setScheduledArrivalTs(schedArr)
            .setActualDepartureTs(null)
            .setActualArrivalTs(null)

            .setStatus(FlightStatus.SCHEDULED)
            .setDelayMinutes(null)

            .setGate(FAKER.regexify("[A-Z][0-9]{1,2}"))
            .setAircraftType(FAKER.aviation()
                .aircraft())
            .build();

        OPEN_FLIGHTS.put(flightId, event);
        return event;
    }


    private static FlightEvent updateExistingFlight() {
        List<String> keys = new ArrayList<>(OPEN_FLIGHTS.keySet());
        String id = keys.get(ThreadLocalRandom.current()
            .nextInt(keys.size()));
        FlightEvent prev = OPEN_FLIGHTS.get(id);

        FlightStatus next = nextStatus(prev.getStatus());
        Instant now = Instant.now();

        FlightEvent.Builder b = FlightEvent.newBuilder(prev)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setIngestedTs(now)
            .setStatus(next);

        switch (next) {
            case BOARDING -> { /* nothing extra */ }
            case TAXI -> { /* still ground */ }
            case AIRBORNE -> b.setActualDepartureTs(now);
            case LANDED -> b.setActualArrivalTs(now);
            case CANCELLED, DIVERTED -> {
                b.setDelayMinutes(ThreadLocalRandom.current()
                    .nextInt(10, 120));
            }
            default -> {
            }   // keep existing fields
        }

        FlightEvent updated = b.build();

        if (isTerminal(next)) {
            OPEN_FLIGHTS.remove(id);
        } else {
            OPEN_FLIGHTS.put(id, updated);
        }
        return updated;
    }

    /* ── status transitions ─────────────────────────────────────────────── */
    private static FlightStatus nextStatus(FlightStatus current) {
        int roll = ThreadLocalRandom.current()
            .nextInt(100);
        return switch (current) {
            case SCHEDULED -> (roll < PROB_TO_BOARD) ? FlightStatus.BOARDING : FlightStatus.CANCELLED;
            case BOARDING -> (roll < PROB_TO_TAXI) ? FlightStatus.TAXI : FlightStatus.CANCELLED;
            case TAXI -> (roll < PROB_TO_AIRBORNE) ? FlightStatus.AIRBORNE : FlightStatus.CANCELLED;
            case AIRBORNE -> (roll < 97) ? FlightStatus.LANDED : FlightStatus.DIVERTED;
            default -> current;  // already terminal
        };
    }

    private static boolean isTerminal(FlightStatus s) {
        return s == FlightStatus.LANDED || s == FlightStatus.CANCELLED || s == FlightStatus.DIVERTED;
    }

    private static int getDuration() {
        int roll = ThreadLocalRandom.current()
            .nextInt(100);
        if (roll < 60) {
            return Utils.rand(45, 120);
        } else if (roll < 90) {
            return Utils.rand(120, 300);
        } else {
            return Utils.rand(240, 600);
        }
    }
}

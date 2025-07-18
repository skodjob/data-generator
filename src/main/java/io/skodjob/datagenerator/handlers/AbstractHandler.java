/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

/**
 * Constants used across handlers and generator
 */
public abstract class AbstractHandler {
    /**
     * Generator source name for v1 events
     */
    public static final String GENERATOR_SOURCE = "datagen";

    /**
     * Schema version for v1
     */
    public static final int SCHEMA_VERSION_V1 = 1;

    /**
     * Max events opened without terminal state.
     * The mechanism works with probability so the size will be eventually higher, but it will stop at some point.
     * Feel free to override it inside the classes.
     */
    protected static final int OPEN_EVENTS_MAX = 10_000;
}

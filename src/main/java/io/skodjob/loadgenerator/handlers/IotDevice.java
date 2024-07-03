/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator.handlers;

import net.datafaker.Faker;

import java.util.Locale;

/**
 * This class is responsible for generating IoT device data using the Faker library.
 */
public class IotDevice {
    private static final Faker FAKER = new Faker();

    private static final String TYPE_LIGHT = "light";
    private static final String TYPE_BUTTON = "button";
    private static final String TYPE_THERMOMETER = "thermometer";
    private static final String TYPE_PLUG = "plug";
    private static final String TYPE_CUSTOM = "custom";
    private static final String TYPE_GATE = "gate";

    /**
     * Generates a random power state (on/off).
     *
     * @return a randomly generated power state
     */
    private static String generatePowerState() {
        return FAKER.options().option("on", "off");
    }

    /**
     * Generates a random power on behavior (on/off/toggle/previous).
     *
     * @return a randomly generated power on behavior
     */
    private static String generatePowerOnBehavior() {
        return FAKER.options().option("on", "off", "toggle", "previous");
    }

    /**
     * Generates a random vendor name.
     *
     * @return a randomly generated vendor name
     */
    private static String generateVendor() {
        return FAKER.options().option("ikea", "apple", "tasmota", "sencor", "amazon", "google", "xiaomi");
    }

    /**
     * Generates a random activity state (active/inactive/error).
     *
     * @return a randomly generated activity state
     */
    private static String generateActivityState() {
        return FAKER.options().option("active", "inactive", "error");
    }

    /**
     * Generates a JSON data section for the specified device type.
     *
     * @param type the type of the device
     * @return a JSON string representing the data section for the specified device type
     */
    private static String generateDataSection(String type) {
        switch (type) {
            case TYPE_LIGHT:
                return String.format("{\"power\": \"%s\", \"brightness\": %d, \"power_on_behavior\": \"%s\"}",
                    generatePowerState(), FAKER.number().numberBetween(0, 254), generatePowerOnBehavior());
            case TYPE_PLUG:
                return String.format("{\"power\": \"%s\", \"energy_current\": {\"state\": %.3f, \"unit\": \"A\"}, " +
                        "\"energy_today\": {\"state\": %.3f, \"unit\": \"kWh\"}}",
                    generatePowerState(), FAKER.number().randomDouble(3, 0, 1),
                    FAKER.number().randomDouble(3, 0, 10));
            case TYPE_BUTTON:
                return String.format("{\"power\": \"%s\", \"battery\": {\"value\": %d, \"unit\": \"%%\"}}",
                    generatePowerState(), FAKER.number().numberBetween(0, 100));
            case TYPE_THERMOMETER:
                return String.format("{\"temperature\": %.2f, \"humidity\": %.2f, " +
                        "\"battery\": {\"value\": %d, \"unit\": \"%%\"}}",
                    FAKER.number().randomDouble(2, -30, 40),
                    FAKER.number().randomDouble(2, 10, 90),
                    FAKER.number().numberBetween(0, 100));
            case TYPE_GATE:
                return String.format("{\"vendor\": \"%s\", \"state\": \"%s\"}",
                    generateVendor(), generateActivityState());
            default:
                return String.format("{\"info\": \"custom data\", \"state\": \"%s\"}", generateActivityState());
        }
    }

    /**
     * Generates IoT device data using the Faker library.
     *
     * @return the generated IoT device data as a JSON string
     */
    public static String generateData() {
        String ipv4 = FAKER.internet().ipV4Address();
        String mac = FAKER.internet().macAddress();
        String id = String.valueOf(FAKER.number().numberBetween(10000, 999999));
        String type = FAKER.options().option(TYPE_LIGHT, TYPE_BUTTON, TYPE_THERMOMETER,
            TYPE_PLUG, TYPE_CUSTOM, TYPE_GATE);
        String lastUpdate = String.valueOf(System.currentTimeMillis());
        int linkQuality = FAKER.number().numberBetween(0, 100);
        String data = generateDataSection(type);

        return String.format(Locale.US,
            "{\"IPV4\":\"%s\"," +
                "\"MAC\":\"%s\"," +
                "\"ID\":\"%s\"," +
                "\"TYPE\":\"%s\"," +
                "\"LAST_UPDATE\":\"%s\"," +
                "\"LINK_QUALITY\":%d," +
                "\"DATA\":%s}",
            ipv4, mac, id, type, lastUpdate, linkQuality, data);
    }
}

/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator.handlers;

import io.skodjob.loadgenerator.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class generates random IoT device data for use in templates.
 */
public class IotDevice {
    private static final Random RANDOM = new Random();

    private static final String STATE_ACTIVE = "active";
    private static final String STATE_INACTIVE = "inactive";
    private static final String STATE_ERROR = "error";
    private static final String STATE_ON = "on";
    private static final String STATE_OFF = "off";
    private static final List<String> ACTIVITY_STATES = Arrays.asList(STATE_ACTIVE, STATE_INACTIVE, STATE_ERROR);
    private static final List<String> POWER_STATES = Arrays.asList(STATE_ON, STATE_OFF);

    private static final String BEHAVIOR_ON = STATE_ON;
    private static final String BEHAVIOR_OFF = STATE_OFF;
    private static final String BEHAVIOR_TOGGLE = "toggle";
    private static final String BEHAVIOR_PREVIOUS = "previous";
    private static final List<String> BEHAVIORS = Arrays.asList(
        BEHAVIOR_ON, BEHAVIOR_OFF, BEHAVIOR_TOGGLE, BEHAVIOR_PREVIOUS);
    private static final List<String> VENDORS;

    private static final String TYPE_LIGHT = "light";
    private static final String TYPE_BUTTON = "button";
    private static final String TYPE_THERMOMETER = "thermometer";
    private static final String TYPE_PLUG = "plug";
    private static final String TYPE_CUSTOM = "custom";
    private static final String TYPE_GATE = "gate";

    private static final List<String> TYPES = Arrays.asList(TYPE_LIGHT, TYPE_BUTTON, TYPE_THERMOMETER,
        TYPE_PLUG, TYPE_CUSTOM, TYPE_GATE);

    static {
        try {
            VENDORS = Utils.loadData("/data/iot_vendors.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Default constructor
     */
    private IotDevice() {

    }

    /**
     * Generates a random IoT device type.
     *
     * @return A random IoT device type.
     */
    private static String generateType() {
        return TYPES.get(RANDOM.nextInt(TYPES.size()));
    }

    /**
     * Generates a random IPv4 address.
     *
     * @return A random IPv4 address.
     */
    private static String generateIPv4() {
        return RANDOM.nextInt(256) + "." + RANDOM.nextInt(256) + "." + RANDOM.nextInt(256) + "." + RANDOM.nextInt(256);
    }

    /**
     * Generates a random activity state.
     *
     * @return A random activity state.
     */
    private static String generateActivityState() {
        return ACTIVITY_STATES.get(RANDOM.nextInt(ACTIVITY_STATES.size()));
    }

    /**
     * Generates a random power state.
     *
     * @return A random power state.
     */
    private static String generatePowerState() {
        return POWER_STATES.get(RANDOM.nextInt(POWER_STATES.size()));
    }

    /**
     * Generates a random MAC address.
     *
     * @return A random MAC address.
     */
    private static String generateMacAddress() {
        byte[] macAddr = new byte[6];
        RANDOM.nextBytes(macAddr);

        macAddr[0] = (byte) (macAddr[0] & (byte) 254);

        StringBuilder mac = new StringBuilder(18);
        for (byte b : macAddr) {
            if (mac.length() > 0) mac.append(":");
            mac.append(String.format("%02x", b));
        }
        return mac.toString();
    }

    /**
     * Generates a random ID.
     *
     * @return A random ID.
     */
    private static String generateId() {
        return String.valueOf(RANDOM.nextInt(1000000));
    }

    /**
     * Generates the current timestamp.
     *
     * @return The current timestamp.
     */
    private static String generateLastUpdate() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * Generates a random link quality (0-100).
     *
     * @return A random link quality.
     */
    private static String generateLinkQuality() {
        return String.format("%s", RANDOM.nextInt(101));
    }

    /**
     * Generates data for a specific IoT device type.
     *
     * @param type The type of the IoT device.
     * @return The generated data for the IoT device.
     */
    private static String generateData(String type) {
        switch (type) {
            case TYPE_LIGHT:
                return String.format("{\"power\": \"%s\", \"brightness\": %d, \"power_on_behavior\": \"%s\"}",
                    generatePowerState(), RANDOM.nextInt(255), generatePowerOnBehavior());
            case TYPE_PLUG:
                return String.format("{\"power\": \"%s\", \"energy_current\": {\"state\": %.3f, \"unit\": \"A\"}, " +
                        "\"energy_today\": {\"state\": %.3f, \"unit\": \"kWh\"}}",
                    generatePowerState(), RANDOM.nextDouble(), RANDOM.nextDouble() * 10);
            case TYPE_BUTTON:
                return String.format("{\"power\": \"%s\", \"battery\": {\"value\": %d, \"unit\": \"%%\"}}",
                    generatePowerState(), RANDOM.nextInt(101));
            case TYPE_THERMOMETER:
                return String.format("{\"temperature\": %.2f, \"humidity\": %.2f, " +
                        "\"battery\": {\"value\": %d, \"unit\": \"%%\"}}",
                    RANDOM.nextDouble() * 70 - 30, RANDOM.nextDouble() * 80 + 10, RANDOM.nextInt(101));
            case TYPE_GATE:
                return String.format("{\"vendor\": \"%s\", \"state\": \"%s\"}",
                    generateVendor(), generateActivityState());
            default:
                return String.format("{\"info\": \"custom data\", \"state\": \"%s\"}", generateActivityState());
        }
    }

    /**
     * Generates a random power-on behavior.
     *
     * @return A random power-on behavior.
     */
    private static String generatePowerOnBehavior() {
        return BEHAVIORS.get(RANDOM.nextInt(BEHAVIORS.size()));
    }

    /**
     * Generates a random vendor.
     *
     * @return A random vendor.
     */
    private static String generateVendor() {
        return VENDORS.get(RANDOM.nextInt(VENDORS.size()));
    }

    /**
     * Fills the template with random IoT device data.
     *
     * @param template The template to be filled.
     * @return The filled template.
     */
    public static String fillTemplate(String template) {
        String type = generateType();
        template = template.replace("@ipv4", "\"%s\"".formatted(generateIPv4()));
        template = template.replace("@mac", "\"%s\"".formatted(generateMacAddress()));
        template = template.replace("@id", "\"%s\"".formatted(generateId()));
        template = template.replace("@type", "\"" + type + "\"");
        template = template.replace("@last_update", "\"%s\"".formatted(generateLastUpdate()));
        template = template.replace("@link_quality", generateLinkQuality());
        template = template.replace("@data", generateData(type));

        return template;
    }
}

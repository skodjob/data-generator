/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.legacy;

import io.skodjob.datagenerator.models.iotdevice.Battery;
import io.skodjob.datagenerator.models.iotdevice.ButtonData;
import io.skodjob.datagenerator.models.iotdevice.CustomData;
import io.skodjob.datagenerator.models.iotdevice.EnergyCurrent;
import io.skodjob.datagenerator.models.iotdevice.EnergyToday;
import io.skodjob.datagenerator.models.iotdevice.GateData;
import io.skodjob.datagenerator.models.iotdevice.IotDevice;
import io.skodjob.datagenerator.models.iotdevice.LightData;
import io.skodjob.datagenerator.models.iotdevice.PlugData;
import io.skodjob.datagenerator.models.iotdevice.ThermometerData;
import net.datafaker.Faker;

/**
 * This class is responsible for generating IoT device data using the Faker library.
 */
public class IotDeviceHandler {

    /**
     * Private constructor to make class static
     */
    private IotDeviceHandler() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "iot_device";

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
     * Generates IoT device data using the Faker library.
     *
     * @return the generated IoT device data as an Avro Object
     */
    public static Object generateData() {
        IotDevice iotDevice = new IotDevice();

        iotDevice.setIPV4(FAKER.internet().ipV4Address());
        iotDevice.setMAC(FAKER.internet().macAddress());
        iotDevice.setID(String.valueOf(FAKER.number().numberBetween(10000, 999999)));
        iotDevice.setTYPE(FAKER.options().option(TYPE_LIGHT, TYPE_BUTTON, TYPE_THERMOMETER,
            TYPE_PLUG, TYPE_CUSTOM, TYPE_GATE));
        iotDevice.setLASTUPDATE(String.valueOf(System.currentTimeMillis()));
        iotDevice.setLINKQUALITY(FAKER.number().numberBetween(0, 100));

        switch (iotDevice.getTYPE().toString()) {
            case TYPE_LIGHT:
                LightData lightData = new LightData();
                lightData.setPower(generatePowerState());
                lightData.setPowerOnBehavior(generatePowerOnBehavior());
                lightData.setBrightness(FAKER.number().numberBetween(0, 254));

                iotDevice.setDATA(lightData);
                break;
            case TYPE_PLUG:
                PlugData plugData = new PlugData();
                plugData.setPower(generatePowerState());

                EnergyCurrent energyCurrent = new EnergyCurrent();
                energyCurrent.setState((float) FAKER.number().randomDouble(3, 0, 1));
                energyCurrent.setUnit("A");

                plugData.setEnergyCurrent(energyCurrent);

                EnergyToday energyToday = new EnergyToday();
                energyToday.setState((float) FAKER.number().randomDouble(3, 0, 10));
                energyToday.setUnit("kWh");

                plugData.setEnergyToday(energyToday);

                iotDevice.setDATA(plugData);
                break;
            case TYPE_BUTTON:
                ButtonData buttonData = new ButtonData();
                buttonData.setPower(generatePowerState());
                buttonData.setBattery(new Battery(FAKER.number().numberBetween(0, 100), "%"));

                iotDevice.setDATA(buttonData);
                break;
            case TYPE_THERMOMETER:
                ThermometerData thermometerData = new ThermometerData();
                thermometerData.setTemperature((float) FAKER.number().randomDouble(2, -30, 40));
                thermometerData.setHumidity((float) FAKER.number().randomDouble(2, 10, 90));
                thermometerData.setBattery(new Battery(FAKER.number().numberBetween(0, 100), "%"));

                iotDevice.setDATA(thermometerData);
                break;
            case TYPE_GATE:
                GateData gateData = new GateData(generateVendor(), generateActivityState());

                iotDevice.setDATA(gateData);
                break;
            default:
                CustomData customData = new CustomData("custom data", generateActivityState());

                iotDevice.setDATA(customData);
                break;
        }

        return iotDevice;
    }
}

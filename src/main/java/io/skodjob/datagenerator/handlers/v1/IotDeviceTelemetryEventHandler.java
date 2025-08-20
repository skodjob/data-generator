/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.v1;

import io.skodjob.datagenerator.handlers.AbstractHandler;
import io.skodjob.datagenerator.helpers.Utils;
import io.skodjob.datagenerator.models.v1.iot.CustomData;
import io.skodjob.datagenerator.models.v1.iot.GateData;
import io.skodjob.datagenerator.models.v1.iot.LightData;
import io.skodjob.datagenerator.models.v1.iot.PlugData;
import io.skodjob.datagenerator.models.v1.iot.DeviceTelemetry;
import io.skodjob.datagenerator.models.v1.iot.GeoPoint;
import io.skodjob.datagenerator.models.v1.iot.ButtonData;
import io.skodjob.datagenerator.models.v1.iot.ButtonState;
import io.skodjob.datagenerator.models.v1.iot.DeviceStatus;
import io.skodjob.datagenerator.models.v1.iot.GateState;
import io.skodjob.datagenerator.models.v1.iot.LightState;
import io.skodjob.datagenerator.models.v1.iot.PowerState;
import io.skodjob.datagenerator.models.v1.iot.SensorType;
import io.skodjob.datagenerator.models.v1.iot.ThermoData;
import net.datafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Emits new devices plus follow-up telemetry with union payload.
 */
public final class IotDeviceTelemetryEventHandler extends AbstractHandler {

    /**
     * Template name announced to the CLI / REST interface.
     */
    public static final String TEMPLATE_NAME = "iot_device_telemetry";

    private static final Faker FAKER = new Faker();
    private static final Map<String, DeviceTelemetry> LIVE = new ConcurrentHashMap<>();
    private static final int PROB_NEW_DEVICE = 35;   // %

    private IotDeviceTelemetryEventHandler() {
    }

    /**
     * Returns a newly generated {@link DeviceTelemetry}.
     *
     * @return new or updated event
     */
    public static DeviceTelemetry generateData() {
        if (LIVE.isEmpty() || Utils.rand(100) < PROB_NEW_DEVICE) {
            return spawnNewDevice();
        }
        return updateExisting();
    }

    /* ───────────────────────── new device ───────────────────────── */
    private static DeviceTelemetry spawnNewDevice() {
        Instant now = Instant.now();
        SensorType st = randomSensor();

        DeviceTelemetry event = DeviceTelemetry.newBuilder()
            .setSchemaVersion(SCHEMA_VERSION_V1)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setSource(GENERATOR_SOURCE)
            .setIngestedTs(now)

            .setDeviceId("dev-" + FAKER.number()
                .digits(6))
            .setIpv4(FAKER.internet()
                .ipV4Address())
            .setMac(FAKER.internet()
                .macAddress())

            .setGeo(randomGeo())
            .setSensorType(st)
            .setBatteryPct(Utils.rand(50, 100))
            .setReadingTs(now)
            .setStatus(DeviceStatus.OK)
            .setFirmware("v" + Utils.rand(1, 3) + "." + Utils.rand(0, 9))

            .setPayload(makePayload(st))
            .build();

        LIVE.put(event.getDeviceId()
            .toString(), event);
        return event;
    }

    /* ───────────────────────── update ───────────────────────── */
    private static DeviceTelemetry updateExisting() {
        List<String> ids = new ArrayList<>(LIVE.keySet());
        String id = ids.get(Utils.rand(ids.size()));
        DeviceTelemetry prev = LIVE.get(id);

        Instant now = Instant.now();
        int battery = Math.max(prev.getBatteryPct() - Utils.rand(0, 2), 0);
        DeviceStatus stat = battery < 10 ? DeviceStatus.FAIL :
            battery < 20 ? DeviceStatus.WARN : DeviceStatus.OK;

        DeviceTelemetry updated = DeviceTelemetry.newBuilder(prev)
            .setEventId(UUID.fromString(UUID.randomUUID()
                .toString()))
            .setIngestedTs(now)
            .setBatteryPct(battery)
            .setStatus(stat)
            .setReadingTs(now)
            .setGeo(jitter(prev.getGeo()))
            .setPayload(makePayload(prev.getSensorType()))   // fresh reading
            .build();

        LIVE.put(id, updated);
        return updated;
    }

    /* ───────────────────────── payload builders ───────────────────────── */
    private static Object makePayload(SensorType t) {
        return switch (t) {
            case BUTTON -> ButtonData.newBuilder()
                .setButtonState(Utils.randBool() ? ButtonState.PRESSED : ButtonState.RELEASED)
                .build();
            case LIGHT -> LightData.newBuilder()
                .setLightState(Utils.randBool() ? LightState.ON : LightState.OFF)
                .setBrightnessPct(Utils.randBool() ? Utils.rand(0, 100) : null)
                .build();
            case GATE -> GateData.newBuilder()
                .setGateState(Utils.randBool() ? GateState.OPEN : GateState.CLOSED)
                .setPositionPct(Utils.rand(0, 100))
                .build();
            case PLUG -> PlugData.newBuilder()
                .setPowerState(Utils.randBool() ? PowerState.ON : PowerState.OFF)
                .setWatts(Utils.randBool() ? Utils.randDouble(5, 1500) : null)
                .build();
            case THERMOMETER -> ThermoData.newBuilder()
                .setTemperatureC(Utils.randDouble(15, 35))
                .setHumidityPct(Utils.randBool() ? Utils.randDouble(20, 90) : null)
                .build();
            case CUSTOM -> CustomData.newBuilder()
                .setBlob("{\"value\":" + Utils.rand(0, 1000) + "}")
                .build();
        };
    }

    /* ───────────────────────── helpers ───────────────────────── */
    private static SensorType randomSensor() {
        return FAKER.options()
            .option(SensorType.values());
    }

    private static GeoPoint randomGeo() {
        return GeoPoint.newBuilder()
            .setLat(buf(coord(-90, 90)))
            .setLon(buf(coord(-180, 180)))
            .build();
    }

    private static GeoPoint jitter(GeoPoint g) {
        double lat = toDec(g.getLat()).doubleValue();
        double lon = toDec(g.getLon()).doubleValue();
        return GeoPoint.newBuilder()
            .setLat(buf(coord(lat - 0.01, lat + 0.01)))
            .setLon(buf(coord(lon - 0.01, lon + 0.01)))
            .build();
    }

    private static BigDecimal coord(double min, double max) {
        return BigDecimal.valueOf(Utils.randDouble(min, max))
            .setScale(6, RoundingMode.HALF_UP);
    }

    private static ByteBuffer buf(BigDecimal v) {
        return ByteBuffer.wrap(v.unscaledValue()
            .toByteArray());
    }

    private static BigDecimal toDec(ByteBuffer b) {
        return new BigDecimal(new java.math.BigInteger(b.array()), 6);
    }
}

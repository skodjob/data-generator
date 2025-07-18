/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Static class for helper utils
 */
public class Utils {

    /**
     * Get random int from 0 to bound value
     *
     * @param bound upper maximum
     * @return random int
     */
    public static int rand(int bound) {
        return ThreadLocalRandom.current()
            .nextInt(bound);
    }

    /**
     * Get random boolean
     *
     * @return random boolean
     */
    public static boolean randBool() {
        return ThreadLocalRandom.current()
            .nextBoolean();
    }

    /**
     * Get random int from min to max value
     *
     * @param min minimal value
     * @param max maximal value
     * @return random int from the range
     */
    public static int rand(int min, int max) {
        return ThreadLocalRandom.current()
            .nextInt(min, max + 1);
    }

    /**
     * Get random double from min to max value
     *
     * @param min minimal value
     * @param max maximal value
     * @return random double from the range
     */
    public static double randDouble(double min, double max) {
        return ThreadLocalRandom.current()
            .nextDouble(min, max);
    }

    /**
     * Transfer int into big decimal as a representation of money
     *
     * @param val amount of money
     * @return amount of money in BigDecimal
     */
    public static BigDecimal money(int val) {
        return BigDecimal.valueOf(val)
            .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Transfer long into big decimal as a representation of money
     *
     * @param val amount of money
     * @return amount of money in BigDecimal
     */
    public static BigDecimal money(long val) {
        return BigDecimal.valueOf(val)
            .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Randomly pick item from the list
     *
     * @param list passed list
     * @param <T>  list type
     * @return random item
     */
    public static <T> T pick(List<T> list) {
        return list.get(rand(list.size()));
    }

    /**
     * Get random amount from 10 to 1000
     *
     * @return random amount from 10 to 1000
     */
    public static BigDecimal randomAmount() {
        double raw = ThreadLocalRandom.current()
            .nextDouble(10.00, 1000.00);
        return BigDecimal.valueOf(raw)
            .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Transfer BigDecimal into ByteBuffer
     *
     * @param value BigDecimal value
     * @return ByteBUtter
     */
    public static ByteBuffer decimalToBuffer(BigDecimal value) {
        return ByteBuffer.wrap(value.unscaledValue()
            .toByteArray());
    }
}

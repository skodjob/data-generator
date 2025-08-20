/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simple carrier for IP, country, continent plus helper utilities.
 *
 * @param ipAddress IP address
 * @param country   name of the country
 * @param continent name of the continent
 */
public record IpInfo(String ipAddress, String country, String continent) {

    private static final Map<String, String> COUNTRY2CONT = Map.ofEntries(
        Map.entry("US", "NA"), Map.entry("CA", "NA"), Map.entry("MX", "NA"),
        Map.entry("BR", "SA"), Map.entry("AR", "SA"), Map.entry("CL", "SA"),
        Map.entry("GB", "EU"), Map.entry("FR", "EU"), Map.entry("DE", "EU"),
        Map.entry("CZ", "EU"), Map.entry("PL", "EU"), Map.entry("NL", "EU"),
        Map.entry("CN", "AS"), Map.entry("JP", "AS"), Map.entry("IN", "AS"),
        Map.entry("SG", "AS"), Map.entry("AU", "OC"), Map.entry("NZ", "OC"),
        Map.entry("ZA", "AF"), Map.entry("NG", "AF"), Map.entry("EG", "AF")
    );

    private static final Map<String, String[]> CONT2IP = Map.of(
        "NA", new String[]{"3.22.44.", "18.116.0.", "23.45.67.", "34.102.128.", "44.128.16.",
            "52.14.0.", "54.236.1.", "63.142.248.", "96.30.0.", "172.98.67."},
        "SA", new String[]{"181.0.0.", "181.30.84.", "186.33.128.", "187.19.0.", "190.57.32.",
            "190.216.0.", "191.96.0.", "138.121.0.", "45.162.224.", "170.0.48."},
        "EU", new String[]{"62.210.0.", "77.88.55.", "80.90.160.", "83.97.20.", "91.121.0.",
            "109.68.160.", "151.101.64.", "185.34.15.", "213.160.0.", "217.146.0."},
        "AS", new String[]{"43.224.10.", "45.64.64.", "49.44.0.", "60.248.0.", "61.8.0.",
            "101.32.0.", "103.25.56.", "117.18.232.", "120.29.0.", "150.242.0."},
        "OC", new String[]{"13.238.0.", "43.245.160.", "52.62.0.", "103.6.212.", "110.44.116.",
            "116.90.72.", "118.97.0.", "139.180.128.", "202.68.64.", "203.23.237."},
        "AF", new String[]{"41.77.16.", "41.204.0.", "41.231.32.", "41.243.96.", "102.132.96.",
            "102.165.48.", "154.66.0.", "156.0.0.", "197.210.0.", "197.248.0."}
    );

    /**
     * Random ISO country code.
     *
     * @return ISO country code
     */
    public static String randomCountry() {
        List<String> cc = new ArrayList<>(COUNTRY2CONT.keySet());
        return cc.get(Utils.rand(cc.size()));
    }

    /**
     * Random IP inside the continent of the given country.
     *
     * @param country currently used country
     * @return IP from map for specific country
     */
    public static String ipForCountry(String country) {
        String cont = COUNTRY2CONT.getOrDefault(country, "EU");
        String[] pool = CONT2IP.get(cont);
        return pool[Utils.rand(pool.length)] + Utils.rand(1, 254);
    }

    /**
     * Get whole random IP Info based on connections between IPs, countries, and continents
     *
     * @return generated IP info
     */
    public static IpInfo randomIpInfo() {
        String c = randomCountry();
        return new IpInfo(ipForCountry(c), c, COUNTRY2CONT.get(c));
    }

    /**
     * Get different IP from another continent based on passed IP
     *
     * @param fromCountry currently used IP
     * @return IP from different continent
     */
    public static String fakeIpDifferentContinent(String fromCountry) {
        return differentContinentIpInfo(fromCountry).ipAddress();
    }

    /**
     * Get IP Info for different continent than passed IP is based on
     *
     * @param fromCountry currently used IP
     * @return IP Info
     */
    public static IpInfo differentContinentIpInfo(String fromCountry) {
        String fromCont = COUNTRY2CONT.getOrDefault(fromCountry, "EU");

        List<String> candidates = COUNTRY2CONT.entrySet()
            .stream()
            .filter(e -> !e.getValue()
                .equals(fromCont))
            .map(Map.Entry::getKey)
            .toList();

        String newCountry = candidates.get(Utils.rand(candidates.size()));
        return new IpInfo(ipForCountry(newCountry),
            newCountry,
            COUNTRY2CONT.get(newCountry));
    }

    /**
     * Get IP for different country
     *
     * @param fromCountry IP from currently used country
     * @return IP for different country
     */
    public static String fakeIpDifferentCountry(String fromCountry) {
        return differentCountryIpInfo(fromCountry).ipAddress();
    }

    /**
     * Get IP Info for different country than passed IP is based on
     *
     * @param fromCountry currently used IP
     * @return IP Info
     */
    public static IpInfo differentCountryIpInfo(String fromCountry) {
        List<String> pool = new ArrayList<>(COUNTRY2CONT.keySet());
        pool.remove(fromCountry);

        String newCountry = pool.get(Utils.rand(pool.size()));
        return new IpInfo(ipForCountry(newCountry), newCountry,
            COUNTRY2CONT.get(newCountry));
    }
}

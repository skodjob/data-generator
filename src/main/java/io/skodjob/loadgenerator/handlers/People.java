/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.loadgenerator.handlers;

import io.skodjob.loadgenerator.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * This class generates random people-related data for use in templates.
 */
public class People {
    private static final Random RANDOM = new Random();

    private static final List<String> NAMES_F;
    private static final List<String> NAMES_M;
    private static final List<String> SURNAMES;
    private static final List<String> COMPANIES;
    private static final List<String> EMAIL_PROVIDERS;

    private static final String MALE = "Male";
    private static final String FEMALE = "Female";

    static {
        try {
            NAMES_F = Utils.loadData("/data/nameF.txt");
            NAMES_M = Utils.loadData("/data/nameM.txt");
            SURNAMES = Utils.loadData("/data/surnames.txt");
            COMPANIES = Utils.loadData("/data/companies.txt");
            EMAIL_PROVIDERS = Utils.loadData("/data/mail_provider.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Default constructor
     */
    private People() {

    }

    /**
     * Generates a random male first name.
     *
     * @return A random male first name.
     */
    public static String generateMaleFirstName() {
        return NAMES_M.get(RANDOM.nextInt(NAMES_M.size()));
    }

    /**
     * Generates a random female first name.
     *
     * @return A random female first name.
     */
    public static String generateFemaleFirstName() {
        return NAMES_F.get(RANDOM.nextInt(NAMES_F.size()));
    }

    /**
     * Generates a random surname.
     *
     * @return A random surname.
     */
    public static String generateSurname() {
        return SURNAMES.get(RANDOM.nextInt(SURNAMES.size()));
    }

    /**
     * Generates a random company name.
     *
     * @return A random company name.
     */
    public static String generateCompany() {
        return COMPANIES.get(RANDOM.nextInt(COMPANIES.size()));
    }

    /**
     * Generates a random email address.
     *
     * @param name    The first name to be included in the email address.
     * @param surname The surname to be included in the email address.
     * @param company The company to be included in the email address.
     * @return A random email address.
     */
    public static String generateEmail(String name, String surname, String company) {
        return "%s.%s@%s".formatted(name, surname, company);
    }

    /**
     * Generates a random age between 18 and 60.
     *
     * @return A random age between 18 and 60.
     */
    public static String generateAge() {
        return String.valueOf(18 + RANDOM.nextInt(43));  // Generate age between 18 and 60
    }

    /**
     * Generates a random Social Security Number (SSN).
     *
     * @return A random SSN.
     */
    public static String generateSSN() {
        return String.format("%03d-%02d-%04d",
            RANDOM.nextInt(1000),
            RANDOM.nextInt(100),
            RANDOM.nextInt(10000));
    }

    /**
     * Generates a random hourly rate between $10.00 and $50.00.
     *
     * @return A random hourly rate.
     */
    public static String generateHourlyRate() {
        // Generate hourly rate between $10.00 and $50.00
        return String.format("%.2f", 10 + (RANDOM.nextDouble() * 40));
    }

    /**
     * Generates a random gender.
     *
     * @return A random gender.
     */
    public static String generateGender() {
        return RANDOM.nextBoolean() ? MALE : FEMALE;
    }

    /**
     * Generates a random employee ID.
     *
     * @return A random employee ID.
     */
    public static String generateEmployeeId() {
        return String.format("%s", (1000 + RANDOM.nextInt(900000)));
    }

    /**
     * Generates a random email provider.
     *
     * @return A random email provider.
     */
    public static String generateEmailProvider() {
        return EMAIL_PROVIDERS.get(RANDOM.nextInt(EMAIL_PROVIDERS.size()));
    }

    /**
     * Fills the template with random people-related data.
     *
     * @param template The template to be filled.
     * @return The filled template.
     */
    public static String fillTemplate(String template) {
        String gender = generateGender();
        String name;
        if (gender.equals(MALE)) {
            name = generateMaleFirstName();
        } else {
            name = generateFemaleFirstName();
        }
        String surname = generateSurname();
        String company = generateCompany();

        template = template.replace("@employee_id", "\"%s\"".formatted(generateEmployeeId()));
        template = template.replace("@surname", "\"%s\"".formatted(surname));
        template = template.replace("@name", "\"%s\"".formatted(name));
        template = template.replace("@age", generateAge());
        template = template.replace("@ssn", "\"%s\"".formatted(generateSSN()));
        template = template.replace("@hourly_rate", generateHourlyRate());
        template = template.replace("@gender", "\"%s\"".formatted(gender));
        template = template.replace("@email", "\"%s\"".formatted(generateEmail(name, surname,
            generateEmailProvider()).toLowerCase(Locale.US)));
        template = template.replace("@company", "\"%s\"".formatted(company));

        return template;
    }
}

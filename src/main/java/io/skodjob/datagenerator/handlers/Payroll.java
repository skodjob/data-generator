/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers;

import net.datafaker.Faker;

import java.util.Locale;

/**
 * This class generates random people-related data for use in templates.
 */
public class Payroll {

    /**
     * Private constructor to make class static
     */
    private Payroll() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "payroll";

    /**
     * Generates payroll employee data using the Faker library.
     *
     * @return the generated payroll employee data as a JSON string
     */
    public static String generateData() {
        String employeeId = String.valueOf(FAKER.number().numberBetween(10000, 999999));
        String firstName = FAKER.name().firstName();
        String lastName = FAKER.name().lastName();
        int age = FAKER.number().numberBetween(18, 60);
        String ssn = FAKER.idNumber().ssnValid();
        double hourlyRate = FAKER.number().randomDouble(2, 10, 50);
        String gender = FAKER.demographic().sex();
        String email = FAKER.internet().emailAddress();
        String company = FAKER.company().name();

        return String.format(Locale.US,
            "{\"employee_id\":\"%s\"," +
                "\"first_name\":\"%s\"," +
                "\"last_name\":\"%s\"," +
                "\"age\":%d," +
                "\"ssn\":\"%s\"," +
                "\"hourly_rate\":%.2f," +
                "\"gender\":\"%s\"," +
                "\"email\":\"%s\"," +
                "\"company\":\"%s\"}",
            employeeId, firstName, lastName, age, ssn, hourlyRate, gender, email, company);
    }
}

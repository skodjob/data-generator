/*
 * Copyright Skodjob authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.skodjob.datagenerator.handlers.legacy;

import io.skodjob.datagenerator.models.payroll.Employee;
import net.datafaker.Faker;

/**
 * This class generates random people-related data for use in templates.
 */
public class PayrollHandler {

    /**
     * Private constructor to make class static
     */
    private PayrollHandler() {

    }

    private static final Faker FAKER = new Faker();

    /**
     * Template name used in the generator
     */
    public static final String TEMPLATE_NAME = "payroll";

    /**
     * Generates payroll employee data using the Faker library.
     *
     * @return the generated payroll employee data as an Avro Object
     */
    public static Object generateData() {
        Employee employee = new Employee();

        employee.setEmployeeId(String.valueOf(FAKER.number().numberBetween(10000, 999999)));
        employee.setFirstName(FAKER.name().firstName());
        employee.setLastName(FAKER.name().lastName());
        employee.setAge(FAKER.number().numberBetween(18, 60));
        employee.setSsn(FAKER.idNumber().ssnValid());
        employee.setHourlyRate((float) FAKER.number().randomDouble(2, 10, 50));
        employee.setGender(FAKER.demographic().sex());
        employee.setEmail(FAKER.internet().emailAddress());
        employee.setCompany(FAKER.company().name());

        return employee;
    }
}

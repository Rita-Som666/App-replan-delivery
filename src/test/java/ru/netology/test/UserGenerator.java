package ru.netology.test;


import com.github.javafaker.Faker;

import java.util.Locale;

public class UserGenerator {
    private final Faker faker;

    public UserGenerator() {
        this.faker = new Faker(new Locale("ru"));
    }

    private static String randomCity() {
        RussianAdminCenters centers = new RussianAdminCenters();
        return centers.cities();
    }

    private static String randomName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    private static String randomPhone() {
        Faker faker = new Faker();
        return faker.expression("+7#######");
    }

    public User generateUser() {
        return new User(randomCity(), randomName(), randomPhone());
    }

}

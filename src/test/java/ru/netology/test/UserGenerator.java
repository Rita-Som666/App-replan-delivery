package ru.netology.test;


import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

import static ru.netology.test.RussianAdminCenters.cities;

@Value
public class UserGenerator {
    Faker faker;

    private UserGenerator() {
        this.faker = new Faker(new Locale("ru"));
    }

    private static String randomCity() {

        return cities();
    }

    private static String randomName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    private static String randomPhone() {
        Faker faker = new Faker();
        return faker.expression("+7#######");
    }

    public static User generateUser() {
        return new User(randomCity(), randomName(), randomPhone());
    }

}

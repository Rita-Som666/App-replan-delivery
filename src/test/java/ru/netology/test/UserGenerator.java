package ru.netology.test;


import com.github.javafaker.Faker;

import java.util.Locale;

public class UserGenerator {
    private final Faker faker;

    public UserGenerator() {
        this.faker = new Faker(new Locale("ru"));
    }

    public User generateUser() {
        return new User();
    }

}

package ru.netology.test;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    Faker faker = new Faker(new Locale("ru"));
    RussianAdminCenters centers = new RussianAdminCenters();
    private final String city = centers.cities();
    private final String name = faker.name().fullName();
    private final String phone = faker.expression("+7#######");

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}


package ru.netology.test;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Faker faker = new Faker();
    RussianAdminCenters centers = new RussianAdminCenters();
private final String city = centers.cities();
private final String name = faker.name().fullName();
private final String phone = faker.expression("+7#######");
}


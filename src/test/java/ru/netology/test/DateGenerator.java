package ru.netology.test;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Value
public class DateGenerator {
    private DateGenerator() {
    }


    public static java.lang.String getDate(int plus) {
        return LocalDate.now().plusDays(plus).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
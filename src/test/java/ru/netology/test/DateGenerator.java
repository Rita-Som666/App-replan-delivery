package ru.netology.test;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateGenerator {

    public String localDatePlusThree() {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String localDatePlusFour() {
        return LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String localDatePlusTwo() {
        return LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
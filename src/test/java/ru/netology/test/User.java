package ru.netology.test;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String city;
    private String name;
    private String phone;

}


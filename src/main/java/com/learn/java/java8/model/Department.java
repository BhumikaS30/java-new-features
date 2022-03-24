package com.learn.java.java8.model;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Department {

    CSE("Computer Science Engineering");

    private final String name;

    public static Department fromName(String name) {

        return Stream.of(Department.values())
                     .filter(dealType -> dealType.name.equals(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(
                         "Invalid value for Department : " + name));
    }
}

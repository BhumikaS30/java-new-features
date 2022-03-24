package com.learn.java.java8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Customer {

    private Long id;
    private String name;
    private Integer tier;
}

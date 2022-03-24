package com.learn.java.java8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Employee {

    private String name;
    private String empId;
    private Department department;
    private String jobTitle;
    private Double salary;

}

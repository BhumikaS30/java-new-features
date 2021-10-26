package com.learn.java.java8;

import java.util.function.BiFunction;

public class Java8Features {

    public static void saySomething() {
        System.out.println("Hello, this is static method.");
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {

        //Functional Interface Instantiation
        Print<Integer> printable = (a, b) -> System.out.println("The values are " + a + " " + b);
        printable.apply(10, 20);

        // Referring static method
        BiFunction<Integer, Integer, Integer> adder = Java8Features::add;
        // Calling interface method
        int result = adder.apply(10, 20);
        System.out.println("Result -> " + result);

        // Referring static method
        Sayable sayable = Java8Features::saySomething;
        // Calling interface method
        sayable.say();

        //Default & static methods with diamond problem
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

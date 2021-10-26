package com.learn.java.java8;

public class Car implements Vehicle, FourWheeler{

    /**
     * This method has to be overridden to resolve
     * the ambiguity for the famous diamond problem !
     */
    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("I am a car!");
    }
}

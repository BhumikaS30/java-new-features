package com.learn.java.java8;

@FunctionalInterface
public interface Print<T> {
    void apply(T a, T b);
}

package com.solvd.hospital.interfaces;

@FunctionalInterface
public interface Calculable<T>{
    // for lambdas

    int calculate(T t);

}

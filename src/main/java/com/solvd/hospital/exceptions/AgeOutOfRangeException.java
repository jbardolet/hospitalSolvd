package com.solvd.hospital.exceptions;

public class AgeOutOfRangeException extends NumberFormatException{

    public AgeOutOfRangeException() {
    }

    public AgeOutOfRangeException(String s) {
        super(s);
    }
}

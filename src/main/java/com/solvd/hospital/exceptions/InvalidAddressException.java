package com.solvd.hospital.exceptions;

public class InvalidAddressException extends IllegalArgumentException{
    public InvalidAddressException() {
    }

    public InvalidAddressException(String s) {
        super(s);
    }
}

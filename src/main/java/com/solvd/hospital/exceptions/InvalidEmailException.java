package com.solvd.hospital.exceptions;

public class InvalidEmailException extends IllegalArgumentException{
    public InvalidEmailException() {
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}

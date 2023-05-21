package com.solvd.hospital.exceptions;

public class InvalidPhoneNumberException extends IllegalArgumentException{
    public InvalidPhoneNumberException() {
    }

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
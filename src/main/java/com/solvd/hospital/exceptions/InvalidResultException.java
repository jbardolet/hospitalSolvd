package com.solvd.hospital.exceptions;

public class InvalidResultException extends Exception{
    public InvalidResultException() {
    }

    public InvalidResultException(String message) {
        super(message);
    }

    public InvalidResultException(String message, Throwable cause) {
        super(message, cause);
    }
}

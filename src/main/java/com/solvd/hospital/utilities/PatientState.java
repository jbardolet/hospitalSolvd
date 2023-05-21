package com.solvd.hospital.utilities;

public enum PatientState {
    TREATED("If your value is less than 5", 5),
    HOSPITALISED("if your value is equal or greater than 5 and less than 12", 12),
    DEAD("if your value is greater than or equal to 12", 12);

    private String description;
    private int value;

    PatientState(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}

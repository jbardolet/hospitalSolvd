package com.solvd.hospital.utilities;

public enum HospitalNumbers {
    NUM_BED (50),
    NUM_LAB(3),
    NUM_OR(5),
    NUM_TREATMENTS (6);

    private int value;

    HospitalNumbers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

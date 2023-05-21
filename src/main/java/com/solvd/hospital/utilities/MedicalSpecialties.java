package com.solvd.hospital.utilities;

public enum MedicalSpecialties {
    GENERAL_SURGERY(1),
    CARDIOLOGY(2),
    DERMATOLOGY(3),
    NEUROSURGERY(4);

    private final int id;


    MedicalSpecialties(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

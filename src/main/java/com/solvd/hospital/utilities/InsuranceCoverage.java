package com.solvd.hospital.utilities;

public enum InsuranceCoverage {
    TOTAL(100),
    HALF(50),
    NO_INSURANCE(0);

    private final int coverage;


    InsuranceCoverage(int coverage) {
        this.coverage = coverage;
    }

    public int getCoverage() {
        return coverage;
    }
}

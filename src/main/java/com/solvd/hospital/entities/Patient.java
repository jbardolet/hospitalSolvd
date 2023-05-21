package com.solvd.hospital.entities;


import com.solvd.hospital.utilities.InsuranceCoverage;
import com.solvd.hospital.utilities.PatientState;
import com.solvd.hospital.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Comparable<Patient>{
    private Disease disease;
    private int luck;
    private PatientState patientState;
    private int count;
    private List<MedicalRecord> medicalRecords;
    private InsuranceCoverage insuranceCoverage;

    public Patient(Integer id, String name, String lastName,int age, PhoneNumber phone, Address address,String email, ArrayList<MedicalRecord> medicalRecords) {
        super(id, name, lastName,age, phone, address, email);
        this.disease = new Disease();
        this.luck = Utils.getrandomInt(1,3);
        patientState = PatientState.HOSPITALISED;
        //this.count = disease.getPain() + disease.getEmergency();
        //Calculable - my own - lambda
        this.count = disease.calculateDisease((Disease d) ->d.getEmergency()+d.getPain());
        this.medicalRecords = medicalRecords;

    }

    public Patient() {
    }

    public PatientState getPatientState() {
        return patientState;
    }

    public void setPatientState(PatientState patientState) {
        this.patientState = patientState;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void recalculateCount(){
        this.count = disease.getPain() + disease.getEmergency();
    }

    public InsuranceCoverage getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(InsuranceCoverage insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    //to use for sort the patients in priority order
    @Override
    public int compareTo(Patient o) {
        if(this.getCount() == o.getCount()) return 0;
        else if(this.getCount()>o.getCount()) return -1;
        else return 1;

    }

    @Override
    public String toString() {
        return "Patient{" +
                "disease=" + disease +
                ", luck=" + luck +
                ", patientState=" + patientState +
                ", count=" + count +
                ", id=" + super.getId() +
                ", name='" + super.getName()  +
                ", lastName='" + super.getLastName() +
                ", phone=" + super.getPhone() +
                ", address=" + super.getAddress() +
                '}';
    }
}


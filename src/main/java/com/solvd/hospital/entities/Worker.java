package com.solvd.hospital.entities;

import com.solvd.hospital.utilities.WorkShift;
import com.solvd.hospital.interfaces.IPay;

public abstract class Worker extends Person implements IPay {

    private float salary;
    private byte yearsWorHospital;
    private WorkShift workShift;
    private Boolean availability;

    public Worker(Integer id, String name, String lastName, int age,PhoneNumber phone, Address address, String email, float salary, byte yearsWorHospital, WorkShift workShift) {
        super(id, name, lastName,age, phone, address, email);
        this.salary = salary;
        this.yearsWorHospital = yearsWorHospital;
        this.workShift = workShift;
        this.availability = true;
    }

    public Worker() {
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public byte getYearsWorHospital() {
        return yearsWorHospital;
    }

    public void setYearsWorHospital(byte yearsWorHospital) {
        this.yearsWorHospital = yearsWorHospital;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public abstract void introduceYourself();

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return super.getName() +" "+super.getLastName();
    }

    @Override
    public abstract void bePaid();
}


package com.solvd.hospital.entities;

public abstract class HospitalMaterialResource {
    private Integer id;
    private Boolean available;

    public HospitalMaterialResource(Integer id) {
        this.id = id;
        this.available = true;
    }

    public HospitalMaterialResource() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

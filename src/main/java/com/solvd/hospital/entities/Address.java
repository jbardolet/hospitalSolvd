package com.solvd.hospital.entities;

public class Address {

    private String direction;
    private String city;
    private String country;
    private String cp;

    //custom constructor
    public Address(String direction, String city, String country, String cp) {
        this.direction = direction;
        this.city = city;
        this.country = country;
        this.cp = cp;
    }

    //default, if we don't have the data, we can create
    public Address() {
    }

    //getters and setters
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return direction+" "+ city+ " "+ country+" "+cp;
    }
}

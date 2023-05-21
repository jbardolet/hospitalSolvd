package com.solvd.hospital.entities;

import java.util.Objects;

public abstract class Person {
    private Integer id;
    private String name;
    private String lastName;
    private PhoneNumber phone;
    private Address address;
    private String email;
    private int age;

    public Person() {
    }

    public Person(Integer id, String name, String lastName,int age, PhoneNumber phone, Address address, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


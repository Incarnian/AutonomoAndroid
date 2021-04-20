package com.cursojava.appautonomo.model;

public class Address {
    private String street;

    private String city;

    private Integer number;

    private String cep;

    public Address() {}

    public Address(String street, String city, Integer number, String cep) {
        this.street = street;
        this.city = city;
        this.number = number;
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

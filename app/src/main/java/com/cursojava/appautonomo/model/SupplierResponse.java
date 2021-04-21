package com.cursojava.appautonomo.model;

import java.util.List;

public class SupplierResponse {
    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private Address address;
    private List<Phone> phones;

    public SupplierResponse() {
    }

    public SupplierResponse(Long id, String name, String cnpj, String email, Address address, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.address = address;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    // NÂO MUDARRRR
    @Override
    public String toString() {
        return name;
    }
}

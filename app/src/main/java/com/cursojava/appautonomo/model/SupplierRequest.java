package com.cursojava.appautonomo.model;

import java.util.List;

public class SupplierRequest {

    private final String name;
    private final String cnpj;
    private final String email;
    private final List<Phone> phones;
    private final Address address;

    private SupplierRequest (Builder builder){
        this.name = builder.name;
        this.cnpj = builder.cnpj;
        this.email = builder.email;
        this.phones = builder.phones;
        this.address = builder.address;
    }

    public static class Builder {

        private String name;
        private String cnpj;
        private String email;
        private List<Phone> phones;
        private Address address;

        public Builder name (String name){
            this.name = name;
            return this;
        }

        public Builder cnpj (String cnpj){
            this.cnpj = cnpj;
            return this;
        }

        public Builder email (String email){
            this.email = email;
            return this;
        }

        public Builder phones (List<Phone> phones){
            this.phones = phones;
            return this;
        }

        public Builder address (Address address){
            this.address = address;
            return this;
        }

        public SupplierRequest build(){
            return new SupplierRequest(this);
        }
    }



}

package com.cursojava.appautonomo.model;

public class SupplierRequest {

    private final String name;
    private final String cnpj;
    private final String email;
    private final String phone;
    private final Address address;

    private SupplierRequest (Builder builder){
        this.name = builder.name;
        this.cnpj = builder.cnpj;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public static class Builder {

        private String name;
        private String cnpj;
        private String email;
        private String phone;
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

        public Builder phone (String phone){
            this.phone = phone;
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

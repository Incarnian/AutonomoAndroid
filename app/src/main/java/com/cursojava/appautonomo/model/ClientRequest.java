package com.cursojava.appautonomo.model;

public class ClientRequest {

    private final String name;
    private final String email;
    private final String cpf;
    private final String phone;
    private final Address address;

    private ClientRequest(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.cpf = builder.cpf;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public static class Builder {

        private String name;
        private String email;
        private String cpf;
        private String phone;
        private Address address;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public ClientRequest build() {
            return new ClientRequest(this);
        }

    }


}

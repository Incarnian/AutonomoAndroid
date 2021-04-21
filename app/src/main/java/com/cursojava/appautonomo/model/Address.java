package com.cursojava.appautonomo.model;
public class Address {

    private final String street;
    private final String city;
    private final Integer number;
    private final String cep;
    private final String neighborhood;

    private Address(Builder builder){
        this.street= builder.street;
        this.city = builder.city;
        this.number = builder.number;
        this.cep = builder.cep;
        this.neighborhood = builder.neighborhood;
    }

    public static class Builder {

        private String street;
        private String city;
        private Integer number;
        private String cep;
        private String neighborhood;

        public Builder street(final String street) {
            this.street = street;
            return this;
        }

        public Builder city(final String city) {
            this.city = city;
            return this;
        }

        public Builder number(final Integer number) {
            this.number = number;
            return this;
        }

        public Builder cep(final String cep) {
            this.cep = cep;
            return this;
        }

        public Builder neighborhood(final String neighborhood) {
            this.neighborhood = neighborhood;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
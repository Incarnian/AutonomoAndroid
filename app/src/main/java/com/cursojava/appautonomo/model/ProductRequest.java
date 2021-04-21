package com.cursojava.appautonomo.model;

public class ProductRequest {

    private final String name;
    private final Double price;
    private final String description;
    private final String measurement;
    private final Integer quantity;
    private final Long supplierId;

    private ProductRequest(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.measurement = builder.measurement;
        this.quantity = builder.quantity;
        this.supplierId = builder.supplierId;
    }

    public static class Builder {

        private String name;
        private Double price;
        private String description;
        private String measurement;
        private Integer quantity;
        private Long supplierId;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder price(final Double price) {
            this.price = price;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder measurement(final String measurement) {
            this.measurement = measurement;
            return this;
        }

        public Builder quantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder supplierId(final Long supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public ProductRequest build() {
            return new ProductRequest(this);
        }

    }


}

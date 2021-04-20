package com.cursojava.appautonomo.model;

public class ProductRequest {

    private String name;
    private Double price;
    private String description;
    private String measurement;
    private Integer quantity;
    private String supplierId;

    public ProductRequest() {
    }

    public ProductRequest(String name, Double price, String description, String measurement, Integer quantity, String supplierId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.measurement = measurement;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", measurement='" + measurement + '\'' +
                ", quantity=" + quantity +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }
}

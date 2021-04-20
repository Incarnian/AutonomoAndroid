package com.cursojava.appautonomo.model;

public class ProductResponse {

    private Long id;
    private String name;
    private String price;
    private String description;
    private String measurement;
    private Integer quantity;
    private Integer supplierId;

    public ProductResponse(Long id,
                           String name,
                           String price,
                           String description,
                           String measurement,
                           Integer quantity,
                           Integer supplierId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.measurement = measurement;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getMeasurement() {
        return measurement;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSupplierId() {
        return supplierId;
    }
}

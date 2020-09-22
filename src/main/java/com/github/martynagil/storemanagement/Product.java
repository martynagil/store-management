package com.github.martynagil.storemanagement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.UUID;

public class Product {

    private String id;
    private String name;
    private String brand;
    private String type;
    private String barcode;
    private double price;

    @JsonCreator
    public Product(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("brand") String brand,
            @JsonProperty("type") String type,
            @JsonProperty("barcode") String barcode,
            @JsonProperty("price") double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.barcode = barcode;
        this.price = price;
    }

    public Product(String name, String brand, String type, String barcode, double price) {
        this(UUID.randomUUID().toString(), name, brand, type, barcode, price);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", barcode='" + barcode + '\'' +
                ", price=" + price +
                '}';
    }
}

// uuid
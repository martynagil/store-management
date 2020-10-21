package com.github.martynagil.storemanagement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Objects;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setPrice(String cost) {
        try {
            price = Double.parseDouble(cost);
        } catch (Exception e) {
            throw new IllegalArgumentException("Number required", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(type, product.type) &&
                Objects.equals(barcode, product.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, type, barcode, price);
    }
}


package com.github.martynagil.storemanagement;

public class Product {

    private String name;
    private String brand;
    private String type;
    private double price;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public Product(String name, String brand, String type, double price) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
    }
}

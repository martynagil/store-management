package com.github.martynagil.storemanagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private static final Path SAVE_PATH = Paths.get("saveProductList.json");

    private ObjectMapper objectMapper = new ObjectMapper();

    private Console console = new Console();

    private List<Product> products = new ArrayList<Product>();

    public void saveProductList() {
        try {
            String json = objectMapper.writeValueAsString(products);
            Files.write(SAVE_PATH, json.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void loadSave() {
        try {
            byte[] bytes = Files.readAllBytes(SAVE_PATH);
            products = objectMapper.readValue(bytes, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void show() {
        int index = 0;
        for (Product product : products) {
            System.out.println(index + 1 + " " + product.toString());
            index++;
        }
    }

    public void add() {
        String name = console.askForString("Enter the name of product: ");
        String brand = console.askForString("Enter the name of brand: ");
        String type = console.askForString("Enter the type: ");
        String barcode = console.askForString("Enter the barcode: ");
        Double price = console.askForDouble("Enter the price: ");
        Product product = new Product(name, brand, type, barcode, price);

        products.add(product);
    }

    public void delete() {
        show();
        int index = console.askForInt("Enter the index: ");
        products.remove(index - 1);
    }

    public boolean saveExist() {
        return Files.exists(SAVE_PATH);
    }

    public int returnListLength() {
        return products.size();
    }

    public void deleteSave() {
        try {
            Files.deleteIfExists(SAVE_PATH);
        } catch (IOException e) {
        }
    }
}

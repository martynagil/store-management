package com.github.martynagil.storemanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martynagil.storemanagement.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonProductRepository implements ProductRepository {

    private Path savePath;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Product> products = new ArrayList<>();

    public JsonProductRepository(Path savePath) {
        this.savePath = savePath;
        if (dataExist()) {
            loadData();
        }
    }

    @Override
    public void removeByIndex(int index) {
        products.remove(index);
        writeData();
    }

    @Override
    public void modifyByIndex(int index) {

    }

    @Override
    public void save(Product product) {
        products.add(product);
        writeData();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    private void writeData() {
        try {
            String json = objectMapper.writeValueAsString(products);
            Files.write(savePath, json.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void loadData() {
        try {
            byte[] bytes = Files.readAllBytes(savePath);
            products = objectMapper.readValue(bytes, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean dataExist() {
        return Files.exists(savePath);
    }

}

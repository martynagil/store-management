package com.github.martynagil.storemanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martynagil.storemanagement.MenuAction;
import com.github.martynagil.storemanagement.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void removeById(String id) {
        products.removeIf(product -> product.getId().equals(id));
        writeData();
    }

    @Override
    public void save(Product product) {
        if (exists(product)) {
            update(product);
        } else {
            products.add(product);
        }
        writeData();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    private void update(Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getId().equals(product.getId())) {
                products.set(i, product);
                return;
            }
        }
    }

    private boolean exists(Product product) {
        return products.stream()
                .anyMatch(p -> p.getId().equals(product.getId()));
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
            products = objectMapper.readValue(bytes, new TypeReference<List<Product>>() {
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean dataExist() {
        try {
            return Files.exists(savePath) && Files.size(savePath) > 0;
        } catch (IOException e) {
            return false;
        }
    }

}

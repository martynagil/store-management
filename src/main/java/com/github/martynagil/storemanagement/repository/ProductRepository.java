package com.github.martynagil.storemanagement.repository;

import com.github.martynagil.storemanagement.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    void save(Product product);

    void removeByIndex(int index);
}

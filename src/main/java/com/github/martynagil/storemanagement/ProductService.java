package com.github.martynagil.storemanagement;

import com.github.martynagil.storemanagement.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void removeByIndex(int index) {
        productRepository.removeByIndex(index);
    }
}

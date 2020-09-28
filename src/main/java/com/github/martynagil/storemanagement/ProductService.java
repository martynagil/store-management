package com.github.martynagil.storemanagement;

import com.github.martynagil.storemanagement.repository.ProductRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addToRepository(Product product) {
        productRepository.save(product);
    }

    public void remove(Product product) {
        productRepository.removeById(product.getId());
    }

    public void modify(int index, int category, String newData) {
        productRepository.modifyByIndex(index, category, newData);
    }

    public List<Product> searchByText(String text) {
        return getAllProducts().stream()
                .filter(product -> matchesCriteria(text, product))
                .collect(toList());
    }

    private boolean matchesCriteria(String text, Product product) {
        return product.getName().toLowerCase().contains((text).toLowerCase())
                || product.getBrand().toLowerCase().contains((text).toLowerCase())
                || product.getType().toLowerCase().contains((text).toLowerCase());
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}

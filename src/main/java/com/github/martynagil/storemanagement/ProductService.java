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

    public void remove(Product product) {
        productRepository.removeById(product.getId());
    }

    public List<Product> searchByText(String text) {
        return getAllProducts().stream()
                .filter(product -> matchesCriteria(text, product))
                .collect(toList());
    }

    private boolean matchesCriteria(String text, Product product) {
        String searchText = text.toLowerCase();
        return product.getName().toLowerCase().contains(searchText)
                || product.getBrand().toLowerCase().contains(searchText)
                || product.getType().toLowerCase().contains(searchText);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}

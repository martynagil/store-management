package com.github.martynagil.storemanagement;

import com.github.martynagil.storemanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.MockitoAnnotations.openMocks;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldGiveAllProducts() {
        productService.getAllProducts();
    }

}
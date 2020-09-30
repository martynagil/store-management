package com.github.martynagil.storemanagement;

import com.github.martynagil.storemanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        Product product1 = new Product("name", "brand", "type", "barcode", 2.09);
        Product product2 = new Product("name2", "brand2", "type2", "barcode2", 2.1);
        List<Product> products = asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> allProducts = productService.getAllProducts();

        assertThat(allProducts)
                .hasSize(2)
                .containsExactly(product1, product2);
    }

    @Test
    void shouldRemoveProduct() {
        Product product = new Product("id","name", "brand", "type", "barcode", 2.09);

        productService.remove(product);

        verify(productRepository).removeById("id");
    }

    @Test
    void shouldAddProductToRepository() {
        Product product = new Product("id","name", "brand", "type", "barcode", 2.09);

        productService.addToRepository(product);

        verify(productRepository).save(product);
    }

    @Test
    void shouldSearchByText() {
        Product product1 = new Product("kremn","krem nawilżający", "brand", "type", "barcode", 2.09);
        Product product2 = new Product("kremp","krem przeciwzmarszczkowy", "brand", "type", "barcode", 2.09);
        Product product3 = new Product("tonic","tonik", "brand", "type", "barcode", 2.09);

        productService.addToRepository(product1);
        productService.addToRepository(product2);
        productService.addToRepository(product3);
        //when(productRepository.findAll()).thenReturn(products);
// TODO: 30.09.2020  
    }

}
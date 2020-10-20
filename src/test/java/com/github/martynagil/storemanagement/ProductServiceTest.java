package com.github.martynagil.storemanagement;

import com.github.martynagil.storemanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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

        productService.save(product);

        verify(productRepository).save(product);
    }

    @Test
    void shouldSearchByTextInName() {
        Product product1 = new Product("scsdcs","krem nawilżający", "brand", "type", "barcode", 2.09);
        Product product2 = new Product("ksdccsc","krem przeciwzmarszczkowy", "brand", "type", "barcode", 2.09);
        Product product3 = new Product("tonic","tonik", "brand", "type", "barcode", 2.09);
        List<Product> products = asList(product1, product2, product3);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> searchedProducts = productService.searchByText("krem");

        assertThat(searchedProducts)
                .hasSize(2)
                .containsExactly(product1, product2);

    }

    @Test
    void shouldSearchByTextInBrand() {
        Product product1 = new Product("scsdcs","nawilżający", "my super brand", "type", "barcode", 2.09);
        Product product2 = new Product("ksdccsc","przeciwzmarszczkowy", "my brand is extra", "type", "barcode", 2.09);
        Product product3 = new Product("tonic","tonik", "company", "type", "barcode", 2.09);
        List<Product> products = asList(product1, product2, product3);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> searchedProducts = productService.searchByText("brand");

        assertThat(searchedProducts)
                .hasSize(2)
                .containsExactly(product1, product2);

    }

    @Test
    void shouldSearchByTextInType() {
        Product product1 = new Product("scsdcs","nawilżający", "my super brand", "typekrem", "barcode", 2.09);
        Product product2 = new Product("ksdccsc","przeciwzmarszczkowy", "my brand is extra", "krem", "barcode", 2.09);
        Product product3 = new Product("tonic","tonik", "company", "2type", "barcode", 2.09);
        List<Product> products = asList(product1, product2, product3);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> searchedProducts = productService.searchByText("type");

        assertThat(searchedProducts)
                .hasSize(2)
                .containsExactly(product1, product3);

    }

    @Test
    void shouldNotSearchByNonExistingText() {
        Product product1 = new Product("scsdcs","nawilżający", "my super brand", "typekrem", "barcode", 2.09);
        Product product2 = new Product("ksdccsc","przeciwzmarszczkowy", "my brand is extra", "krem", "barcode", 2.09);
        Product product3 = new Product("tonic","tonik", "company", "2type", "barcode", 2.09);
        List<Product> products = asList(product1, product2, product3);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> searchedProducts = productService.searchByText("nothing");

        assertThat(searchedProducts).isEmpty();
    }

}

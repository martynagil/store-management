package com.github.martynagil.storemanagement.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.martynagil.storemanagement.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class JsonProductRepositoryTest {

    private File tempFile;
    private JsonProductRepository jsonProductRepository;
    private Path path;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("storeManagement", ".json");
        path = Paths.get(tempFile.getAbsolutePath());
        jsonProductRepository = new JsonProductRepository(path);
    }

    @AfterEach
    void cleanTempFile() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void shouldAddNewProduct() {
        Product product1 = new Product("name", "brand", "type", "barcode", 3.99);
        Product product2 = new Product("name", "brand", "type", "barcode", 3.99);

        jsonProductRepository.save(product1);
        jsonProductRepository.save(product2);

        assertThat(jsonProductRepository.findAll()).hasSize(2);
        assertThat(readTempFile()).containsExactly(product1, product2);
    }

    @Test
    void shouldModifyExistingProduct() {
        Product product = new Product("id", "name", "brand", "type", "barcode", 3.99);

        jsonProductRepository.save(product);
        Product modifiedProduct = new Product("id", "other name", "brand", "type", "barcode", 3.99);
        jsonProductRepository.save(modifiedProduct);

        assertThat(jsonProductRepository.findAll()).hasSize(1);
        assertThat(readTempFile()).containsExactly(modifiedProduct);
    }

    @Test
    void shouldRemove() {
        Product product1 = new Product("name", "brand", "type", "barcode", 3.99);
        Product product2 = new Product("name", "brand", "type", "barcode", 3.99);

        jsonProductRepository.save(product1);
        jsonProductRepository.save(product2);
        jsonProductRepository.removeById(product1.getId());

        assertThat(jsonProductRepository.findAll()).doesNotContain(product1);
        assertThat(jsonProductRepository.findAll()).hasSize(1);
    }

    @Test
    void shouldNotRemove() {
        Product product = new Product("id", "name", "brand", "type", "barcode", 3.99);

        jsonProductRepository.save(product);
        jsonProductRepository.removeById("other id");

        assertThat(jsonProductRepository.findAll()).hasSize(1);
    }

    @Test
    void shouldLoad() throws IOException {
        File tempFileLoad = File.createTempFile("loadTest", ".json");
        Path pathLoad = Paths.get(tempFileLoad.getAbsolutePath());
        JsonProductRepository jsonProductRepositoryToLoad = new JsonProductRepository(pathLoad);
        Product product = new Product("id", "name", "brand", "type", "barcode", 3.99);

        jsonProductRepositoryToLoad.save(product);
        JsonProductRepository jsonProductRepositoryLoadTest = new JsonProductRepository(pathLoad);

        assertThat(jsonProductRepositoryToLoad.findAll()).isEqualTo(jsonProductRepositoryLoadTest.findAll());
    }

    private List<Product> readTempFile() {
        try {
            byte[] bytes = Files.readAllBytes(path);
            return objectMapper.readValue(bytes, new TypeReference<List<Product>>() {
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
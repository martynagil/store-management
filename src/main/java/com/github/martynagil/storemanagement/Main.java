package com.github.martynagil.storemanagement;

import com.github.martynagil.storemanagement.repository.JsonProductRepository;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path savePath = Paths.get("data.json");
        Console console = new Console();
        ProductService productService = new ProductService(new JsonProductRepository(savePath));
        UserInterface userInterface = new UserInterface(console, productService);

        new Application(userInterface).run();
    }

}

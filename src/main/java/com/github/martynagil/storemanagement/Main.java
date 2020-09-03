package com.github.martynagil.storemanagement;

import com.github.martynagil.storemanagement.repository.JsonProductRepository;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        ProductService productService = new ProductService(new JsonProductRepository());
        UserInterface userInterface = new UserInterface(console, productService);

        new Application(userInterface).run();
    }

}

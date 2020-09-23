package com.github.martynagil.storemanagement;

import java.util.List;
import java.util.stream.Collectors;

public class UserInterface {

    private Console console;
    private ProductService productService;

    public UserInterface(Console console, ProductService productService) {

        this.console = console;
        this.productService = productService;
    }

    public void exit() {
        console.printGoodbye();
    }

    public void printMenu() {
        console.printMenu();
    }

    public int askForMenuChoice() {
        return console.askForInt("Your choice:");
    }

    public void showProducts() {
        showProducts(productService.getAllProducts());
    }

    public void add() {
        String name = console.askForString("Enter the name of product: ");
        String brand = console.askForString("Enter the name of brand: ");
        String type = console.askForString("Enter the type: ");
        String barcode = console.askForString("Enter the barcode: ");
        double price = console.askForDouble("Enter the price: ");
        Product product = new Product(name, brand, type, barcode, price);

        productService.addToRepository(product);
    }

    public void delete() {
        showProducts();
        int index = console.askForInt("Enter the index: ");
        productService.removeByIndex(index - 1);
    }

    public void search() {
        showProducts(listSearch());
    }

    public void modifyProduct() {
        search();
        List<Product> list = listSearch();
        int choice;
        if (list.size() > 1) {
            choice = console.askForInt("Enter your choice") - 1;
        } else {
            choice = 0;
        }

        // TODO: 24.09.2020 co konkretnie się chce zmodyfikować na co
        productService.modify(indexFromId(list.get(choice).getId()));
    }

    private void showProducts(List<Product> products) {
        int index = 1;
        for (Product product : products) {
            console.writeMessage(index + " " + product.toString());
            index++;
        }
        System.out.println();
    }

    private List<Product> listSearch() {
        String name = console.askForString("Enter the name of product: ");
        List<Product> matchingElements = productService.getAllProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains((name).toLowerCase())
                        || product.getBrand().toLowerCase().contains((name).toLowerCase())
                        || product.getType().toLowerCase().contains((name).toLowerCase()))
                .collect(Collectors.toList());
        return matchingElements;
    }

    private int indexFromId(String id) {
        int index = 0;
        while (!productService.getAllProducts().get(index).getId().equals(id)) {
            index++;
        }
        return index;
    }
}

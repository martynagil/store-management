package com.github.martynagil.storemanagement;

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
        return console.askForMenuChoice();
    }

    public void showProducts() {
        int index = 0;
        for (Product product : productService.getAllProducts()) {
            console.writeMessage(index + 1 + " " + product.toString());
            index++;
        }
    }

    public void addProduct() {
        String name = console.askForString("Enter the name of product: ");
        String brand = console.askForString("Enter the name of brand: ");
        String type = console.askForString("Enter the type: ");
        String barcode = console.askForString("Enter the barcode: ");
        double price = console.askForDouble("Enter the price: ");
        Product product = new Product(name, brand, type, barcode, price);

        productService.addProduct(product);
    }

    public void deleteProduct() {
        showProducts();
        int index = console.askForInt("Enter the index: ");
        productService.removeByIndex(index - 1);
    }
}

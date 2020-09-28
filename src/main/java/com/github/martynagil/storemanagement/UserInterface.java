package com.github.martynagil.storemanagement;

import java.util.Comparator;
import java.util.List;

public class UserInterface {

    private static final Comparator<Product> PRODUCT_COMPARATOR = Comparator.comparing(Product::getBrand)
            .thenComparing(Product::getName);

    private Console console;
    private ProductService productService;

    public UserInterface(Console console, ProductService productService) {

        this.console = console;
        this.productService = productService;
    }

    public void mShowProducts() {
        showProducts(productService.getAllProducts());
    }

    public void mAddProduct() {
        String name = console.askForString("Enter the name of product: ");
        String brand = console.askForString("Enter the name of brand: ");
        String type = console.askForString("Enter the type: ");
        String barcode = console.askForString("Enter the barcode: ");
        double price = console.askForDouble("Enter the price: ");
        Product product = new Product(name, brand, type, barcode, price);

        productService.addToRepository(product);
    }

    public void mDeleteProduct() {
        List<Product> products = productService.getAllProducts();
        products.sort(PRODUCT_COMPARATOR);
        showProducts(products);
        int index = console.askForInt("Enter the index: ");
        productService.remove(products.get(index - 1));
    }

    public void mSearchProduct() {
        showProducts(searchProducts());
    }

    public void mModifyProduct() {
        List<Product> products = searchProducts();
        showProducts(products);
        int productIndex = console.askForInt("Enter the index: ");
        Product product = products.get(productIndex - 1);
        console.printAttributes();
        int attribute = console.askForInt("Choose attribute to change: ");
        String newData = console.askForString("Enter what do you want to change the data for: ");
        switch (attribute) {
            case 1:
                product.setName(newData);
                break;
            case 2:
                product.setBrand(newData);
                break;
            case 3:
                product.setType(newData);
                break;
            case 4:
                product.setBarcode(newData);
                break;
            case 5:
                product.setPrice(newData);
                break;
            default:
                throw new IndexOutOfBoundsException();
        }

        productService.save(product);
    }

    public void mExit() {
        console.printGoodbye();
    }

    public void printMenu() {
        console.printMenu();
    }

    public int askForMenuChoice() {
        return console.askForInt("Your choice:");
    }

    private void showProducts(List<Product> products) {
        products.sort(PRODUCT_COMPARATOR);
        int index = 1;
        for (Product product : products) {
            console.writeMessage(index + " " + product.toString());
            index++;
        }
        System.out.println();
    }

    private int chooseItem(List<Product> list) {
        int choice;
        if (list.size() == 0) {
            return 0;
        }
        choice = console.askForInt("Enter your choice") - 1;
        if (choice >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        return choice;
    }

    private List<Product> searchProducts() {
        String text = console.askForString("Enter search: ");
        return productService.searchByText(text);
    }

    private int indexFromId(String id) {
        int index = 0;
        while (!productService.getAllProducts().get(index).getId().equals(id)) {
            index++;
        }
        return index;
    }
}

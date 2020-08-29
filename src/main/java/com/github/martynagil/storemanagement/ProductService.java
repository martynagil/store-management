package com.github.martynagil.storemanagement;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private Console console = new Console();

    private List<Product> products = new ArrayList<Product>();

    public void show() {
        int index = 0;
        for (Product product : products) {
            System.out.println(index + 1 + " " + product.toString());
            index++;
        }
    }

    public void add() {
        String name = console.askForNameAndReturnIt();
        String brand = console.askForBrandAndReturnIt();
        String type = console.askForTypeAndReturnIt();
        Double price = console.askForPriceAndReturnIt();
        Product product = new Product(name, brand, type, price);

        products.add(product);
    }

    public void delete() {
        show();
        int index = console.askForIndexOfProductAndReturnIt();
        products.remove(index - 1);
    }
}
// TODO: 29.08.2020 zrobić zapisywanie do jsona
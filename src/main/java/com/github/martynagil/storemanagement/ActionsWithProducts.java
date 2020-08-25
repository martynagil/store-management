package com.github.martynagil.storemanagement;

import sun.awt.windows.WPrinterJob;

import java.util.ArrayList;
import java.util.List;

public class ActionsWithProducts {
    
    private Console console = new Console();

    private List<Product> products = new ArrayList<Product>();

    public void show() {
        products.forEach(System.out::print);
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
        // TODO: 17.08.2020 
    }
}

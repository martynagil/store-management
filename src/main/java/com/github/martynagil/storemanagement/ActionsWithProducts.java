package com.github.martynagil.storemanagement;

public class ActionsWithProducts {
    
    private Console console = new Console();

    public void show() {
        // TODO: 17.08.2020
    }

    public void add() {
        String name = console.askForNameAndReturnIt();
        String brand = console.askForBrandAndReturnIt();
        String type = console.askForTypeAndReturnIt();
        Double price = console.askForPriceAndReturnIt();
        new Product(name, brand, type, price);

        // TODO: 17.08.2020  
    }

    public void delete() {
        // TODO: 17.08.2020 
    }
}

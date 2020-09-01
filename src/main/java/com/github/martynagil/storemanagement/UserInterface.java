package com.github.martynagil.storemanagement;

import java.util.HashMap;
import java.util.Map;

public class UserInterface {

    private Console console = new Console();
    private ProductService productService = new ProductService();
    private Map<Integer, MenuAction> menuActions = new HashMap<>();

    public void run() {
        initMenu();
        int choice;
        if (productService.saveExist()) {
            productService.loadSave();
            productService.deleteSave();
        }
        do {
            console.printMenu();
            choice = console.askForMenuChoice();
            menuActions.get(choice).run();
        } while (choice != 0);
        if (productService.returnListLength() != 0) {
            productService.saveProductList();
        }
    }

    private void initMenu() {
        menuActions.put(0, () -> console.printGoodbye());
        menuActions.put(1, () -> productService.show());
        menuActions.put(2, () -> productService.add());
        menuActions.put(3, () -> productService.delete());
    }
}

package com.github.martynagil.storemanagement;

import java.util.HashMap;
import java.util.Map;

public class UserInterface {

    private Console console = new Console();
    private ProductService action = new ProductService();
    private Map<Integer, MenuAction> menuActions = new HashMap<>();

    public void run() {
        initMenu();
        int choice;
        do {
            console.printMenu();
            choice = console.askForMenuChoice();
            menuActions.get(choice).run();
        } while (choice != 0);
    }

    private void initMenu() {
        menuActions.put(0, () -> console.printGoodbye());
        menuActions.put(1, () -> action.show());
        menuActions.put(2, () -> action.add());
        menuActions.put(3, () -> action.delete());
    }
}

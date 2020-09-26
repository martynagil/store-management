package com.github.martynagil.storemanagement;

import java.util.HashMap;
import java.util.Map;

public class Application {

    private Map<Integer, MenuAction> menuActions = new HashMap<>();
    private UserInterface userInterface;

    public Application(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void run() {
        initMenu();
        int choice;
        do {
            userInterface.printMenu();
            choice = userInterface.askForMenuChoice();
            if (choice > 4) {
                throw new IndexOutOfBoundsException();
            }
            menuActions.get(choice).run();
        } while (choice != 0);
    }

    private void initMenu() {
        menuActions.put(0, new MenuAction() {
            @Override
            public void run() {
                userInterface.exit();
            }
        });
        menuActions.put(1, () -> userInterface.showProducts());
        menuActions.put(2, () -> userInterface.add());
        menuActions.put(3, () -> userInterface.delete());
        menuActions.put(4, () -> userInterface.search());
    }
}

// TODO: 03.09.2020 czy plik ma zawartość
// TODO: 23.09.2020 Zrób testy, skup się na serwisie oraz repozytorium
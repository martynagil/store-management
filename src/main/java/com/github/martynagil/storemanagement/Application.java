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
            if (choice >= menuActions.size()) {
                throw new IndexOutOfBoundsException();
            }
            menuActions.get(choice).run();
        } while (choice != 0);
    }

    private void initMenu() {
        menuActions.put(0, new MenuAction() {
            @Override
            public void run() {
                userInterface.mExit();
            }
        });
        menuActions.put(1, () -> userInterface.mShowProducts());
        menuActions.put(2, () -> userInterface.mAddProduct());
        menuActions.put(3, () -> userInterface.mDeleteProduct());
        menuActions.put(4, () -> userInterface.mSearchProduct());
        menuActions.put(5, () -> userInterface.mModifyProduct());
    }
}

// TODO: 23.09.2020 Zrób testy, skup się na serwisie oraz repozytorium
// TODO: 28.09.2020 obsługa błędów do wszystkiego elo
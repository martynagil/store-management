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
            menuActions.get(choice).run();
        } while (choice != 0);
    }

    private void initMenu() {
        menuActions.put(0, () -> userInterface.exit());
        menuActions.put(1, () -> userInterface.showProducts());
        menuActions.put(2, () -> userInterface.addProduct());
        menuActions.put(3, () -> userInterface.deleteProduct());
    }
}

// TODO: 03.09.2020 czy plik ma zawartość
// TODO: 02.09.2020 pooglądać na youtube o mockito
// TODO: 29.08.2020 zmodyfikować metodę save o parametr mówiący o folderze, żeby się nie nadpisywało

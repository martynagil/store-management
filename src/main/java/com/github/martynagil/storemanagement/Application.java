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
        menuActions.put(0, new MenuAction() {
            @Override
            public void run() {
                userInterface.exit();
            }
        });
        menuActions.put(1, () -> userInterface.showProducts());
        menuActions.put(2, () -> userInterface.addProduct());
        menuActions.put(3, () -> userInterface.deleteProduct());
        menuActions.put(4, () -> userInterface.searchProduct());
    }
}

// TODO: 03.09.2020 czy plik ma zawartość
// TODO: 21.09.2020 modyfikacja produktu
// TODO: 23.09.2020 Przy wyświetlaniu produktów posortuj je po marce oraz nazwie (tej samej marki produkty koło siebie i w obrębie marki posortowane po nazwie)
// TODO: 23.09.2020 Zrób testy, skup się na serwisie oraz repozytorium
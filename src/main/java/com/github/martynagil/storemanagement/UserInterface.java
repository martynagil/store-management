package com.github.martynagil.storemanagement;

public class UserInterface {

    private Console console = new Console();
    private int[] menu = {0, 1, 2, 3};

    public void run() {
        do {
            console.printMenu();
            action(choiceMenu(console.enterChoice()));
        }while (choiceMenu(console.enterChoice()) != 0);
    }

    private int choiceMenu(int choice) {
        try {
            return menu[choice];
        } catch (Exception e) {
            throw new IllegalStateException("Give proper number [0-3]");
        }
    }

    private void action(int choice) {
        ActionsWithProducts action = new ActionsWithProducts();
        switch (choice) {
            case 0:
                console.printGoodbye();
            case 1:
                action.show();
            case 2:
                action.add();
            case 3:
                action.delete();

        }
    }
}

package storeManagement;

public class UserInterface {

    private Console console = new Console();
    private int[] menu = {0, 1, 2, 3};

    public void run() {
        console.printMenu();
        choiceMenu(console.enterChoice());
    }

    private int choiceMenu(int choice) {
        try {
            return menu[choice];
        } catch (Exception e) {
            throw new IllegalStateException("Give proper number [0-3]");
        }
    }

    private void action(int choice) {
        switch (choice) {
            case 0:
                console.printGoodbye();
            case 1:
                Products.show();
            case 2:
                Products.add();
            case 3:
                Products.delete();

        }
    }
}

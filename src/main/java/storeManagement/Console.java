package storeManagement;

import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.print("[1] Show products");
        System.out.println("[2] Add product");
        System.out.println("[3] Delete product");
        System.out.println("[0] Exit");
        System.out.println();
        System.out.println("Your choice:");
    }

    public int enterChoice() {
       return scanner.nextInt();
    }

    public void printGoodbye() {
        System.out.println("Goodbye!");
    }
}

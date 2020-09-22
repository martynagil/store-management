package com.github.martynagil.storemanagement;

import java.lang.ref.SoftReference;
import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("[1] Show products");
        System.out.println("[2] Add product");
        System.out.println("[3] Delete product");
        System.out.println("[4] Search product");
        System.out.println("[0] Exit");
        System.out.println();
    }

    public void printGoodbye() {
        System.out.println("Goodbye!");
    }

    public String askForString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public double askForDouble(String message) {
        System.out.println(message);
        String string = scanner.nextLine();
        return Double.parseDouble(string);
    }

    public int askForInt(String message) {
        System.out.println(message);
        String string = scanner.nextLine();
        return Integer.parseInt(string);
    }

    public void writeMessage(String msg) {
        System.out.println(msg);
    }
}

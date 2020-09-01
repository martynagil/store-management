package com.github.martynagil.storemanagement;

import java.lang.ref.SoftReference;
import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("[1] Show products");
        System.out.println("[2] Add product");
        System.out.println("[3] Delete product");
        System.out.println("[0] Exit");
        System.out.println();
        System.out.println("Your choice:");
    }

    public int askForMenuChoice() {
       return scanner.nextInt();
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
        return scanner.nextDouble();
    }

    public int askForInt(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
}

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

    public String askForNameAndReturnIt() {
        System.out.print("Enter the name of product: ");
        return scanner.nextLine();
    }

    public String askForBrandAndReturnIt() {
        System.out.print("Enter the name of brand: ");
        return scanner.nextLine();
    }

    public String askForTypeAndReturnIt() {
        System.out.print("Enter the type: ");
        return scanner.nextLine();
    }

    public double askForPriceAndReturnIt() {
        System.out.print("Enter the price: ");
        return scanner.nextDouble();
    }

    public int askForIndexOfProductAndReturnIt() {
        System.out.println("Enter the index: ");
        return scanner.nextInt();
    }
}

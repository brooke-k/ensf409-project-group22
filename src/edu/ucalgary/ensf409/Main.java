package edu.ucalgary.ensf409;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        UserIO input = new UserIO();
        // Initiate a menu object
        int selection = input.menu();
        System.out.println("You entered: " + selection);
        // Example taking in input
        System.out.print("Please enter user input: ");
        int in = input.readIntUntilAccepted(0, 4);
        System.out.println("You entered: " + in);

        // Testing PriceOptimizer

    }
}

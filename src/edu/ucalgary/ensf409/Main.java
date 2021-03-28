package edu.ucalgary.ensf409;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        UserIO input = new UserIO();

        int selection = input.menu();
        input.processInput(selection);


       //System.out.println("You entered: " + selection);
      // System.out.print("Please enter user input: ");
        //int in = input.readIntUntilAccepted(0, 4);
        //System.out.println("You entered: " + in);

        // Testing PriceOptimizer

    }
}

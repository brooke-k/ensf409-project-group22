package edu.ucalgary.ensf409;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      /*  System.out.println("Hello world!");
        UserIO input = new UserIO();
        // Initiate a menu object
        int selection = input.menu();
        System.out.println("You entered: " + selection);
        // Example taking in input
        System.out.print("Please enter user input: ");
        int in = input.readIntUntilAccepted(0, 4);
        System.out.println("You entered: " + in);
        */
        String[] id = {"one", "two", "three"};
        boolean[][] parts =  {{true, true, false}, {false, false, false}, {false, false, true}};
        int[] price = {10, 20, 5};
        PriceOptimizer opt = new PriceOptimizer(id, parts, price);
        String[] output = opt.optimize();
        for (String i: output) {
            System.out.println(i);
        }


      /*  String[] output = new String[3];
        output = opt.optimize();
        for (int i = 0; i < output.length; i++) {
            System.out.println(output);
        }*/
    }
}

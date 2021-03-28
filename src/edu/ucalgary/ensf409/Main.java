package edu.ucalgary.ensf409;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
     /*   System.out.println("Hello world!");
        UserIO input = new UserIO();
        // Initiate a menu object
        int selection = input.menu();


        System.out.println("You entered: " + selection);
        // Example taking in input
        System.out.print("Please enter user input: ");
        int in = input.readIntUntilAccepted(0, 4);
        System.out.println("You entered: " + in);
*/
        // Testing PriceOptimizer
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, false, false, true},

        };
        PriceOptimizer opt = new PriceOptimizer(id, parts, price);
        String[] output = opt.optimize();

        if(output == null){
            System.out.println("No Match found");
        } else{
            for(String i : output){
                System.out.println(i);
            }
        }


    }
}

package edu.ucalgary.ensf409;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        UserIO input = new UserIO();
        int selection;
        // Initiate a menu object
        while(true){
            selection =input.menu();
            input.processInput(selection);
        }
        // Example taking in input
        //System.out.print("Please enter user input: ");
       // int in = input.readIntUntilAccepted(0, 5);
        //System.out.println("You entered: " + in);
    }
}

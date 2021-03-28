package edu.ucalgary.ensf409;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        UserIO input = new UserIO();

        int selection = input.menu();
        while(true) {
            input.processInput(selection);
            selection = input.menu();
        }

    }
}

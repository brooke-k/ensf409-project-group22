package edu.ucalgary.ensf409;

import java.util.Scanner;
/*
 * This class facilitates IO with the command-line interface
 */
public class UserIO {
    Scanner input;

    /**
     * Constructor that initializes a new System.in scanner object
     * to input
     */
    public UserIO() {
        input = new Scanner(System.in);
    }

    /**
     * String readLine() reads
     * a line of input from console, returning it as a String
     * @return String with user input to console
     */
    public String readLine() {
        // Take the next line of input
        return input.nextLine();
    }

    /**
     * int readInt() reads the next integer from
     * console
     */
    public int readInt(int min, int max) {
        int value = input.nextInt();

        return value;
    }

}

package edu.ucalgary.ensf409;

import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * This class facilitates IO with the command-line interface
 */
class UserIO {
    Scanner input;

    /**
     * int menu() will display the user menu and return the selected option
     * by the user.
     * @return int corresponding to the selected option by the user
     */
    public int menu() {
        System.out.println("1. Input a User Request");
        System.out.println("2. Change SQL Database Credentials");
        System.out.println("3. Change Output Filename");

        System.out.println("0. Exit Program");
        System.out.print("Enter your selection: ");
        return readIntUntilAccepted(0,2);
    }


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
     * int readInt() reads the next integer from System.in
     * Note, this does NOT handle InputMismatchException
     * @return int corresponding to the int the user entered
     */
    public int readInt(){
        return input.nextInt();
    }

    /**
     * int readInt(int min, int max) reads the next integer from System.in
     * with a check that it is between the specified min, max range.
     * If it is not within the min and max range it will throw a
     * InputOutOfBounds Exception.
     * Note, this does NOT handle InputMismatchException
     * @throws InputOutOfBoundsException Thrown when next user integer input is not an integer.
     * @return int corresponding to the int the user entered
     */
    public int readInt(int min, int max) throws InputOutOfBoundsException {
        int userInput = readInt();
        if(userInput < min || userInput > max) {
            throw new InputOutOfBoundsException();
        }
        return userInput;
    }

    /**
     * int readIntUntilAccepted(int min, int max) reads the next integer from System.in
     * with a check between min and max, and does it in a loop until the user
     * gives a correct integer response.
     * @return int corresponding to the valid int the user entered
     */
    public int readIntUntilAccepted(int min, int max) {
        boolean inputOK;
        int userInput = -1;
        do {
            inputOK = true;
            try {
                userInput = readInt(min, max);
            } catch (InputOutOfBoundsException | InputMismatchException e) {
                input.nextLine();
                System.out.print("That is not a valid input. Enter again: ");
                inputOK = false;
            }
        }while(!inputOK);

        return userInput;
    }

    /**
     * close will close the System.in scanner object
     */
    public void close() {
        input.close();
    }
}
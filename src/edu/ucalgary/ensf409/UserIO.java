package edu.ucalgary.ensf409;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * UserIO is a class that facilitates interaction between the user and the
 * program.
 * Provides methods for the user to make a request, alter output file and
 * MySQL settings, and interact with the database.
 * Provides methods for the terminal to be used as the input and primary output
 * that the user will receive information on the current program operations
 * through.
 */
public class UserIO {
    private Scanner input;
    private String furnType;
    private String furnCategory;
    private String numOfItems;
    private DatabaseIO databaseIO;
    private FileIO fileIO;
    private PriceOptimizer priceOpt;
    private String outputFile = "OrderOutput.txt";
    
    /**
     * The method menu will display the user menu and return the selected option
     * by the user.
     * @return int corresponding to the selected option by the user
     */
    public int menu() {
            System.out.println();
            System.out.println("1. Input a User Request");
            System.out.println("2. View Current Output Filename");
            System.out.println("3. View Current SQL Database Credentials");
            System.out.println("4. Change Output Filename");
            System.out.println("5. Change SQL Database Credentials");

            System.out.println("0. Exit Program");
            System.out.print("Enter your selection: ");
            return readIntUntilAccepted(0, 5);
    }


    /**
     * Constructor that initializes a new System.in scanner object
     * to input so the user can provide consistent input to the program.
     */
    public UserIO() {
        input = new Scanner(System.in);
        databaseIO = new DatabaseIO();
        databaseIO.createConnection();
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
     * @throws InputOutOfBoundsException Thrown when next user
     * integer input is not an integer.
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
     * int readIntUntilAccepted(int min, int max) reads the next
     * integer from System.in with a check between min and max, and
     * does it in a loop until the user gives a correct integer response.
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
     * Method processInput processes the menu to select an action to take from
     * the menu. Will call appropriate methods for action selected if a valid
     * input has been provided by the user, or will return with no action taken
     * if the user inputs an invalid input.
     * @param inputValue Request from the user.
     */
    public void processInput(int inputValue){
        switch(inputValue){
            case 1:
                readLine();
                System.out.println("\nPlease input request for " +
                        "furniture item in the form");
                System.out.println("[type] [furniture category], " +
                        "[quantity of items]");
                System.out.println("ex. Mesh chair, 1");
                System.out.println("or enter \"CANCEL\" to return to the menu");
                System.out.println("Enter request: ");
                String readFromScan = readLine();
                processUserRequest(readFromScan);
                break;
            case 2:
                System.out.println("\nCurrent output file name is: " +
                        outputFile + "\n");
                break;
            case 3:
                System.out.println("\nCurrent database URL: "
                        + databaseIO.getDbUrl());
                System.out.println("Current database username: "
                        + databaseIO.getUsername());
                System.out.println("Current database password: "
                        + hidePassword(databaseIO.getPassword()));
                break;
            case 4:
                readLine();
                System.out.println("\nThe current output file name is: \"" +
                        outputFile + "\"\n");
                System.out.println("Please the filename for order output, or enter \"CANCEL\" to cancel the operation.");
                System.out.println("New file name: ");
                String readFileName = readLine();
                updateOutputName(readFileName);
                break;
            case 5:
                updateSQLCredentials();
                break;
            case 0:
                System.out.println("\nSelected option 0. Now closing program.");
                input.close();
                System.exit(0);

        }
    }

    /**
     * Method processUserRequest is responsible for handling user input
     * when the user is placing a new order for a furniture item.
     *
     * processUserRequest will be a recursive function if the first, or
     * subsequent, entries by the user are in the invalid format for a request.
     * Will stop being called when the user places a correctly formatted,
     * valid order.
     *
     * Once a valid order has been placed, processUserRequests calls other
     * methods to provide the correct data from the database needed to
     * fulfill the order.
     *
     * processUserInput will not be a recursive function if the user
     * provides a correct input the first time processUserRequest is called.
     *
     * Provides the user with the option to exit the order at any time without
     * the order being processed by entering the String "CANCEL"
     *
     * @param userRequest String of the user's order request that is to
     *                   be processed.
     */
    public void processUserRequest(String userRequest){
        if(userRequest.equals("CANCEL")){
            System.out.println("Returning to menu. No order has been placed.");
            return;
        }
        String requestREGEX = "([A-Z][a-z]+) ([a-z]+), ([0-9]+)";
        Pattern requestPattern = Pattern.compile(requestREGEX);
        Matcher requestMatch = requestPattern.matcher(userRequest);
        boolean matchesFound = requestMatch.find();
        if(!matchesFound){
            System.out.println("Invalid input provided.");
            System.out.println("Please enter a valid input in the form");
            System.out.println("[type] [furniture category], " +
                    "[quantity of items]");
            System.out.println("Or enter \"CANCEL\" to return to the menu.\n");
            System.out.println("Enter request: ");
            processUserRequest(readLine());
        }
        else{
            furnType = requestMatch.group(1);
            furnCategory = requestMatch.group(2);
            numOfItems = requestMatch.group(3);
            if(furnCategory.equals("chair") || furnCategory.equals("desk")
                    || furnCategory.equals("filing")
                    || furnCategory.equals("lamp")){
                if(databaseIO.typeExists(furnType)){
                    checkFurniture();
                    String[] temp = priceOpt
                            .optimize(Integer.parseInt(numOfItems));
                    if(temp!=null){
                        for(String t: temp){
                            databaseIO.removeItem(furnCategory, t);
                        }
                        fileIO = new FileIO(outputFile,temp,userRequest,
                                priceOpt.getCurrentCost());
                    }
                    else{
                        fileIO = new FileIO(databaseIO
                                .suggestedManufacturers(furnCategory));
                    }
                } else {
                    System.out.println("Invalid type, try again.");
                    processUserRequest(readLine());
                }
            } else {
                System.out.println("Invalid category, try again.");
                processUserRequest(readLine());
            }


        }
    }

    /**
     * Method checkFurniture checks that the furniture category that has been
     * read by processUserInput is a valid furniture category.
     *
     * If the furniture category that has been read by processUserRequest is
     * valid, the method will call the instance of PriceOptimizer, priceOpt,
     * for provided furniture category and return.
     *
     * If the furniture category that has been read by processUserRequest is
     * not valid, checkFurniture will print "error" to the terminal and return.
     */
    public void checkFurniture(){
        switch (this.furnCategory) {
            case "chair":
                this.priceOpt = databaseIO.getChairData(this.furnType);
                break;
            case "desk":
                this.priceOpt = databaseIO.getDeskData(this.furnType);
                break;
            case "lamp":
                this.priceOpt = databaseIO.getLampData(this.furnType);
                break;
            case "filing":
                this.priceOpt = databaseIO.getFilingData(this.furnType);
                break;
            default:
                System.out.println("error");
        }
    }

    /**
     * Method updateOutputName allows the user to update their preferred name of
     * the output file that the order will be written to.
     *
     * Checks to ensure that the user provides a valid file name for the new
     * output.
     *
     * Does not update the current output file name until the user
     * has confirmed their changes.
     *
     * @param newFileName String of the new file name the user intends to
     *                    replace the current output file name with.
     */
    public void updateOutputName(String newFileName){
        if(newFileName.equals("CANCEL")){
            System.out.println("Returning to menu. Order output file name " +
                    "has not been updated.");
            return;
        }else{
            File checkFile = new File(newFileName);
            if(checkFile.isDirectory()){
                System.out.println("\n\nThe provided name is not a valid " +
                        "file.");
                System.out.println("Please enter a valid file name or enter " +
                        "\"CANCEL\" to return to the menu");
                System.out.println("Current order output file name: "
                        + this.outputFile);
                System.out.println("New order output file name: ");
                updateOutputName(readLine());
            }
            else{
                String newOutputFileName = newFileName;
                System.out.println("New order output file name is \"" +
                        newOutputFileName + "\"");
                System.out.println("Save update? (Y/N): ");
                String saveResponse = readLine();
                while(!saveResponse.equals("Y") && !saveResponse.equals("N")){
                    System.out.println("Save update? (Y/N): ");
                    saveResponse = readLine();
                }
                if(saveResponse.equals("N")){
                    System.out.println("Returning to menu. Order output file " +
                            "name has not been updated.");
                    return;
                }
                else{
                    this.outputFile = newOutputFileName;
                    System.out.println("\nOrder output file name has been " +
                            "updated to " + this.outputFile);
                    return;
                }
            }
        }
    }

    /**
     * Method updateSQLCredentials is a method for handling updates the the
     * MySQL credentials from the terminal by taking user inputs one at a time
     * and saving them locally until the user makes the final decision to update
     * the credentials.
     *
     * User is able to exit the method and return to the menu at any time by
     * providing the input "CANCEL" in any line.
     */
    public void updateSQLCredentials(){
        readLine();
        System.out.println("\n\nWarning: Altering the current MySQL " +
                "credentials \nmay make the database unreachable.");
        System.out.println("\nDo you want to proceed? (Y/N):");
        String updateString = readLine();
        while(!updateString.equals("Y") && !updateString.equals("N")){
            System.out.println("Do you want to proceed? (Y/N):");
            updateString = readLine();
        }
        if(updateString.equals("N")){
            System.out.println("\nReturning to menu. " +
                    "No changes have been made.");
            return;
        }
        else{
            String newURL;
            System.out.println("\nEnter \"CANCEL\" at any time to return to " +
                    "menu \nwithout updating MySQL credentials.");
            System.out.println("Please enter the new database URL: ");
            updateString = readLine();
            if(updateString.equals("CANCEL")){
                System.out.println("\nReturning to menu. " +
                        "No changes have been made.");
                return;
            }
            else{
                newURL = updateString;
            }
            String newUser;
            System.out.println("\nPlease enter the new database username: ");
            updateString = readLine();
            if(updateString.equals("CANCEL")){
                System.out.println("\nReturning to menu. " +
                        "No changes have been made.");
                return;
            } else {
                newUser = updateString;
            }
            String newPassword;
            System.out.println("Please enter the new database password: ");
            updateString = readLine();
            if(updateString.equals("CANCEL")) {
                System.out.println("\nReturning to menu. " +
                        "\nNo changes have been made.");
                return;
            } else {
                newPassword = updateString;
            }
            System.out.println("\nOld MySQL credentials are");
            System.out.println("  URL: " + databaseIO.getDbUrl());
            System.out.println("  Username: " + databaseIO.getUsername());
            System.out.println("  Password: " + hidePassword(databaseIO
                    .getPassword()));
            System.out.println();
            System.out.println("New MySQL credentials are");
            System.out.println("  URL: " + newURL);
            System.out.println("  Username: " + newUser);
            System.out.println("  Password: " + hidePassword(newPassword));
            System.out.println();
            System.out.println("Update MySQL credentials? (Y/N):");
            updateString = readLine();
            while(!updateString.equals("Y") && !updateString.equals("N")){
                System.out.println("Update MySQL credentials? (Y/N):");
                updateString = readLine();
            }
            if(updateString.equals("N")){
                System.out.println("\nReturning to menu. " +
                        "\nMySQL credentials have not been updated.");
                return;
            }
            else {
                databaseIO.updateCredentials(newURL, newUser, newPassword);
                System.out.println("\nMySQL credentials have been " +
                        "\nupdated successfully.");
                System.out.println("Returning to menu.");
                return;

            }
        }
    }


    /**
     * Method hidePassword hides passwords by replacing all characters with a
     * uniform substitute, intended for displaying in the terminal to the user.
     * Returns a string of the same length as provided String password, but
     * with each character replaced with the '*' character.
     * @param password String password to be hidden
     * @return String password, but with each character replaced with the '*'
     * character.
     */
    private String hidePassword(String password){
        StringBuilder hidden = new StringBuilder();
        for(int i = 0; i<password.length(); i++){
            hidden.append("*");
        }
        return hidden.toString();
    }

    /**
     * Method close closes the scanner input.
     */
    public void close() {
        input.close();
    }
}
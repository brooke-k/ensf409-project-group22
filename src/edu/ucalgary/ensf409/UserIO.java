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
    private String latestRequest;
    private PriceOptimizer priceOpt;
    private String outputFile = "OrderOutput.txt";
    
    /**
     * The method menu will display the user menu and return the selected option
     * by the user.
     * @return int corresponding to the selected option by the user
     */
    public int menu() {
            pressEnterToContinue();
            System.out.println();
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("1. Input a User Request");
            System.out.println("2. View Current Output Filename");
            System.out.println("3. View Current SQL Database Credentials");
            System.out.println("4. Change Output Filename");
            System.out.println("5. Change SQL Database Credentials");

            System.out.println("0. Exit Program");
            System.out.println();
            System.out.print("Enter your selection: ");
            return readIntUntilAccepted(0, 5);
    }

    /**
     * Method for loading the introduction to the program, before
     * giving the user the option to pick through the menu.
     * giving the user the option to pick through the menu.
     */
    public void firstMenu(){
        System.out.println();
        System.out.println("Welcome to the Supply Chain Manager for");
        System.out.println("recycled and second hand furniture.");
        System.out.println();
        System.out.println("Press ENTER after every prompted entry ");
        System.out.println("and type \"CANCEL\" in any operation ");
        System.out.println("to return to the main menu.");
    }



    /**
     * Constructor that initializes a new System.in scanner object
     * to input so the user can provide consistent input to the program.
     */
    public UserIO() {
        input = new Scanner(System.in);
        databaseIO = new DatabaseIO();
        furnType = null;
        furnCategory = null;
        latestRequest = null;
        numOfItems = null;
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
                System.out.println();
                System.out.print("Please only enter a number from 0 to 5.");
                System.out.println();
                System.out.println("1. Input a User Request");
                System.out.println("2. View Current Output Filename");
                System.out.println("3. View Current SQL Database Credentials");
                System.out.println("4. Change Output Filename");
                System.out.println("5. Change SQL Database Credentials");
                System.out.println("0. Exit Program");
                System.out.println();
                System.out.println("Selection:");
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
    public boolean processInput(int inputValue){
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println();
        switch(inputValue){
            case 1:
                databaseIO.createConnection();
                setReadValuesNull();

               boolean connectionMade = databaseIO.createConnection();
               if(!connectionMade) {
                   System.out.println("Returning to menu. " +
                           "No order has been placed.");
                   return true;
               }
                System.out.println("\nPlease input request for " +
                        "furniture item in the form");
                System.out.println("[type] [furniture category], " +
                        "[quantity of items]");
                System.out.println("ex. Mesh chair, 1");
                System.out.println("or enter \"CANCEL\" to return to the menu");
                System.out.println("Enter request: ");
                readLine();
                String readFromScan = readLine();
                processUserRequest(readFromScan);
                return true;
            case 2:
                System.out.println("\nCurrent output file name is: " +
                        outputFile + "\n");
                readLine();
                return true;
            case 3:
                System.out.println("\nCurrent database URL: "
                        + databaseIO.getDbUrl());
                System.out.println("Current database username: "
                        + databaseIO.getUsername());
                System.out.println("Current database password: "
                        + hidePassword(databaseIO.getPassword()));
                readLine();
                return true;
            case 4:
                readLine();
                System.out.println("\nThe current output file name is: \"" +
                        outputFile + "\"\n");
                System.out.println("Please the filename for order output, or " +
                        "enter \"CANCEL\" to cancel the operation.");
                    System.out.println("New file name: ");
                    String readFileName = readLine();
                updateOutputName(readFileName);
                return true;
            case 5:
                updateSQLCredentials();
                return true;
            case 0:
                System.out.println();
                System.out.println("Thank you for using Supply Chain Manager.");
                System.out.println("Now closing the program.");
                return false;

        }
        return true;
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

        String requestREGEX1 = "([A-Z][a-z]+) ([a-z]+), ([0-9]+)";
        String requestREGEX2 = "([A-Z][a-z]+ [A-Z][a-z]+) ([a-z]+), ([0-9]+)";

        Pattern requestPattern1 = Pattern.compile(requestREGEX1);
        Matcher requestMatch1 = requestPattern1.matcher(userRequest);
        boolean matchesFound1 = requestMatch1.find();

        Pattern requestPattern2 = Pattern.compile(requestREGEX2);
        Matcher requestMatch2 = requestPattern2.matcher(userRequest);
        boolean matchesFound2 = requestMatch2.find();
        if(!matchesFound1 && !matchesFound2){
            String spellingCapErrREGEX ="([A-Za-z]+[ ]){0,2}" +
                                         "([A-Za-z]+), ([0-9]+)";
            Pattern spellingCapErrPat = Pattern.compile(spellingCapErrREGEX);
            Matcher spelCapErrMat = spellingCapErrPat.matcher(userRequest);
            boolean spelCapErrMatFound = spelCapErrMat.find();
            if(!spelCapErrMatFound){
                System.out.println();
                System.out.println("Order could not be recognized.");
            } else if (spelCapErrMat.group(0).length() != (userRequest.length())){
                System.out.println();
                System.out.println("Order could not be recognized.");
            }
            else {
                System.out.println();
                System.out.println("Please make sure that your capitalization");
                System.out.println("matches that of the provided examples.");
                System.out.println("Example One: Mesh chair, 3");
                System.out.println("Example Two: Swing Arm lamp, 1");
                pressEnterToContinue();
            }

            System.out.println();
            System.out.println("Please enter your order in the form");
            System.out.println("[type] [furniture category], " +
                    "[quantity of items]");
            System.out.println("Or enter \"CANCEL\" to return to the menu.\n");
            System.out.println("Enter order: ");
            processUserRequest(readLine());
        }


        else{
            if(matchesFound2) {
                if(requestMatch2.group(0).length()!=userRequest.length()){
                    System.out.println();
                    System.out.println("Order could not be recognized.");
                    System.out.println();
                    System.out.println("Please enter your order in the form");
                    System.out.println("[type] [furniture category], " +
                                       "[quantity of items]");
                    System.out.println("Or enter \"CANCEL\" to return to the menu.\n");
                    System.out.println("Enter order: ");
                    processUserRequest(readLine());
                    return;
                } else {
                    furnType = requestMatch2.group(1);
                    furnCategory = requestMatch2.group(2);
                    numOfItems = requestMatch2.group(3);
                    latestRequest = userRequest;
                }
            }else{
                if(matchesFound1 && requestMatch1.group(0).length()!=userRequest.length()){
                    System.out.println();
                    System.out.println("Order could not be recognized.");
                    System.out.println();
                    System.out.println("Please enter your order in the form");
                    System.out.println("[type] [furniture category], " +
                                       "[quantity of items]");
                    System.out.println("Or enter \"CANCEL\" to return to the menu.\n");
                    System.out.println("Enter order: ");
                    processUserRequest(readLine());
                    return;
                } else {
                    furnType = requestMatch1.group(1);
                    furnCategory = requestMatch1.group(2);
                    numOfItems = requestMatch1.group(3);
                    latestRequest = userRequest;
                }
            }
            if(furnCategory.equals("chair") || furnCategory.equals("desk")
                    || furnCategory.equals("filing")
                    || furnCategory.equals("lamp")){
                if(databaseIO.typeExists(furnCategory, furnType)){
                    checkFurniture();
                    String[] temp = priceOpt
                            .optimize(Integer.parseInt(numOfItems));
                    if(temp!=null){
                        for(String t: temp){
                            //databaseIO.removeItem(furnCategory, t);
                        }
                        fileIO = new FileIO(outputFile,temp,userRequest,
                                priceOpt.getCurrentCost());
                    }
                    else{
                        fileIO = new FileIO(databaseIO
                                .suggestedManufacturers(furnCategory));
                    }
                } else {
                    System.out.println();
                    System.out.println("The furniture type " + furnType  +
                                       "could ");
                    System.out.println("not be found in the " +
                                       "current database.");
                    pressEnterToContinue();
                    System.out.println();
                    System.out.println("Please enter your order in the form");
                    System.out.println("[type] [furniture category], " +
                                       "[quantity of items]");
                    System.out.println("Or enter \"CANCEL\" to return to the menu.\n");
                    System.out.println("Enter order: ");
                    processUserRequest(readLine());
                }
            } else {
                System.out.println();
                System.out.println("The category " + furnCategory  +
                                   "could ");
                System.out.println("not be found in the " +
                                   "current database.");
                pressEnterToContinue();
                System.out.println();
                System.out.println("Please enter your order in the form");
                System.out.println("[type] [furniture category], " +
                                   "[quantity of items]");
                System.out.println("Or enter \"CANCEL\" to return to the menu.\n");
                System.out.println("Enter order: ");
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
                System.out.println("New order output file name is \"" +
                        newFileName + "\"");
                System.out.println("Save update? (Y/N): ");
                String saveResponse = readLine();
                while(!saveResponse.equals("Y") && !saveResponse.equals("N")){
                    System.out.println("Save update? (Y/N): ");
                    saveResponse = readLine();
                }
                if(saveResponse.equals("N")){
                    System.out.println("Returning to menu. Order output file " +
                            "name has not been updated.");
                }
                else{
                    this.outputFile = newFileName;
                    System.out.println("\nOrder output file name has been " +
                            "updated to " + this.outputFile);
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
                "credentials \nmay make the database unreachable.\n");
        System.out.println("\nDo you want to proceed? (Y/N):");
        String updateString = readLine();
        while(!updateString.equals("Y") && !updateString.equals("N")){
            System.out.println("Do you want to proceed? (Y/N):");
            updateString = readLine();
        }
        if(updateString.equals("N")){
            System.out.println("\nReturning to menu. " +
                    "No changes have been made.");
        }
        else{
            String newURL;
            System.out.println("\nEnter \"CANCEL\" at any time to return to " +
                    "menu \nwithout updating MySQL credentials.\n");
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
            System.out.println("Please enter the new database username: ");
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
            }
            else {
                databaseIO.updateCredentials(newURL, newUser, newPassword);
                System.out.println("\nMySQL credentials have been " +
                        "\nupdated.");
                System.out.println("Returning to menu.");
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
     * Getter method for the latest user request made for an order
     * @return Most recent valid order request from user
     */
    public String getLatestRequest(){
        return latestRequest;
    }

    /**
     * Getter method for the furniture type of the latest valid order request
     * @return Latest valid order furniture type
     */
    public String getFurnType(){
        return furnType;
    }

    /**
     * Getter method for the number of items of the latest valid order request
     * @return Latest valid order request number of items
     */
    public String getNumOfItems(){
        return numOfItems;
    }

    /**
     * Getter method for the furniture category of the latest valid order request
     * @return Latest valid order request furniture category
     */
    public String getFurnCategory(){
        return furnCategory;
    }

    /**
     * Getter method for the current output file the order will be written to
     * @return Currently set order output file
     */
    public String getOutputFile(){
        return outputFile;
    }

    /**
     * Method close closes the scanner input.
     */
    public void close() {
        input.close();
    }


    /**
     * Method for creating a space in terminal for the user to press enter
     * before continuing the program.
     * Intended to allow the user a chance to look at the loaded data
     * before the menu is loaded again and moving the information away
     * from the user's focus.
     */
    private void pressEnterToContinue(){
        boolean pressed = false;
        String returned = null;
        while(!pressed) {
            System.out.println();
            System.out.println("Press ENTER to continue");
            returned = readLine();
            if (returned != null) {
                pressed = true;
            }
        }
    }



    /**
     * Private method setReadValuesNull sets the values for
     * furnType, furnCategory, latestRequest, and numofItems to
     * null.
     *
     * Used in the case of an invalid order being placed.
     */
    private void setReadValuesNull(){
        furnType = null;
        furnCategory = null;
        latestRequest = null;
        numOfItems = null;
    }
}
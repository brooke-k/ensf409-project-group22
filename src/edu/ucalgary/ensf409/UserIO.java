package edu.ucalgary.ensf409;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class facilitates IO with the command-line interface
 */
public class UserIO {
    private Scanner input;
    private String furnType;
    private String furnCategory;
    private String numOfItems;
    private DatabaseIO dbIO;
    private FileIO fileIO;
    private PriceOptimizer priceOpt;
    private String outputFile = "OrderOutput.txt";

    /**
     * int menu() will display the user menu and return the selected option
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
     * to input
     */
    public UserIO() {
        input = new Scanner(System.in);
        dbIO = new DatabaseIO();
        dbIO.createConnection();
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
     * @throws InputOutOfBoundsException Thrown when next user integer
     * input is not an integer.
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
     * int readIntUntilAccepted(int min, int max)
     * reads the next integer from System.in
     * with a check between min and max, and does it in a loop
     * until the user
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

    public void processInput(int inputValue){
        switch(inputValue){
            case 1:
                readLine();
                System.out.println("\nPlease input request for " +
                        "furniture item in the form");
                System.out.println("[type] [furniture category], " +
                        "[quantity of items]");
                System.out.println("ex. Mesh chair, 1");
                System.out.println("Enter request: ");
                String readFromScan = readLine();
                processUserRequest(readFromScan);
                break;
            case 2:
                System.out.println("\nCurrent output file name is: "
                        + outputFile + "\n");
                break;
            case 3:
                System.out.println("\nCurrent database URL: "
                        + dbIO.getDbUrl());
                System.out.println("Current database username: "
                        + dbIO.getUsername());
                System.out.println("Current database password: "
                        + dbIO.getPassword());
                break;
            case 4:
                System.out.println("\nSelected option 4\n");
                break;
            case 5:
                System.out.println("\nSelected option 5\n");
                break;
            case 0:
                System.out.println("\nSelected option 0. " +
                        "Now closing program.");
                input.close();
                System.exit(0);

        }
    }

    public void processUserRequest(String userRequest){
        String requestREGEX = "([A-Z][a-z]+) ([a-z]+), ([0-9]+)";
        Pattern requestPattern = Pattern.compile(requestREGEX);
        Matcher requestMatch = requestPattern.matcher(userRequest);
        boolean matchesFound = requestMatch.find();
        if(!matchesFound){
            System.out.println("Invalid input provided.");
            processUserRequest(readLine());
        }
        else{
            furnType = requestMatch.group(1);
            furnCategory = requestMatch.group(2);
            numOfItems = requestMatch.group(3);

            // System.out.println("Furniture type: " + furnitureType);
            // System.out.println("Furniture category: " + furnitureCategory);
            // System.out.println("Number of items: " + numberOfItems);
            checkFurniture();
            String[] temp = priceOpt.optimize(Integer.parseInt(numOfItems));

            if(temp!=null){
                fileIO = new FileIO(outputFile,temp,userRequest,
                        priceOpt.getCurrentCost());
            }
            else{
                fileIO = new FileIO(dbIO.suggestedManufacturers(furnCategory));
            }
            //System.out.println(Arrays.toString(temp));


        }

        //System.out.println("In process read \"" + userRequest + "\"");
    }

    public void checkFurniture(){
        switch (this.furnCategory) {
            case "chair":
                this.priceOpt = dbIO.getChairData(this.furnType);
                break;
            case "desk":
                this.priceOpt = dbIO.getDeskData(this.furnType);
                break;
            case "lamp":
                this.priceOpt = dbIO.getLampData(this.furnType);
                break;
            case "filing":
                this.priceOpt = dbIO.getFilingData(this.furnType);
                break;
            default:
                System.out.println("error");
        }
    }



    /**
     * close will close the System.in scanner object
     */
    public void close() {
        input.close();
    }
}
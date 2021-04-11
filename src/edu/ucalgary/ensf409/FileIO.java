package edu.ucalgary.ensf409;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * FileIO is a class used for formatting and writing the output from the
 * order to a .txt file, if the order was successful, and for creating and
 * printing an appropriate console message to the console informing them of
 * the order status.
 */
public class FileIO {
    private File outputFile;            // File to be written to as the order
                                         // form
    private String[] itemsOrdered;      // ID numbers of the items that were
                                         // purchased to fulfill the order
    private String originalRequest;     // The original order request the user
                                         // made
    private int orderCost;              // Total cost of the order
    private String orderOutputString;   // String to be written to the order
                                         // output form
    private String consoleOutputString; // String to be printed to the console
    private ArrayList<String> manufacturer; // List of manufacturers to be
                                            // suggest for alternative supply

    /**
     * Constructor for FileIO in the case of an unfulfilled order.
     * Creates an output message to the console that
     * includes the suggested alternative manufacturer(s).
     * @param manufacturer Arraylist of length 1+ elements that contains
     *                     all suggested manufacturers for the item
     *                     order that couldn't be fulfilled
     */
    public FileIO(ArrayList<String> manufacturer){
        this.manufacturer = manufacturer;
        // Constructs the FileIO object and creates an output for an
        // unfulfilled order at the same time.
        createUnfulfilledOutput();
    }


    /**
     * Constructor for FileIO in the case of a fulfilled order. Creates an
     * output message to the console that contains the items that have been
     * ordered and generates an output file with the name specified where
     * the order form will be written to.
     * @param outputFileName String name of the file where the order form is
     *                       to be written to
     * @param itemsOrdered String array of item ID's that were purchased to
     *                     fulfill the order
     * @param originalRequest String of the original request made by the user
     * @param orderCost int of the total cost of the order in dollars
     */
    public FileIO(String outputFileName, String[] itemsOrdered,
                  String originalRequest, int orderCost){
        // Constructs the FileIO object and creates a fulfilled output
        // at the same time.
        this.itemsOrdered = itemsOrdered;
        this.originalRequest = originalRequest;
        this.orderCost = orderCost;
        this.outputFile = new File(outputFileName);
        createFulfilledOutput();
    }

    /**
     * Method for handling the output for an order that is not fulfilled.
     * Notifies the user the order could not be filled,
     * then provides a list of suggested manufacturers that produce the product
     * they requested to order.
     * Output is provided only in the terminal.
     * No order form is created.
     *
     * @return the String printed to the console regarding the order output
     */
    public String createUnfulfilledOutput(){
        StringBuilder outputString = new StringBuilder();
        outputString.append("\nOrder could not be fulfilled based " +
                "on current inventory.");
        outputString.append("\n");
        if(manufacturer.size() == 1){
            // Provides proper grammar for when only one manufacturer is
            // suggested.
            outputString.append("The suggested manufacturer for this " +
                    "order is: \n");
            outputString.append(manufacturer.get(0));
            outputString.append(".");
            consoleOutputString = outputString.toString();
        }
        else if(manufacturer.size() == 2){
            // Provides proper grammar for when two manufacturers are
            // suggested.
            outputString.append("Suggested manufacturers for this order are: " +
                    "\n");
            outputString.append(manufacturer.get(0));
            outputString.append(" and ");
            outputString.append(manufacturer.get(1));
            outputString.append(".");
            consoleOutputString = outputString.toString();
        }
        else{
            // Provides proper grammar for when three or more manufacturers
            // are suggested.
            outputString.append("Suggested manufacturers for this order are: " +
                    "\n");
            for(int i = 0; i<manufacturer.size()-1; i++){
                outputString.append(manufacturer.get(i));
                outputString.append(", \n");
            }
            outputString.append("and ");
            outputString.append(manufacturer.get(manufacturer.size()-1));
            outputString.append(".");
            consoleOutputString = outputString.toString();
        }
        // Prints the message regarding the order's lack of success to the
        // terminal for the user to see, including suggested
        // manufacturers.
        System.out.println(consoleOutputString);
        // Returns the string that was printed to the console
        return consoleOutputString;
    }

    /**
     * Method for handling the output for an order that has been fulfilled.
     * Informs the user in the terminal that the order was successful,
     * followed by a list of the IDs of the components that were used to
     * fulfill the order and the total cost of the order.
     * Creates and formats the output for the order form, containing blank
     * spaces for faculty, contact, and date, the original request, the IDs of
     * the ordered components, and the total cost of the order.
     * Calls the private method printOutputs to display console output and
     * write to the order form.
     */
    public void createFulfilledOutput(){
        StringBuilder consoleBuilder = new StringBuilder();
        StringBuilder fileBuilder = new StringBuilder();

    consoleBuilder.append("\n");
    consoleBuilder.append("Order successful.");
    consoleBuilder.append("\n");
    // Construction for the message that will be printed to the console
        if(itemsOrdered.length == 1){
            // Provides proper grammar if only one component was purchased.
            consoleBuilder.append("Purchased component: ");
            consoleBuilder.append(itemsOrdered[0]);

        } else if(itemsOrdered.length == 2){
            // Provides proper grammar if exactly two components were purchased
            consoleBuilder.append("Purchased components: ");
            consoleBuilder.append(itemsOrdered[0]);
            consoleBuilder.append(" and ");
            consoleBuilder.append(itemsOrdered[1]);

        } else{
            // Provides proper grammar if three or more components were
            // purchased.
            consoleBuilder.append("Purchased components: ");
            for(int i = 0; i<itemsOrdered.length -1; i++){
                consoleBuilder.append(itemsOrdered[i]);
                consoleBuilder.append(", ");
            }
            consoleBuilder.append("and ");
            consoleBuilder.append(itemsOrdered[itemsOrdered.length-1]);

        }

        // Construction for the String that will be written into the order
        // output file containing details about the order.
        consoleBuilder.append(" for $");
        consoleBuilder.append(orderCost);
        consoleOutputString = consoleBuilder.toString();

        fileBuilder.append("SCM Order Form");
        fileBuilder.append("\n\n");
        fileBuilder.append("Faculty Name: ");
        fileBuilder.append("\n");
        fileBuilder.append("Contact: ");
        fileBuilder.append("\n");
        fileBuilder.append("Date: ");
        fileBuilder.append("\n\n");
        fileBuilder.append("Original Request: ");
        fileBuilder.append(originalRequest);
        fileBuilder.append("\n\n");
        fileBuilder.append("Items Ordered:");

        // Lists the items that were purchased to fulfill the user's order
        for (String s : itemsOrdered) {
            fileBuilder.append("\n");
            fileBuilder.append("  ID: ");
            fileBuilder.append(s);
        }
        fileBuilder.append("\n\n");
        fileBuilder.append("Total price of order: $");
        fileBuilder.append(orderCost);

        // Saves the string that was printed to the order form for
        // future reference.
        orderOutputString = fileBuilder.toString();

        printOutputs();
    }

    /**
     * Method for printing the user order update to the console
     * and for writing the order form to the output file.
     */
    private void printOutputs(){

        // Tries to write the order's output form to the order output
        // file
        try {
            BufferedWriter outWriter = new BufferedWriter(
                    new FileWriter(outputFile));
            outWriter.write(orderOutputString);
            outWriter.close();
        }catch(IOException e){
            System.err.println("Output file could not be created " +
                    "for order form.");
        }

        // Prints the message with details about the successful order to the
        // console for the user to see.
        System.out.println(consoleOutputString);
    }

    /**
     * Getter method for the generated consoleOutputString
     * @return String intended to be printed to the console
     */
    public String getConsoleOutputString(){
        return this.consoleOutputString;
    }

    /**
     * Getter method for the generated orderOutputString
     * @return String intended to be written to the order output file
     */
    public String getOrderOutputString(){
        return this.orderOutputString;
    }
}

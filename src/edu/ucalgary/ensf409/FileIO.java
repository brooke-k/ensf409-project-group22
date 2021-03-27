package edu.ucalgary.ensf409;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileIO is a class used for formatting and writing the output from the order to a .txt file
 *
 */
public class FileIO {
    private File outputFile;
    private String[] itemsOrdered;
    private String originalRequest;
    private int orderCost;
    private String orderOutputString;
    private String consoleOutputString;
    private String[] manufacturer;

    /**
     * Constructor for FileIO in the case of an unfulfilled order. Creates an output message to the console that
     * includes the suggested alternative manufacturer(s).
     * @param manufacturer String array of length 1+ elements that contains all suggested manufacturers for the item
     *                     order that couldn't be fulfilled
     */
    public FileIO(String[] manufacturer){
        this.manufacturer = manufacturer;
        createUnfulfilledOutput();
    };


    /**
     * Constructor for FileIO in the case of a fulfileld order. Creates an output message to the console that
     * contains the items that have been ordered and generates an output file with the name specifed where
     * the order form will be written to.
     * @param outputFileName String name of the file where the order form is to be written to
     * @param itemsOrdered String array of item ID's that were purchased to fulfill the order
     * @param originalRequest String of the original request made by the user
     * @param orderCost int of the total cost of the order in dollars
     */
    public FileIO(String outputFileName, String[] itemsOrdered, String originalRequest, int orderCost){
        this.itemsOrdered = itemsOrdered;
        this.originalRequest = originalRequest;
        this.orderCost = orderCost;
        this.outputFile = new File(outputFileName);
        createFulfilledOutput();
    };

    /**
     * Constructor for FileIO in the case of a fulfileld order. Creates an output message to the console that
     * contains the items that have been ordered and generates an output file with the name "OrderOutput.txt" in
     * the immediate local directory.
     * @param itemsOrdered String array of item ID's that were purchased to fulfill the order
     * @param originalRequest String of the original order request made by the user
     * @param orderCost int of the total cost of the order in dollars
     */
    public FileIO(String[] itemsOrdered, String originalRequest, int orderCost){
        this("OrderOutput.txt", itemsOrdered,originalRequest,orderCost);
    };

    /**
     * Method for handling the output for an order that is not fulfilled.
     * Notifies the user the order could not be filled,
     * then provides a list of suggested manufacturers that produce the product
     * they requested to order.
     * Output is provided only in the terminal.
     * No order form is created.
     */
    public void createUnfulfilledOutput(){
        StringBuilder outputString = new StringBuilder();
        outputString.append("Order could not be fulfilled based on current inventory.");
        outputString.append("\n");
        if(manufacturer.length == 1){
            outputString.append("The suggested manufacturer for this order is ");
            outputString.append(manufacturer[0] + ".");
            consoleOutputString = outputString.toString();
        }
        else if(manufacturer.length == 2){
            outputString.append("Suggested manufacturers for this order are ");
            outputString.append(manufacturer[0] + " and " + manufacturer[1]);
            consoleOutputString = outputString.toString();
        }
        else{
            outputString.append("Suggested manufacturers for this order are ");
            for(int i = 0; i<manufacturer.length-1; i++){
                outputString.append(manufacturer[i] + ", ");
            }
            outputString.append("and " +manufacturer[manufacturer.length-1]);
            consoleOutputString = outputString.toString();
        }
        System.out.println(consoleOutputString);
        return;
    }

    /**
     * Method for handling the output for an order that has been fulfilled.
     * Informs the user in the terminal that the order was sucessful, followed by a list
     * of the IDs of the components that were used to fulfill the order and the total cost of the order.
     * Creates and formats the output for the order form, containing blank spaces for faculty, contact,
     * and date, the original request, the IDs of the ordered components, and the total cost of the order.
     *
     * Calls the private method printOutputs to display console output and write to the order form.
     */
    public void createFulfilledOutput(){
        StringBuilder consoleBuilder = new StringBuilder();
        StringBuilder fileBuilder = new StringBuilder();

    consoleBuilder.append("\n");
    consoleBuilder.append("Order sucessful.");
    consoleBuilder.append("\n");
        if(itemsOrdered.length == 1){
            consoleBuilder.append("Purchased component: ");
            consoleBuilder.append(itemsOrdered[0]);

        } else if(itemsOrdered.length == 2){
            consoleBuilder.append("Purchased components: ");
            consoleBuilder.append(itemsOrdered[0] + " and " + itemsOrdered[1]);

        } else{
            consoleBuilder.append("Purchased components: ");
            for(int i = 0; i<itemsOrdered.length -1; i++){
                consoleBuilder.append(itemsOrdered[i] + ", ");
            }
            consoleBuilder.append("and " + itemsOrdered[itemsOrdered.length-1]);

        }

        consoleBuilder.append(" for $" + orderCost);
        consoleOutputString = consoleBuilder.toString();

        fileBuilder.append("SCM Order Form");
        fileBuilder.append("\n\n");
        fileBuilder.append("Faculty Name: ");
        fileBuilder.append("\n");
        fileBuilder.append("Contact: ");
        fileBuilder.append("\n");
        fileBuilder.append("Date: ");
        fileBuilder.append("\n\n");
        fileBuilder.append("Original Request: " + originalRequest);
        fileBuilder.append("\n\n");
        fileBuilder.append("Items Ordered:");

        for(int i = 0; i< itemsOrdered.length; i++){
            fileBuilder.append("\n");
            fileBuilder.append("  ID: " + itemsOrdered[i]);
        }
        fileBuilder.append("\n\n");
        fileBuilder.append("Total price of order: $" + orderCost);

        orderOutputString = fileBuilder.toString();

        printOutputs();

        return;


    }

    /**
     * Method for printing the user order update to the console and for writing the order
     * form to the output file.
     */
    private void printOutputs(){
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(outputFile));
            outWriter.write(orderOutputString);
            outWriter.close();
        }catch(IOException e){
            System.err.println("Output file could not be created for order form.");
        }

        System.out.println(consoleOutputString);
    }
}

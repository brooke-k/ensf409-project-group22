package edu.ucalgary.ensf409;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileIO is a class used for formatting and writing the output from the order to a .txt file
 * Needs to be completed based on Component
 */
public class FileIO {
    private String outputFileName;
    private File outputFile;
    private BufferedWriter outWriter;
    private String[] itemsOrdered;
    private String originalRequest;
    private int orderCost;
    private String orderOutputString;
    private String consoleOutputString;

    /**
     *
     * @param outputFileName
     * @param itemsOrdered
     * @param originalRequest
     * @param orderCost
     */
    public FileIO(String outputFileName, String[] itemsOrdered, String originalRequest, int orderCost){
        this.outputFileName = outputFileName;
        for(int i = 0; i<itemsOrdered.length; i++){
            this.itemsOrdered[i] = itemsOrdered[i];
        }
        this.originalRequest = originalRequest;
        this.orderCost = orderCost;
        outputFile = new File(outputFileName);
        createOutput();
    };

    /**
     * Constructor for FileIO without the output file name for the order being specifed.
     * Will automatically create and use an output file with the name "AutomaticOrderOutput.txt"
     * @param itemsOrdered String array of components that were ordered
     * @param originalRequest String of the original user request
     * @param orderCost int of the total calcuated cost of the order
     */
    public FileIO(String[] itemsOrdered, String originalRequest, int orderCost){
        this("AutomaticOrderOutput.txt", itemsOrdered, originalRequest, orderCost);
    };

    public void createOutput(boolean orderSuccessful){
        if(!orderSuccessful){
        }
    }

    public void createOutput(){
        try{outWriter = new BufferedWriter(new FileWriter(outputFile));
        } catch(IOException e){
            System.err.println("Order output was unable to be created because output file could not be opened");
        }

        createOutputString();

        try{
            outWriter.write(orderOutputString);
        } catch(IOException e){
            System.err.println("Order output was unable to be written to the output file " + outputFileName);
        }
        finally {
            try {
                outWriter.close();
                System.out.println(consoleOutputString);
            }
            catch(IOException e){
                System.err.println("Order output file " + outputFileName + " was unable to be closed.");
            }
        }

    }

    public void createOutputString(){
        StringBuilder outFileString = new StringBuilder();
        StringBuilder consoleFileString = new StringBuilder();

        outFileString.append("Furniture Order Form");
        outFileString.append("\n\n\n");
        outFileString.append("Faculty Name: ");
        outFileString.append("\n");
        outFileString.append("Contact: ");
        outFileString.append("\n\n\n");
        outFileString.append("Original Order: " + originalRequest);
        outFileString.append("\n\n\n");
        outFileString.append("Items Ordered: ");
        outFileString.append("\n");
       /*
            This is all subject to change depending
            on how the component class is arranged
            and needs to be altered later.



        */




        consoleFileString.append("\n");
        consoleFileString.append("Purchased: ");

        consoleFileString.append(itemsOrdered[0]);

        if(itemsOrdered.length == 1 && !itemsOrdered[0].equals("None")){
            consoleFileString.append(" for $" + orderCost);
        }
        else if(itemsOrdered[0].equals("None")){
        consoleFileString = new StringBuilder();
        consoleFileString.append("No items");}
        else{
            for(int i = 1; i<itemsOrdered.length -1; i++){
                consoleFileString.append(", " + itemsOrdered[i]);
            }
            consoleFileString.append(", and " + itemsOrdered[itemsOrdered.length-1]);
            consoleFileString.append(" for " + orderCost);
        }
        consoleFileString.append("\n");

        consoleOutputString = consoleFileString.toString();

    }



}

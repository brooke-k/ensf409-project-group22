/**
 * ENSF 409 Winter 2021 Final Group Project
 * SupplyChainTest is a program designed to test the methods and construction
 * of all files in the Supply Chain project using Junit 4.13.2
 *
 * @author <a href="christopher.chan2@ucalgary.ca>Christopher Chan</a>
 * @author <a href="amnah.hussain@ucalgary.ca>Amnah Hussain</a>
 * @author <a href="brooke.kindleman@ucalgary.ca>Brooke Kindleman</a>
 * @author <a href="neeraj.sunikumar@ucalgary.ca>Neeraj Sunil Kumar</a>
 *
 * @since 1.0
 *
 * @version 1.0
 *
 *
 */

package edu.ucalgary.ensf409;
import org.junit.*;
import org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;


/**
 * Class SupplyChainTest provides Junit 4 tests for the program to ensure the
 * correct outputs and results are produced by the program methods.
 */
public class SupplyChainTest {
    private final ByteArrayOutputStream terminalContent
            = new ByteArrayOutputStream();
    private final PrintStream originalTermContent = System.out;

    /**
     * readTerminalOutputSetup is a method
     * for sending all outputs from the terminal to the ByteArrayOutputStream
     * terminalContent, which can be used in any test for ensuring
     * terminal output is as expected.
     */
    @Before
    public void readTerminalOutputSetup(){
        System.setOut(new PrintStream(terminalContent));
    }

    /**
     * restoreTerminalOutputStream is a method for returning the output
     * stream System.out to the terminal.
     */
    @After
    public void restoreTerminalOutputStream(){
        System.setOut(originalTermContent);
    }


    /**
     * testUnfulfilledOutputString1Manufacturer tests the method
     * createUnfulfilledOutput to ensure that an unfulfilled output
     * with one manufacturer created is correct.
     */
    @Test
    public void testUnfulfilledOutputString1Manufacturer(){
        ArrayList<String>  manufacturers = new ArrayList<>();
        manufacturers.add("First manufacturer");

        FileIO fileOutput = new FileIO(manufacturers);

        assertEquals(fileOutput.createUnfulfilledOutput(),("\nOrder could " +
                "not be fulfilled based " +
                "on current inventory." + "\nThe suggested " +
                "manufacturer for this " +
                "order is " + "First manufacturer" + "."));
    }

    /**
     * testUnfulfilledOutputString2Manufacturers asserts that the String
     * created by method createUnfulfilled output produces the correct
     * output for two manufacturers.
     */
    @Test
    public void testUnfulfilledOutputString2Manufacturers(){
        ArrayList<String>  manufacturers = new ArrayList<>();
        manufacturers.add("First manufacturer");
        manufacturers.add("Second manufacturer");

        FileIO fileIO = new FileIO(manufacturers);

        assertEquals(fileIO.createUnfulfilledOutput(), ("\nOrder could not " +
                "be fulfilled based " +
                "on current inventory." + "\nSuggested manufacturers for " +
                "this order are " + "First manufacturer" + " and " +
                "Second manufacturer" + "."));
    }

    /**
     * testUnfulfilledOutputString4Manufacturers asserts that the String
     * created by method createUnfulfilled output produces the correct
     * output for four manufacturers.
     */
    @Test public void testUnfulfilledOutputString4Manufacturers(){
        ArrayList<String> manufacturers = new ArrayList<>();
        manufacturers.add("First");
        manufacturers.add("Second");
        manufacturers.add("Third");
        manufacturers.add("Fourth");

        FileIO fileIO = new FileIO(manufacturers);
        assertEquals(fileIO.createUnfulfilledOutput(), ("\nOrder could not " +
                "be fulfilled based " +
                "on current inventory." + "\nSuggested manufacturers " +
                "for this order are " + "First" + ", " + "Second" + ", " +
                "Third" + ", " + "and " + "Fourth" + "."));
    }

    /**
     * testUnfulfilledTerminalOutput1Manufacturer asserts that the terminal
     * output written by method createUnfulfilled output is the correct output
     * for one manufacturer.
     * Tested using method trim() to check for content only, not newline
     * formatting.
     */
    @Test public void testUnfulfilledTerminalOutput1Manufacturer(){
        ArrayList<String> manufacturers = new ArrayList<>();
        manufacturers.add("One manufacturer");

        FileIO fileIO = new FileIO(manufacturers);

        String expectedOutput = ("Order could not be fulfilled based " +
                "on current inventory." + "\n" + "The suggested manufacturer " +
                "for this " +
                "order is " + "One manufacturer" + ".");
        String termCont = terminalContent.toString().trim();


        assertEquals(expectedOutput, termCont);

    }

    /**
     * testUnfulfilledTerminalOutput2Manufacturers asserts that the terminal
     * output written by method createUnfulfilled output is the correct output
     * for two manufacturers.
     * Tested using method trim() to check for content only, not newline
     * formatting.
     */
    @Test public void testUnfulfilledTerminalOutput2Manufacturers(){
        ArrayList<String> manufacturers = new ArrayList<>();
        manufacturers.add("First company");
        manufacturers.add("Second company");

        FileIO fileIO = new FileIO(manufacturers);

        String expectedOutput = ("Order could not be fulfilled based " +
                "on current inventory." + "\n" + "Suggested manufacturers " +
                "for this " +
                "order are " + "First company" + " and " +
                "Second company" + ".");
        String termCont = terminalContent.toString().trim();


        assertEquals(expectedOutput, termCont);
    }

    /**
     * testUnfulfilledTerminalOutput2Manufacturers asserts that the terminal
     * output written by method createUnfulfilled output is the correct output
     * for two manufacturers.
     * Tested using method trim() to check for content only, not newline
     * formatting.
     */
    @Test public void testUnfulfilledTerminalOutput4Manufacturers(){
        ArrayList<String> manufacturers = new ArrayList<>();
        manufacturers.add("First corp");
        manufacturers.add("Second corp");
        manufacturers.add("Third corp");
        manufacturers.add("Fourth corp");

        FileIO fileIO = new FileIO(manufacturers);

        String expectedOutput = ("Order could not be fulfilled based " +
                "on current inventory." + "\n" + "Suggested manufacturers " +
                "for this " +
                "order are " + "First corp" +  ", " + "Second corp" + ", " +
                "Third corp" + ", " + "and "+ "Fourth corp"  + ".");
        String termCont = terminalContent.toString().trim();


        assertEquals(expectedOutput, termCont);
    }

    /**
     * testOrderOutputToTerminalOneItem asserts that the string produced by
     * the FileIO object with a successful order and one item is correct.
     */
    @Test public void testOrderOutputToTerminalOneItem(){
        String[] orderedItems = {"OneItem"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 3700;

        FileIO fileIO = new FileIO(outputFileName,orderedItems,originalRequest,orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" + "Purchased component: " + "OneItem" + " for $" + "3700");
        assertEquals(expectedConsoleOutput,fileIO.getConsoleOutputString().trim());
    }

    /**
     * testOrderOutputToTerminalTwoItems asserts that the string produced by
     * the FileIO object with a successful order and two items is correct.
     */
    @Test public void testOrderOutputToTerminalTwoItems(){
        String[] orderedItems = {"One item", "two items"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 7373;

        FileIO fileIO = new FileIO(outputFileName,orderedItems,originalRequest,orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" + "Purchased components: " + "One item" +  " and " + "two items" +" for $" + "7373");
        assertEquals(expectedConsoleOutput,fileIO.getConsoleOutputString().trim());
    }

    /**
     * testOrderOutputToTerminalFourItems asserts that the string produced by
     * the FileIO object with a successful order and four items is correct.
     */
    @Test public void testOrderOutputToTerminalFourItems(){
        String[] orderedItems = {"One1","Two2","Three3","Four4"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 89899;

        FileIO fileIO = new FileIO(outputFileName,orderedItems,originalRequest,orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" + "Purchased components: " +"One1" + ", " + "Two2" + ", " + "Three3" + ", " + "and " +"Four4" +" for $" + "89899");
        assertEquals(expectedConsoleOutput,fileIO.getConsoleOutputString().trim());
    }



}

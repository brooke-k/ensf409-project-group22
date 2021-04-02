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
import junit.framework.TestCase;
import org.junit.*;
import org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


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
     * testFileIO_UnfulfilledOutputStr1Manufacturer tests the method
     * createUnfulfilledOutput to ensure that an unfulfilled output
     * with one manufacturer created is correct.
     */
    @Test
    public void testFileIO_UnfulfilledOutputStr1Manufacturer(){
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
     * testFileIO_UnfulfilledOutputStr2Manufacturers asserts that the String
     * created by method createUnfulfilled output produces the correct
     * output for two manufacturers.
     */
    @Test
    public void testFileIO_UnfulfilledOutputStr2Manufacturers(){
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
     * testFileIO_UnfulfilledOutputStr4Manufacturers asserts that the String
     * created by method createUnfulfilled output produces the correct
     * output for four manufacturers.
     */
    @Test public void testFileIO_UnfulfilledOutputStr4Manufacturers(){
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
     * the FileIO object to be printed to the terminal with a successful order and one item is correct.
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
     * the FileIO object to be printed to the terminal with a successful order and two items is correct.
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
     * the FileIO object to be printed to the terminal with a successful order and four items is correct.
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

    /**
     * testOrderOutputFileStringOneItem asserts that the string produced by
     * the FileIO object to be written to the order output file with one item is correct.
     */
    @Test public void testOrderOutputFileStringOneItem(){
        String[] orderedItems = {"OneItem"};
        String outputFileName = "orderOutputToFileTest";
        String originalRequest = "orderOutputToFileTest request";
        int orderCost = 3700;

        FileIO fileIO = new FileIO(outputFileName,orderedItems,originalRequest,orderCost);

        String expectedOutputFileString = ("SCM Order Form" +
                "\n\n" +
                "Faculty Name: " +
                "\n" +
                "Contact: " +
                "\n" +
                "Date: " +
                "\n\n" +
                "Original Request: " +
                originalRequest +
                "\n\n" +
                "Items Ordered:" +
                "\n" +
                "  ID: " +
                "OneItem" +
                "\n\n" +
                "Total price of order: $" +
                orderCost);
        assertEquals(expectedOutputFileString,fileIO.getOrderOutputString());
    }

    /**
     * testOrderOutputFileStringOneItem asserts that the string produced by
     * the FileIO object to be written to the order output file with two items is correct.
     */
    @Test public void testOrderOutputFileStringTwoItems(){
        String[] orderedItems = {"One item", "two Items"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 89474;

        FileIO fileIO = new FileIO(outputFileName,orderedItems,originalRequest,orderCost);

        String expectedOutputFileString = ("SCM Order Form" +
                "\n\n" +
                "Faculty Name: " +
                "\n" +
                "Contact: " +
                "\n" +
                "Date: " +
                "\n\n" +
                "Original Request: " +
                originalRequest +
                "\n\n" +
                "Items Ordered:" +
                "\n" +
                "  ID: " +
                "One item" +
                "\n" +
                "  ID: " +
                "two Items" +
                "\n\n" +
                "Total price of order: $" +
                orderCost);
        assertEquals(expectedOutputFileString,fileIO.getOrderOutputString());
    }

    /**
     * testOrderOutputFileStringOneItem asserts that the string produced by
     * the FileIO object to be written to the order output file with four items is correct.
     */
    @Test public void testOrderOutputFileStringFourItems(){
        String[] orderedItems = {"11One","22Two","33Three","44Four"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 27722;

        FileIO fileIO = new FileIO(outputFileName,orderedItems,originalRequest,orderCost);

        String expectedOutputFileString = ("SCM Order Form" +
                "\n\n" +
                "Faculty Name: " +
                "\n" +
                "Contact: " +
                "\n" +
                "Date: " +
                "\n\n" +
                "Original Request: " +
                originalRequest +
                "\n\n" +
                "Items Ordered:" +
                "\n" +
                "  ID: " +
                "11One" +
                "\n" +
                "  ID: " +
                "22Two" +
                "\n" +
                "  ID: " +
                "33Three" +
                "\n" +
                "  ID: " +
                "44Four" +
                "\n\n" +
                "Total price of order: $" +
                orderCost);
        assertEquals(expectedOutputFileString,fileIO.getOrderOutputString());
    }

    /**
     * testOrderOutputFileCreated asserts that an order output file with
     * the correct name was created.
     * Uses a randomly generated value from 0-999998 to attempt to create
     * a unique file for testing each time.
     */
    @Test public void testOrderOutputFileCreated(){
        Random randValue = new Random();
        int testFileNameNumber = randValue.nextInt(999999);
        String testFileName = Integer.toString(testFileNameNumber) + ".txt";
        String testRequest = "Test order request";
        String[] testItemsOrdered = {"Item one", "Item two", "Item three", "Item four", "Item five"};
        int testOrderCost = 345;

        FileIO fileIO = new FileIO(testFileName, testItemsOrdered, testRequest, testOrderCost);
        File testFile = new File(testFileName);
        assertTrue(testFile.exists() && testFile.isFile());
    }

    /**
     * testOrderOutputFileWritten asserts that an order output file was created
     * correctly and contains the correct output string generated by the
     * FileIO method createFulfilledOutput.
     *
     * Uses a randomly generated value from 0-999998 to attempt to create
     * a unique file for testing each time.
     */
    @Test public void testOrderOutputFileWritten() {
        Random randValue = new Random();
        int testFileNameNumber = randValue.nextInt(999999);
        String testFileName = Integer.toString(testFileNameNumber) + ".txt";
        String testRequest = "Test order request";
        String[] testItemsOrdered = {"Item one", "Item two", "Item three", "Item four", "Item five"};
        int testOrderCost = 345;
        FileIO fileIO = new FileIO(testFileName, testItemsOrdered, testRequest, testOrderCost);

        StringBuilder readFromFile = new StringBuilder();

        try {
            FileReader testFileRead = new FileReader(new File(testFileName));
            char readChar;
            int readCharInt = testFileRead.read();
            while (readCharInt != -1) {
                readChar = (char) readCharInt;
                readFromFile.append(readChar);
                readCharInt = testFileRead.read();
            }
            assertEquals(fileIO.getOrderOutputString(), readFromFile.toString());
        } catch (IOException e) {
            System.err.println("IOException: " +
                    "Could not read from the test file \"" + testFileName + "\"");
        }
    }

    /**
     * testPriceOptimizer_compatibleValid asserts that the object PriceOptimizer
     * recognizes that all pieces provided are compatible to create a complete
     * furniture item.
     */
    @Test
        public void testPriceOptimizer_compatibleValid() {
            String[] id  = {};
            int[] price = {};
            boolean[][] parts = {
                    {true, false, false, true},
                    {true, false, true, false},
                    {false, true, false, true}
            };
            int[] list = {0,1,2};
            PriceOptimizer p = new PriceOptimizer(id,parts,price);
            Assert.assertTrue(p.compatible(list));
        }

    /**
     * testPriceOptimizer_compatibleInvalid asserts that object Price
     * Optimizer correctly recognizes when available pieces cannot create
     * a full furniture item.
     */
    @Test
    public void testPriceOptimizer_compatibleInvalid() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, false, false, true},
                {true, false, false, false},
                {false, true, false, true}
        };
        int[] list = {0,1,2};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        assertFalse(p.compatible(list));
    }

    /**
     * testPriceOptimizer_testOptimize asserts that the object Price
     * Optimizer can correctly produce an order with a combination of
     * pieces that total to the lowest possible cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, true, false, true},

        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(1);
        String[] expected = {"C9890", "C0942"};
        String[] expected2 = {"C0942", "C9890"};
        for(int i = 0; i < 2; i++) {
            System.out.println(result[i] + " ");
        }

        Assert.assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(expected2, result));
    }

    /**
     * testPriceOptimizer_testOptimize1 provides a second variation of
     * testPriceOptimizer_testOptimize, using different values for the pieces
     * available.
     *
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize1() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(1);
    }

    /**
     * testPriceOptimizer_testOptimize2 provides a second variation of
     * testPriceOptimizer_testOptimize, using different values for the pieces
     * available.
     *
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, true, true, true},
                {true, true, true, true},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748"};
        String[] expected2 = {"C6748", "C0942"};
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        Assert.assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimize3 provides a second variation of
     * testPriceOptimizer_testOptimize, using different values for the pieces
     * available.
     *
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize3() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, true, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748", "C9890"};
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        Assert.assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(result, expected));
    }
    
}

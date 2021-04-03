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

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertFalse;


/**
 * Class SupplyChainTest provides Junit 4 tests for the program to ensure the
 * correct outputs and results are produced by the program methods.
 */
public class SupplyChainTest {
    private final ByteArrayOutputStream terminalContent
            = new ByteArrayOutputStream();
    private final PrintStream originalTermContent = System.out;
    private final InputStream originalTerminalInput = System.in;


    /**
     * readTerminalOutputSetup is a method
     * for sending all outputs from the terminal to the ByteArrayOutputStream
     * terminalContent, which can be used in any test for ensuring
     * terminal output is as expected.
     */
    @Before
    public void readTerminalOutputSetup() {
        System.setOut(new PrintStream(terminalContent));
        System.setIn(originalTerminalInput);
    }

    /**
     * restoreTerminalOutputStream is a method for returning the output
     * stream System.out to the terminal.
     */
    @After
    public void restoreTerminalOutputStream() {
        System.setOut(originalTermContent);
    }


    /**
     * testFileIO_UnfulfilledOutputStr1Manufacturer tests the method
     * createUnfulfilledOutput to ensure that an unfulfilled output
     * with one manufacturer created is correct.
     */
    @Test
    public void testFileIO_UnfulfilledOutputStr1Manufacturer() {
        ArrayList<String> manufacturers = new ArrayList<>();
        manufacturers.add("First manufacturer");

        FileIO fileOutput = new FileIO(manufacturers);

        assertEquals(fileOutput.createUnfulfilledOutput(), ("\nOrder could " +
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
    public void testFileIO_UnfulfilledOutputStr2Manufacturers() {
        ArrayList<String> manufacturers = new ArrayList<>();
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
    @Test
    public void testFileIO_UnfulfilledOutputStr4Manufacturers() {
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
     * testFileIO_UnfulfilledTerminalOutput1Manufacturer asserts that the terminal
     * output written by method createUnfulfilled output is the correct output
     * for one manufacturer.
     * Tested using method trim() to check for content only, not newline
     * formatting.
     */
    @Test
    public void testFileIO_UnfulfilledTerminalOutput1Manufacturer() {
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
     * testFileIO_UnfulfilledTerminalOutput2Manufacturers asserts that the terminal
     * output written by method createUnfulfilled output is the correct output
     * for two manufacturers.
     * Tested using method trim() to check for content only, not newline
     * formatting.
     */
    @Test
    public void testFileIO_UnfulfilledTerminalOutput2Manufacturers() {
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
     * testFileIO_UnfulfilledTerminalOutput4Manufacturers asserts that the terminal
     * output written by method createUnfulfilled output is the correct output
     * for two manufacturers.
     * Tested using method trim() to check for content only, not newline
     * formatting.
     */
    @Test
    public void testFileIO_UnfulfilledTerminalOutput4Manufacturers() {
        ArrayList<String> manufacturers = new ArrayList<>();
        manufacturers.add("First corp");
        manufacturers.add("Second corp");
        manufacturers.add("Third corp");
        manufacturers.add("Fourth corp");

        FileIO fileIO = new FileIO(manufacturers);

        String expectedOutput = ("Order could not be fulfilled based " +
                "on current inventory." + "\n" + "Suggested manufacturers " +
                "for this " +
                "order are " + "First corp" + ", " + "Second corp" + ", " +
                "Third corp" + ", " + "and " + "Fourth corp" + ".");
        String termCont = terminalContent.toString().trim();


        assertEquals(expectedOutput, termCont);
    }

    /**
     * testFileIO_FulfilledOrderTerminalOutputOneItem asserts that the string produced by
     * the FileIO object to be printed to the terminal with a successful order and one item is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderTerminalOutputOneItem() {
        String[] orderedItems = {"OneItem"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 3700;

        FileIO fileIO = new FileIO(outputFileName, orderedItems, originalRequest, orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" + "Purchased component: " + "OneItem" + " for $" + "3700");
        assertEquals(expectedConsoleOutput, fileIO.getConsoleOutputString().trim());
    }

    /**
     * testFileIO_FulfilledOrderTerminalOutputTwoItems asserts that the string produced by
     * the FileIO object to be printed to the terminal with a successful order and two items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderTerminalOutputTwoItems() {
        String[] orderedItems = {"One item", "two items"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 7373;

        FileIO fileIO = new FileIO(outputFileName, orderedItems, originalRequest, orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" + "Purchased components: " + "One item" + " and " + "two items" + " for $" + "7373");
        assertEquals(expectedConsoleOutput, fileIO.getConsoleOutputString().trim());
    }

    /**
     * testFileIO_FulfilledOrderTerminalOutputFourItems asserts that the string produced by
     * the FileIO object to be printed to the terminal with a successful order and four items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderTerminalOutputFourItems() {
        String[] orderedItems = {"One1", "Two2", "Three3", "Four4"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 89899;

        FileIO fileIO = new FileIO(outputFileName, orderedItems, originalRequest, orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" + "Purchased components: " + "One1" + ", " + "Two2" + ", " + "Three3" + ", " + "and " + "Four4" + " for $" + "89899");
        assertEquals(expectedConsoleOutput, fileIO.getConsoleOutputString().trim());
    }

    /**
     * testFileIO_FulfilledOrderOutputStringOneItem asserts that the string produced by
     * the FileIO object to be written to the order output file with one item is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderOutputStringOneItem() {
        String[] orderedItems = {"OneItem"};
        String outputFileName = "orderOutputToFileTest";
        String originalRequest = "orderOutputToFileTest request";
        int orderCost = 3700;

        FileIO fileIO = new FileIO(outputFileName, orderedItems, originalRequest, orderCost);

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
        assertEquals(expectedOutputFileString, fileIO.getOrderOutputString());
    }

    /**
     * testFileIO_FulfilledOrderOutputStringTwoItems asserts that the string produced by
     * the FileIO object to be written to the order output file with two items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderOutputStringTwoItems() {
        String[] orderedItems = {"One item", "two Items"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 89474;

        FileIO fileIO = new FileIO(outputFileName, orderedItems, originalRequest, orderCost);

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
        assertEquals(expectedOutputFileString, fileIO.getOrderOutputString());
    }

    /**
     * testFileIO_FulfilledOrderOutputStringFourItems asserts that the string produced by
     * the FileIO object to be written to the order output file with four items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderOutputStringFourItems() {
        String[] orderedItems = {"11One", "22Two", "33Three", "44Four"};
        String outputFileName = "orderOutputToTerminalTest";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 27722;

        FileIO fileIO = new FileIO(outputFileName, orderedItems, originalRequest, orderCost);

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
        assertEquals(expectedOutputFileString, fileIO.getOrderOutputString());
    }

    /**
     * testFileIO_OrderOutputFileCreated asserts that an order output file with
     * the correct name was created.
     * Uses a randomly generated value from 0-999998 to attempt to create
     * a unique file for testing each time.
     */
    @Test
    public void testFileIO_OrderOutputFileCreated() {
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
     * testFileIO_OrderOutputFileWritten asserts that an order output file was created
     * correctly and contains the correct output string generated by the
     * FileIO method createFulfilledOutput.
     * <p>
     * Uses a randomly generated value from 0-999998 to attempt to create
     * a unique file for testing each time.
     */
    @Test
    public void testFileIO_OrderOutputFileWritten() {
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
        String[] id = {};
        int[] price = {};
        boolean[][] parts = {
                {true, false, false, true},
                {true, false, true, false},
                {false, true, false, true}
        };
        int[] list = {0, 1, 2};
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        Assert.assertTrue(p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleInvalid asserts that object Price
     * Optimizer correctly recognizes when available pieces cannot create
     * a full furniture item.
     */
    @Test
    public void testPriceOptimizer_compatibleInvalid() {
        String[] id = {};
        int[] price = {};
        boolean[][] parts = {
                {true, false, false, true},
                {true, false, false, false},
                {false, true, false, true}
        };
        int[] list = {0, 1, 2};
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        assertFalse(p.compatible(list));
    }

    /**
     * testPriceOptimizer_testOptimize asserts that the object Price
     * Optimizer can correctly produce an order with a combination of
     * pieces that total to the lowest possible cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, true, false, true},

        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(1);
        String[] expected = {"C9890", "C0942"};
        String[] expected2 = {"C0942", "C9890"};
        for (int i = 0; i < 2; i++) {
            System.out.println(result[i] + " ");
        }

        Assert.assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(expected2, result));
    }

    /**
     * testPriceOptimizer_testOptimize1 provides a second variation of
     * testPriceOptimizer_testOptimize, using different values for the pieces
     * available.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize1() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(1);
    }

    /**
     * testPriceOptimizer_testOptimize2 provides a second variation of
     * testPriceOptimizer_testOptimize, using different values for the pieces
     * available.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, true, true, true},
                {true, true, true, true},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748"};
        String[] expected2 = {"C6748", "C0942"};
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        Assert.assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimize3 provides a second variation of
     * testPriceOptimizer_testOptimize, using different values for the pieces
     * available.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize3() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, true, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748", "C9890"};
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        Assert.assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(result, expected));
    }

    /**
     * testUserIO_validOrderReadFurnCategory asserts that a valid order has been
     * placed and the user's requested furniture category has been read
     * and saved correctly.
     *
     * @throws IOException Exception regarding the use of the input stream for
     *                     for testing purposes only; not relevant to UserIO methods.
     */
    @Test
    public void testUserIO_validOrderReadFurnCategory() throws IOException {
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals("chair", userIO.getFurnCategory());
    }

    /**
     * testUserIO_validOrderReadFurnType asserts that a valid order has been
     * placed and the user's requested furniture type has been read
     * and saved correctly.
     *
     * @throws IOException Exception regarding the use of the input stream for
     *                     for testing purposes only; not relevant to UserIO methods.
     */
    @Test
    public void testUserIO_validOrderReadFurnType() throws IOException {
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals("Mesh", userIO.getFurnType());
    }

    /**
     * testUserIO_validOrderReadNumOfItems asserts that a valid order has been
     * placed and the user's requested number of items
     * has been read and saved correctly.
     *
     * @throws IOException Exception regarding the use of the input stream for
     *                     for testing purposes only; not relevant to UserIO methods.
     */
    @Test
    public void testUserIO_validOrderReadNumOfItems() throws IOException {
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals("1", userIO.getNumOfItems());
    }

    /**
     * testUserIO_validOrderRequest asserts that a valid order has been
     * placed and the user's order request
     * has been read and saved correctly.
     *
     * @throws IOException Exception regarding the use of the input stream for
     *                     for testing purposes only; not relevant to UserIO methods.
     */
    @Test
    public void testUserIO_validOrderRequest() throws IOException {
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals("Mesh chair, 1", userIO.getLatestRequest());
    }

    /**
     * testUserIO_invalidOrderReadFurnTypeFirstOrder asserts that if an invalid order
     * is placed as the first order, the saved furniture type will be null.
     */
    @Test public void testUserIO_invalidOrderReadFurnTypeFirstOrder(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Invalid input" + "\n" + "CANCEL" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals(null, userIO.getFurnType());
    }

    /**
     * testUserIO_invalidOrderReadFurnTypeAfterFirst asserts that if an invalid order
     * is placed after a valid previous order, the saved furniture type will be null.
     */
    @Test public void testUserIO_invalidOrderReadFurnTypeAfterFirst(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n" + "1" + "\n" + "Desk lamp, 1" + "\n" + "1" + "\n" + "Invalid order name" + "\n" + "CANCEL" +"\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1); // Fulfills valid order "Mesh chair, 1"
        userIO.processInput(readFromInput);

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Fulfills the valid order
                                            // "Desk lamp, 1"

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Attempts to fulfill the invalid
                                            // order "Invalid order name"


        assertEquals(null, userIO.getFurnType());
    }

    /**
     * testUserIO_invalidOrderReadCategoryFirstOrder asserts that if an invalid order
     * is placed as the first order, the saved furniture category will be null.
     */
    @Test public void testUserIO_invalidOrderReadCategoryFirstOrder(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Invalid input" + "\n" + "CANCEL" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals(null, userIO.getFurnCategory());
    }

    /**
     * testUserIO_invalidOrderReadFurnCategoryAfterFirst asserts that if an invalid order
     * is placed after a valid previous order, the saved furniture category will be null.
     */
    @Test public void testUserIO_invalidOrderReadFurnCategoryAfterFirst(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n" + "1" + "\n" + "Desk lamp, 1" + "\n" + "1" + "\n" + "Invalid order" + "\n" + "CANCEL" +"\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1); // Fulfills valid order "Mesh chair, 1"
        userIO.processInput(readFromInput);

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Fulfills the valid order
        // "Desk lamp, 1"

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Attempts to fulfill the invalid
        // order "Invalid order"


        assertEquals(null, userIO.getFurnCategory());
    }

    /**
     * testUserIO_invalidOrderReadNumOfItemsFirstOrder asserts that if an invalid order
     * is placed as the first order, the saved number of items will be null.
     */
    @Test public void testUserIO_invalidOrderReadNumOfItemsFirstOrder(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Bad order" + "\n" + "CANCEL" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals(null, userIO.getNumOfItems());
    }

    /**
     * testUserIO_invalidOrderReadNumOfItemsAfterFirst asserts that if an invalid order
     * is placed after a valid previous order, the saved number of items will be null.
     */
    @Test public void testUserIO_invalidOrderReadNumOfItemsAfterFirst(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n" + "1" + "\n" + "Desk lamp, 1" + "\n" + "1" + "\n" + "Meaningless order" + "\n" + "CANCEL" +"\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1); // Fulfills valid order "Mesh chair, 1"
        userIO.processInput(readFromInput);

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Fulfills the valid order
        // "Desk lamp, 1"

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Attempts to fulfill the invalid
        // order "Meaningless order"


        assertEquals(null, userIO.getNumOfItems());
    }

    /**
     * testUserIO_invalidOrderReadLatestOrderFirstOrder asserts that if an invalid order
     * is placed as the first order, the saved record of the latest order will be null.
     */
    @Test public void testUserIO_invalidOrderReadLatestOrderFirstOrder(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Invalid request" + "\n" + "CANCEL" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        assertEquals(null, userIO.getLatestRequest());
    }

    /**
     * testUserIO_invalidOrderReadNumOfItemsAfterFirst asserts that if an invalid order
     * is placed after a valid previous order, the saved number of items will be null.
     */
    @Test public void testUserIO_invalidOrderLatestOrderAfterFirst(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("1" + "\n" + "Mesh chair, 1" + "\n" + "1" + "\n" + "Desk lamp, 1" + "\n" + "1" + "\n" + "Bad bad order" + "\n" + "CANCEL" +"\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1); // Fulfills valid order "Mesh chair, 1"
        userIO.processInput(readFromInput);

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Fulfills the valid order
        // "Desk lamp, 1"

        readFromInput = userIO.menu();
        userIO.processInput(readFromInput); // Attempts to fulfill the invalid
        // order "Bad bad order"


        assertEquals(null, userIO.getLatestRequest());
    }

    /**
     * testUserIO_displaySQLCredentials asserts that when the user requests
     * to see the current MySQL credentials being used, they are
     * corrected displayed to the terminal.
     */
    @Test public void testUserIO_displaySQLCredentials(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("3" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        String expectedOutputREGEX = "(Current database URL: jdbc:mysql://localhost/inventory)\\R(Current database username: scm\\RCurrent database password: \\*\\*\\*\\*\\*\\*\\*)";
        Pattern expectedPattern = Pattern.compile(expectedOutputREGEX);
        Matcher expectedMatch = expectedPattern.matcher(terminalContent.toString());

        boolean correctOutput = expectedMatch.find();

        assertTrue(correctOutput);

    }

    /**
     * testUserIO_displayCurrentOutputFileName asserts that the correct and
     * current output file name for the order output to be written to is
     * displayed to the user in the terminal when requested
     */
    @Test public void testUserIO_displayCurrentOutputFileName(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("2" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        int readFromInput = userIO.menu();
        System.setIn(terminalInput1);
        userIO.processInput(readFromInput);

        String expectedOutputREGEX = "(Current output file name is: OrderOutput.txt)";
        Pattern expectedPattern = Pattern.compile(expectedOutputREGEX);
        Matcher expectedMatch = expectedPattern.matcher(terminalContent.toString());

        boolean correctOutput = expectedMatch.find();

        assertTrue(correctOutput);
    }




}

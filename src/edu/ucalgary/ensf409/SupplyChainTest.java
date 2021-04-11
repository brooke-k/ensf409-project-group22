/**
 * ENSF 409 Winter 2021 Final Group Project
 * SupplyChainTest is a program designed to test the methods and construction
 * of all files in the Supply Chain project using Junit 4.13.2
 *
 * @author <a href="christopher.chan2@ucalgary.ca">Christopher Chan</a>
 * @author <a href="amnah.hussain@ucalgary.ca">Amnah Hussain</a>
 * @author <a href="brooke.kindleman@ucalgary.ca">Brooke Kindleman</a>
 * @author <a href="neeraj.sunikumar@ucalgary.ca">Neeraj Sunil Kumar</a>
 *
 * @since 1.0
 *
 * @version 2.5
 *
 *
 */

package edu.ucalgary.ensf409;

import org.junit.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * Class SupplyChainTest provides Junit 4 tests for Supply Chain Manager to
 * ensure the correct outputs and results are produced by the methods from
 * the program Supply Chain Manager
 */
public class SupplyChainTest {
    private final ByteArrayOutputStream terminalContent
            = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent
            = new ByteArrayOutputStream();
    private final PrintStream originalErrStream = System.err;
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
        System.setErr(new PrintStream(errContent));

    }

    /**
     * restoreTerminalOutputStream is a method for returning the output
     * stream System.out to the terminal.
     */
    @After
    public void restoreTerminalOutputStream() {
        File checkFile = new File("TESTFILEOUTPUT.txt");
        if (checkFile.exists()) {
            checkFile.delete();
        }
        checkFile = new File("OrderOutput.txt");
        if (checkFile.exists()){
            checkFile.delete();
    }
        System.setOut(originalTermContent);
        System.setErr(originalErrStream);
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

        assertEquals("Output String for an unfulfilled " +
                        "order with one manufacturer is incorrect.",
                fileOutput.createUnfulfilledOutput(),
                ("\n" +
                 "Order could not be fulfilled based on current inventory.\n" +
                 "The suggested manufacturer for this order " +
                 "is: \nFirst manufacturer."));
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

        assertEquals("Output String for an unfulfilled " +
                        "order with two manufacturers is incorrect.",
                fileIO.createUnfulfilledOutput(),
                ("\n" +
                 "Order could not be fulfilled based on current inventory.\n" +
                 "Suggested manufacturers for this order are: \nFirst " +
                 "manufacturer and Second manufacturer."));
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
        assertEquals("Output String for an unfulfilled " +
                        "order with four manufacturers is incorrect.",
                fileIO.createUnfulfilledOutput(),
                ("\n" +
                 "Order could not be fulfilled based on current inventory.\n" +
                 "Suggested manufacturers for this " +
                 "order are: \nFirst, \nSecond, \nThird, \nand Fourth."));
    }

    /**
     * testFileIO_UnfulfilledTerminalOutput1Manufacturer asserts that the
     * terminal output written by method createUnfulfilled output is the
     * correct output for one manufacturer.
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
                "order is: \n" + "One manufacturer" + ".");
        String termCont = terminalContent.toString().trim();


        assertEquals("Terminal output for an unfulfilled order " +
                        "with one manufacturer is incorrect.",
                expectedOutput, termCont);

    }

    /**
     * testFileIO_UnfulfilledTerminalOutput2Manufacturers asserts that the
     * terminal output written by method createUnfulfilled output is the
     * correct output for two manufacturers.
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
                "order are: \n" + "First company" + " and " +
                "Second company" + ".");
        String termCont = terminalContent.toString().trim();


        assertEquals("Terminal output for an unfulfilled order " +
                "with two manufacturers is incorrect.",expectedOutput,
                termCont);
    }

    /**
     * testFileIO_UnfulfilledTerminalOutput4Manufacturers asserts that the
     * terminal output written by method createUnfulfilled output is the
     * correct output for two manufacturers.
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
                "on current inventory." +
                "\n" +
                "Suggested manufacturers " +
                "for this order are: \n" +
                "First corp" +
                ", \n" +
                "Second corp" +
                ", \n" +
                "Third corp" +
                ", \n" +
                "and " +
                "Fourth corp" +
                ".");
        String termCont = terminalContent.toString().trim();


        assertEquals("Terminal output for an unfulfilled order " +
                "with four manufacturers is incorrect."
                ,expectedOutput, termCont);
    }

    /**
     * testFileIO_FulfilledOrderTerminalOutputOneItem asserts that the string
     * produced by the FileIO object to be printed to the terminal with a
     * successful order and one item is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderTerminalOutputOneItem() {
        String[] orderedItems = {"OneItem"};
        String outputFileName = "TESTFILEOUTPUT.txt";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 3700;

        FileIO fileIO = new FileIO(outputFileName, orderedItems,
                originalRequest, orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" +
                "Purchased component: " + "OneItem" + " for $" + "3700");
        assertEquals("Terminal output for a fulfilled item" +
                        " with one item is incorrect.",
                expectedConsoleOutput,
                fileIO.getConsoleOutputString().trim());
    }

    /**
     * testFileIO_FulfilledOrderTerminalOutputTwoItems asserts that the string
     * produced by the FileIO object to be printed to the terminal with a
     * successful order and two items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderTerminalOutputTwoItems() {
        String[] orderedItems = {"One item", "two items"};
        String outputFileName = "TESTFILEOUTPUT.txt";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 7373;

        FileIO fileIO = new FileIO(outputFileName, orderedItems,
                originalRequest, orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" +
                "Purchased components: " + "One item" + " and " + "two items"
                + " for $" + "7373");
        assertEquals("Terminal output for a fulfilled item" +
                        " with two items is incorrect.",expectedConsoleOutput,
                fileIO.getConsoleOutputString().trim());
    }

    /**
     * testFileIO_FulfilledOrderTerminalOutputFourItems asserts that the string
     * produced by the FileIO object to be printed to the terminal with a
     * successful order and four items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderTerminalOutputFourItems() {
        String[] orderedItems = {"One1", "Two2", "Three3", "Four4"};
        String outputFileName = "TESTFILEOUTPUT.txt";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 89899;

        FileIO fileIO = new FileIO(outputFileName, orderedItems,
                originalRequest, orderCost);

        String expectedConsoleOutput = ("Order successful." + "\n" +
                "Purchased components: " + "One1" + ", " + "Two2" + ", " +
                "Three3" + ", " + "and " + "Four4" + " for $" + "89899");
        assertEquals("Terminal output for a fulfilled item" +
                        " with four items is incorrect.",expectedConsoleOutput,
                fileIO.getConsoleOutputString().trim());
    }

    /**
     * testFileIO_FulfilledOrderOutputStringOneItem asserts that the string
     * produced by the FileIO object to be written to the order output file with
     * one item is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderOutputStringOneItem() {
        String[] orderedItems = {"OneItem"};
        String outputFileName = "TESTFILEOUTPUT.txt";
        String originalRequest = "orderOutputToFileTest request";
        int orderCost = 3700;

        FileIO fileIO = new FileIO(outputFileName, orderedItems,
                originalRequest, orderCost);

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
        assertEquals("Order form output String for an order " +
                        "with one item is incorrect.",
                expectedOutputFileString, fileIO.getOrderOutputString());
    }

    /**
     * testFileIO_FulfilledOrderOutputStringTwoItems asserts that the string
     * produced by the FileIO object to be written to the order output file with
     * two items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderOutputStringTwoItems() {
        String[] orderedItems = {"One item", "two Items"};
        String outputFileName = "TESTFILEOUTPUT.txt";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 89474;

        FileIO fileIO = new FileIO(outputFileName, orderedItems,
                originalRequest, orderCost);

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
        assertEquals("Order form output String for an order " +
                "with two items is incorrect.",expectedOutputFileString,
                fileIO.getOrderOutputString());
    }

    /**
     * testFileIO_FulfilledOrderOutputStringFourItems asserts that the string
     * produced by the FileIO object to be written to the order output file with
     * four items is correct.
     */
    @Test
    public void testFileIO_FulfilledOrderOutputStringFourItems() {
        String[] orderedItems = {"11One", "22Two", "33Three", "44Four"};
        String outputFileName = "TESTFILEOUTPUT.txt";
        String originalRequest = "orderOutputToTerminal request";
        int orderCost = 27722;

        FileIO fileIO = new FileIO(outputFileName, orderedItems,
                originalRequest, orderCost);

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
        assertEquals("Order form output String for an order " +
                "with four items is incorrect.",expectedOutputFileString,
                fileIO.getOrderOutputString());
    }

    /**
     * testFileIO_OrderOutputFileCreated asserts that an order output file with
     * the correct name was created.
     * Uses a randomly generated value from 0-999998 to attempt to create
     * a unique file for testing each time.
     */
    @Test
    public void testFileIO_OrderOutputFileCreated() {
        String testFileName = "TESTFILEOUTPUT.txt";
        String testRequest = "Test order request";
        String[] testItemsOrdered = {"Item one", "Item two", "Item three",
                "Item four", "Item five"};
        int testOrderCost = 345;

        FileIO fileIO = new FileIO(testFileName, testItemsOrdered,
                testRequest, testOrderCost);
        File testFile = new File(testFileName);
        assertTrue("The order output form file was not created.",
                testFile.exists() && testFile.isFile());
    }

    /**
     * testFileIO_OrderOutputFileWritten asserts that an order output file was
     * created correctly and contains the correct output string generated by the
     * FileIO method createFulfilledOutput.
     * <p>
     * Uses a randomly generated value from 0-999998 to attempt to create
     * a unique file for testing each time.
     */
    @Test
    public void testFileIO_OrderOutputFileWritten() {
        String testFileName = "TESTFILEOUTPUT.txt";
        String testRequest = "Test order request";
        String[] testItemsOrdered = {"Item one", "Item two", "Item three",
                "Item four", "Item five"};
        int testOrderCost = 345;
        FileIO fileIO = new FileIO(testFileName, testItemsOrdered,
                testRequest, testOrderCost);

        StringBuilder readFromFile = new StringBuilder();

        try {
            FileReader testFileRead = new FileReader(testFileName);
            char readChar;
            int readCharInt = testFileRead.read();
            while (readCharInt != -1) {
                readChar = (char) readCharInt;
                readFromFile.append(readChar);
                readCharInt = testFileRead.read();
            }
            assertEquals("The order output String does not " +
                            "match the contents " +
                            "of the generated order output form.",
                    fileIO.getOrderOutputString(),
                    readFromFile.toString());
        } catch (IOException e) {
            System.err.println("IOException: " +
                    "Could not read from the test file \""
                    + testFileName + "\"");
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
        p.setItemCount(1);
        assertTrue("Price optimizer with void id and price fails to " +
                        "identify compatible items.",
                p.compatible(list));
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
        p.setItemCount(1);
        assertFalse("Price optimizer with void id and price does not" +
                        " identify incompatible combinations.",
                p.compatible(list));
    }


    /**
     * testPriceOptimizer_compataibleInvalidSmall asserts that a Price
     * Optimizer object recognizes the available pieces for one full
     * furniture item based on a very limited size of parts (1).
     */
    @Test
    public void testPriceOptimizer_compatibleInvalidSmall() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, false, true}
        };
        int[] list = {0};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(1);
        assertFalse("Price optimizer with void id and price, and " +
                        "one set of parts does not return " +
                        "an invalid compatibility.",
                p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleInvalid2Items() checks to see if
     * a given parts list will return the right value when it is NOT
     * possible to make 2 furniture items (should return false).
     * In this configuration, it is possible to make one furniture
     * item (but not 2) based on 3 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleInvalid2Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, false},
                {false, true, false, true}
        };
        int[] list = {0,1,2};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(2);
        assertFalse("Price optimizer for two items and invalid " +
                        "compatibility did not identify an " +
                        "incompatible combination.",
                p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleValid2Items() checks to see if
     * a given parts list will return the right value when it IS possible
     * to make 2 furniture items with the given configuration list
     * (should return true).
     * In this configuration, it is possible to make 2 furniture
     * items from 3 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleValid2Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, false},
                {false, true, true, true}
        };
        int[] list = {0,1,2};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(2);
        assertTrue("Price optimizer with two item request and " +
                        "valid compatibility did not identify the " +
                        "compatibility.",
                p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleValid3Items() checks to see if
     * a given parts list will return the right value when it IS possible
     * to make 3 furniture items with the given configuration list
     * (should return true).
     * In this configuration, it is possible to make 3 furniture
     * items from 4 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleValid3Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, true, false}
        };
        int[] list = {0,1,2,4};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(3);
        assertTrue("Price optimizer with three item request and " +
                "valid compatibility did not identify the " +
                "compatibility.",p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleInvalid3Items() checks to see if
     * a given parts list will return the right value when it is NOT
     * possible to make 2 furniture items (should return false).
     * In this configuration, it is possible to make 1 or 2 furniture
     * items but not 3 from 4 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleInvalid3Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, true, false}
        };
        int[] list = {0,1,2,3};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(3);
        assertFalse("Price optimizer with three item request and " +
                "invalid compatibility did not identify the " +
                "non-compatibility.",p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleValid3ItemsSimple() checks to see if
     * a given parts list will return the right value when it IS possible
     * to make 3 furniture items with the given configuration list
     * (should return true) based on a very simple configuration
     * where the 3 specified furniture items are complete and can make
     * 3 sets of new furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleValid3ItemsSimple() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, true, true},
                {true, true, true, false},
                {true, true, true, true},
                {true, true, true, true}
        };
        int[] list = {4,6,7};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(3);
        assertTrue("PriceOptimizer with a three item order and " +
                        "simple compatibility did not identify a " +
                        "valid compatibility.",
                p.compatible(list));
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

        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(expected2, result));
    }

    /**
     * testPriceOptimizer_testPriceAccurate will run optimize(1) and
     * check if the price matches the expected price based on the furniture
     * and price configuration.
     */
    @Test
    public void testPriceOptimizer_testOptimizePriceAccurate() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, true, false, true},

        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        p.optimize(1);
        int priceResult = p.getCurrentCost();
        Assert.assertEquals("PriceOptimizer did not correctly " +
                        "identify the lowest possible price for an order.",
                150, priceResult);
    }

    /**
     * testPriceOptimizer_testOptimizeImpossible tests that the
     * optimize() method will return null upon invalid values
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimizeImpossible() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] producedArray = p.optimize(1);
        assertNull("PriceOptimizer did not return null for a " +
                        "selection of incompatible items.",
                producedArray);
    }

    /**
     * testPriceOptimizer_testOptimize2 provides a second variation of
     * testPriceOptimizer_testOptimize, testing that it is possible to make
     *      * 2 furniture items based on a different configuration of parts.
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
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimize2PriceAccurate will run optimize(2) and
     * check if the price matches the expected price based on the furniture
     * and price configuration.
     */
    @Test
    public void testPriceOptimizer_testOptimize2PriceAccurate() {
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
        for (String s : result) {
            System.out.print(s + " ");
        }
        int priceResult = p.getCurrentCost();
        Assert.assertEquals("PriceOptimizer did not produce " +
                        "the correct price.",
                175, priceResult);
    }

    /**
     * testPriceOptimizer_testOptimize2v1 provides a second variation of
     * testPriceOptimizer_testOptimize2, testing that it is possible to make
     * 2 furniture items based on a different configuration of parts.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1() {
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
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimize2v1_3parts provides a second variation of
     * testPriceOptimizer_testOptimize2, testing that it is possible to make
     * 2 furniture items based on a different configuration of parts.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1_3Parts() {
        String[] id = {"1", "2", "3", "4"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true},
                {false, true, true},
                {false, true, false},
                {true, true, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"1","3","4"};
        String[] expected2 = {"1","2","4"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected2));
    }

    /**
     * testPriceOptimizer_testOptimize2v1_3Partsv1 provides a second variation
     * of testPriceOptimizer_testOptimize2, testing that it is possible to make
     * 2 furniture items based on a different configuration of parts.
     * With 3 parts per furniture item.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1_3Partsv1() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {false, false, true},
                {true, true, true},
                {false, true, false},
                {true, true, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"C6748", "C9890"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimize2v1_3Partsv2_allTrue provides a second
     * variation of testPriceOptimizer_testOptimize2, testing that it is
     * possible to make 2 furniture items based on a different configuration
     * of parts, with 3 parts per furniture item and all furniture items are
     * true.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1_3Partsv2_allTrue() {
        String[] id = {"1", "2", "3"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, true, true},
                {true, true, true},
                {true, true, true},
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(3);
        String[] expected = {"1","2","3"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result));
    }

    /**
     * testPriceOptimizer_testOptimize2v1_3Partsv2 provides a second variation
     * of testPriceOptimizer_testOptimize2, testing that it is possible to make
     * 2 furniture items based on a different configuration of parts.
     * With 3 parts per furniture item.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1_3Partsv2() {
        String[] id = {"1","2","3","4","5","6"};
        int[] price = {100, 75, 75, 50, 50, 60};
        boolean[][] parts = {
                {false, false, true},
                {true, false, true},
                {false, true, true},
                {false, true, false},
                {true, false, false},
                {true, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"3","4","5","6"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimize2v1_3Partsv2_compatible provides a second
     * variation of testPriceOptimizer_testOptimize2 just testing the compatible
     * method with a configuration of 3 parts per furniture item.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1_3Partsv2_compatible() {
        String[] id = {"1","2","3","4","5"};
        int[] price = {100, 75, 75, 50, 50};
        boolean[][] parts = {
                {false, false, true},
                {true, false, true},
                {false, true, true},
                {false, true, false},
                {true, false, false}
        };
        int[] list = {0,1,2,3,4};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(2);
        assertTrue(p.compatible(list));
    }

    /**
     * testPriceOptimizer_testOptimize2v1_2Parts provides a second variation of
     * testPriceOptimizer_testOptimize2, testing that it is possible to make
     * 2 furniture items based on a different configuration of parts with
     * a 2 parts per furniture item.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1_2Parts() {
        String[] id = {"1", "2", "3", "4"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false},
                {false, true},
                {false, true},
                {true, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"1", "3", "4"};
        String[] expected2 = {"1","2","4"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(expected2, result));
    }

    /**
     * testPriceOptimizer_testOptimize2v1_2Parts_SwingArm provides a second
     * variation of testPriceOptimizer_testOptimize2, testing that it is
     * possible to make 2 furniture items based on a different configuration
     * of parts with 2 parts per furniture item.
     * This test is based on Swing Arm
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1_2Parts_SwingArm() {
        String[] id = {"1","2","3","4"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false},
                {false, true},
                {true, false},
                {false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"1","2","3","4"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimizeImpossibleLarge provides test of the
     * optimize method, testing that it will return null when it is
     * impossible to create a particular number of furniture items.
     * In this case, it is possible to make 2 but impossible to make 3
     * with any configuration available.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimizeImpossibleLarge() {
        String[] id  = {"1","2","3","4","5","6","7","8"};
        int[] price = {100, 75, 75, 50, 75, 75, 100, 50};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, false, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, false, true},
                {true, true, false, false},
                {true, true, false, true},
                {true, true, true, true}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        assertNull("PriceOptimizer did not return null when" +
                        " given an impossible to combine series of pieces.",
                p.optimize(3));
    }

    /**
     * testPriceOptimizer_testOptimizeImpossibleSmall provides test of the
     * optimize method, testing that it will return null when it is
     * impossible to create a particular number of furniture items.
     * In this case, it is possible to make 2 but impossible to make 3
     * with any configuration available.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimizeImpossibleSmall() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, false, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(2);
        assertNull("PriceOptimizer did not return null when" +
                " given an impossible to combine series of pieces.",result);
    }

    /**
     *testPriceOptimizer_testConstructor1 assets if the Constructor is working
     * as intended. It sets the getParts, getID and the getPrice methods.
     */
    @Test
    public void testPriceOptimizer_testConstructor1() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, true, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        int[] price = {100, 75, 75, 50};
        PriceOptimizer p = new PriceOptimizer (id,parts,price);
        assertTrue("PriceOptimizer was not properly constructed.",
                Arrays.equals(id, p.getId()) &&
                Arrays.equals(parts, p.getParts()) &&
                        Arrays.equals(price, p.getPrice()));
    }

    /**
     * testUserIO_validOrderReadFurnCategory asserts that a valid order has been
     * placed and the user's requested furniture category has been read
     * and saved correctly.
     *
     */
    @Test
    public void testUserIO_validOrderReadFurnCategory() {
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(
                ("\nMesh chair, 1\n" + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1, false);

        assertEquals("processInput did not properly read the " +
                        "furniture category from a valid order.",
                "chair", userIO.getFurnCategory());
    }

    /**
     * testUserIO_validOrderReadFurnType asserts that a valid order has been
     * placed and the user's requested furniture type has been read
     * and saved correctly.
     *
     */
    @Test
    public void testUserIO_validOrderReadFurnType() {
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Mesh chair, 1\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1,false);

        assertEquals("processInput did not properly read the " +
                "furniture type from a valid order.",
                "Mesh", userIO.getFurnType());
    }

    /**
     * testUserIO_validOrderReadNumOfItems asserts that a valid order has been
     * placed and the user's requested number of items
     * has been read and saved correctly.
     *
     */
    @Test
    public void testUserIO_validOrderReadNumOfItems() {
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Mesh chair, 1\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1,false);

        assertEquals("processInput did not properly read the " +
                "number of items from a valid order.",
                "1", userIO.getNumOfItems());
    }

    /**
     * testUserIO_validOrderRequest asserts that a valid order has been
     * placed and the user's order request
     * has been read and saved correctly.
     *
     */
    @Test
    public void testUserIO_validOrderRequest() {
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Mesh chair, 1\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1,false);

        assertEquals("processInput did not properly read the " +
                "order request from a valid order.","Mesh chair, 1",
                userIO.getLatestRequest());
    }

    /**
     * testUserIO_invalidOrderReadFurnTypeFirstOrder asserts that if an invalid
     * order is placed as the first order, the saved furniture type will be
     * null.
     */
    @Test public void testUserIO_invalidOrderReadFurnTypeFirstOrder(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Invalid input\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1,false);

        assertNull("processInput failed to save furniture type " +
                        "as null for an invalid first order.",
                userIO.getFurnType());
    }

    /**
     * testUserIO_invalidOrderReadFurnTypeAfterFirst asserts that if an invalid
     * order is placed after a valid previous order, the saved furniture type
     * will be null.
     */
    @Test public void testUserIO_invalidOrderReadFurnTypeAfterFirst(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Mesh chair, 1\n" +
                                          "\n" +
                                          "Desk lamp, 1\n" +
                                          "\n" +
                                          "Invalid order name\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1,false); // Fulfills valid
        // order "Mesh chair, 1"

        userIO.processInput(1,false);
        // Fulfills the valid order
                                            // "Desk lamp, 1"

        userIO.processInput(1,false);
        // Attempts to fulfill the invalid
                                            // order "Invalid order name"


        assertNull("processInput failed to save furniture type " +
                "as null for an invalid order after the first order."
                ,userIO.getFurnType());
    }

    /**
     * testUserIO_invalidOrderReadCategoryFirstOrder asserts that if an invalid
     * order is placed as the first order, the saved furniture category will be
     * null.
     */
    @Test public void testUserIO_invalidOrderReadCategoryFirstOrder(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Invalid input\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1,false);

        assertNull("processInput failed to save furniture category " +
                "as null for an invalid first order.",userIO.getFurnCategory());
    }

    /**
     * testUserIO_invalidOrderReadFurnCategoryAfterFirst asserts that if an
     * invalid order is placed after a valid previous order, the saved furniture
     * category will be null.
     */
    @Test public void testUserIO_invalidOrderReadFurnCategoryAfterFirst(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Mesh chair, 1\n" +
                                          "\n" +
                                          "Desk lamp, 1\n" +
                                          "\n" +
                                          "Invalid order\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

  System.setIn(terminalInput1); // Fulfills valid order "Mesh chair, 1"
        userIO.processInput(1,false);

       userIO.processInput(1,false);
       // Fulfills the valid order
        // "Desk lamp, 1"

       userIO.processInput(1,false);
       // Attempts to fulfill the invalid
        // order "Invalid order"


        assertNull("processInput failed to save furniture category " +
                        "as null for an invalid order after the first order."
                ,userIO.getFurnCategory());
    }

    /**
     * testUserIO_invalidOrderReadNumOfItemsFirstOrder asserts that if an
     * invalid order is placed as the first order, the saved number of items
     * will be null.
     */
    @Test public void testUserIO_invalidOrderReadNumOfItemsFirstOrder(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Bad order\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        userIO.processInput(1,false);

        assertNull("processInput failed to save number of items " +
                "as null for an invalid first order.",userIO.getNumOfItems());
    }

    /**
     * testUserIO_invalidOrderReadNumOfItemsAfterFirst asserts that if an
     * invalid order is placed after a valid previous order, the saved number
     * of items will be null.
     */
    @Test public void testUserIO_invalidOrderReadNumOfItemsAfterFirst(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Mesh chair, 1\n" +
                                          "\n" +
                                          "Desk lamp, 1\n" +
                                          "\n" +
                                          "Meaningless order\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

        System.setIn(terminalInput1);
        // Fulfills valid order "Mesh chair, 1"
        userIO.processInput(1,false);

        userIO.processInput(1,false);
        // Fulfills the valid order
        // "Desk lamp, 1"

        userIO.processInput(1,false);
        // Attempts to fulfill the invalid
        // order "Meaningless order"


        assertNull("processInput failed to save number of items " +
                        "as null for an invalid order after the first order."
                ,userIO.getNumOfItems());
    }

    /**
     * testUserIO_invalidOrderReadLatestOrderFirstOrder asserts that if an
     * invalid order is placed as the first order, the saved record of the
     * latest order will be null.
     */
    @Test public void testUserIO_invalidOrderReadLatestOrderFirstOrder(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Invalid request\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();


        userIO.processInput(1,false);

        assertNull("processInput failed to save order request " +
                "as null for an invalid first order.",
                userIO.getLatestRequest());
    }

    /**
     * testUserIO_invalidOrderReadNumOfItemsAfterFirst asserts that if an
     * invalid order is placed after a valid previous order, the saved number
     * of items will be null.
     */
    @Test public void testUserIO_invalidOrderLatestOrderAfterFirst(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "Mesh chair, 1\n" +
                                          "\n" +
                                          "Desk lamp, 1\n" +
                                          "\n" +
                                          "Bad bad order\n" +
                                          "CANCEL\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();


        System.setIn(terminalInput1); // Fulfills valid order "Mesh chair, 1"
        userIO.processInput(1,false);


        userIO.processInput(1,false);
        // Fulfills the valid order
        // "Desk lamp, 1"


        userIO.processInput(1,false);
        // Attempts to fulfill the invalid
        // order "Bad bad order"


        assertNull("processInput failed to save order request " +
                        "as null for an invalid order after the first order."
                ,userIO.getLatestRequest());
    }

    /**
     * testUserIO_displaySQLCredentials asserts that when the user requests
     * to see the current MySQL credentials being used, they are
     * corrected displayed to the terminal.
     */
    @Test public void testUserIO_displaySQLCredentials(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("\n3"
                + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

      userIO.processInput(3,false);

        String expectedOutputREGEX = "(Current database URL: " +
                "jdbc:mysql://localhost/inventory)" +
                "\\R(Current database username: scm\\RCurrent database " +
                "password: \\*\\*\\*\\*\\*\\*\\*)";
        Pattern expectedPattern = Pattern.compile(expectedOutputREGEX);
        Matcher expectedMatch = expectedPattern.matcher(
                terminalContent.toString());

        boolean correctOutput = expectedMatch.find();

        assertTrue("UserIO failed to display the correct " +
                        "SQL credentials to the terminal.",
                correctOutput);

    }

    /**
     * testUserIO_displayCurrentOutputFileName asserts that the correct and
     * current output file name for the order output to be written to is
     * displayed to the user in the terminal when requested
     */
    @Test public void testUserIO_displayCurrentOutputFileName(){
        ByteArrayInputStream terminalInput1 = new ByteArrayInputStream(("\n2"
                + "\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();

      userIO.processInput(2,false);

        String expectedOutputREGEX = "(Current output file name is: " +
                "OrderOutput.txt)";
        Pattern expectedPattern = Pattern.compile(expectedOutputREGEX);
        Matcher expectedMatch = expectedPattern.matcher(terminalContent
                .toString());

        boolean correctOutput = expectedMatch.find();

        assertTrue("UserIO failed to display the correct current " +
                        "output file name to the terminal.",
                correctOutput);
    }

    /**
     * testUserIO_changeOutputFileName asserts that the user request to change
     * the current output file name the order output will be written into is
     * fulfilled by changing the stored order output file name in this instance
     * of UserIO, as well as having the updated name correctly display
     * on the terminal to the user.
     */
    @Test public void testUserIO_changeOutputFileName(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n" +
                                          "NewOutputFileName.txt\n" +
                                          "Y\n" +
                                          "2\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();


        userIO.processInput(4,false);
        // Processes the input for
                                            // requesting to change the
                                            //current output file name

        userIO.processInput(2,false);
        // Processes the input for
                                            // requesting to view the current
                                            // output file name

        String expectedOutputREGEX = "(Current output file name is: " +
                "NewOutputFileName.txt)";
        Pattern expectedPattern = Pattern.compile(expectedOutputREGEX);
        Matcher expectedMatch = expectedPattern.matcher
                    (terminalContent.toString());

        String currentOutputFileName = userIO.getOutputFile();
        boolean fileUpdated = currentOutputFileName.equals
                    ("NewOutputFileName.txt");


        boolean correctOutput = expectedMatch.find();
        assertTrue("UserIO failed to update output file name and " +
                        "display the updated output file name to " +
                        "the terminal.",
                fileUpdated && correctOutput);
    }

    @Test public void testUserIO_inValidCredentialsChange(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\nY\n" +
                        "invalidURL\n" +
                        "invalidUsername\n" +
                        "invalidPassword\n" +
                        "Y\n\n").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();


        userIO.processInput(5,false);
        // Processes the input for
        // requesting to change the
        //current MySQL credentials to invalid values

        userIO.processInput(1,false);
        // Process the input for requesting
        // a new order using the invalid MySQL Credentials


        String expectedOutputREGEX = "(Unable to create a connection " +
                "with\\Rthe credentials:\\R[ ]{5}DbURL: " +
                "invalidURL\\R[ ]{2}Username: " +
                "invalidUsername\\R[ ]{2}Password: " +
                "\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*)";
        Pattern expectedPattern = Pattern.compile(expectedOutputREGEX);
        Matcher expectedMatch = expectedPattern.matcher
                (terminalContent.toString());


        boolean correctOutput = expectedMatch.find();
        assertTrue("UserIO failed to identify and print message " +
                "for invalid SQL credentials and failed connection to " +
                "the database.", correctOutput);
    }

    @Test public void testUserIO_ValidCredentialsChange(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\nY\n" +
                        "invalidURL\n" +
                        "invalidUsername\n" +
                        "invalidPassword\n" +
                        "Y\n\n" +
                        "\nY\n" +
                        "jdbc:mysql://localhost/inventory\n" +
                        "scm\n" +
                        "ensf409\n" +
                        "Y\n\nCANCEL").getBytes());
        System.setIn(terminalInput1);
        UserIO userIO = new UserIO();
        userIO.processInput(5,false);
        // Processes the input for
        // requesting to change the
        // current MySQL credentials to invalid values.
        userIO.processInput(5,false);
        // Processes the input for
        // requesting to change the
        //current MySQL credentials to valid values.

        userIO.processInput(1,false);
        // Process the input for requesting
        // a new order using the valid MySQL Credentials

        String expectedOutputREGEX
                = "(Please input request for furniture item)";
        Pattern expectedPattern = Pattern.compile(expectedOutputREGEX);
        Matcher expectedMatch = expectedPattern.matcher
                (terminalContent.toString());


        boolean correctOutput = expectedMatch.find();
        assertTrue("UserIO failed to identify and implement a " +
                        "valid SQL credentials change.",
                correctOutput);
    }


    /**
     * testUserIO_QuiteMenu asserts that upon the user entering the value
     * 0, the method processInput will return false.
     */
    @Test public void testUserIO_QuitMenu(){
        UserIO ioTest = new UserIO();
        boolean returnedFromIO = ioTest.processInput(0,
                false);
        assertFalse("processInput failed to return " +
                        "false upon having input 0.",
                returnedFromIO);
    }


    /**
     * testUserIO_ContinueMenu asserts that when the user puts in a non-zero
     * value between 1 and 5, processInput will return true.
     */
    @Test public void testUserIO_ContinueMenu(){
        ByteArrayInputStream terminalInput1 =
                new ByteArrayInputStream(("\n\n\n").getBytes());
        System.setIn(terminalInput1);
        UserIO testIO = new UserIO();
        boolean fromOption2 = testIO.processInput(2,false);
        boolean fromOption3 = testIO.processInput(3,false);
        assertTrue("processInput failed to return true when " +
                        "provided a valid, non-zero input.",
                fromOption2 && fromOption3);
    }



    /**
     * testDatabaseIO_testValidChairDataTypeTask asserts that the
     * PriceOptimizer object created by the method getChairData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Task"
     */
    @Test
    public void testDatabaseIO_testValidChairDataTypeTask(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"C0914", "C1148", "C3405"};
        int[] price = {50, 125, 100};
        boolean [][] parts = {{false, false, true, true},
                {true, false, true, true} ,{true, true, false, false}};

        PriceOptimizer arrays = database.getChairData("Task");

        assertTrue("Chair data obtained for " +
                        "type Task is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));

    }

    /**
     * testDatabaseIO_testValidChairDataTypeMesh asserts that the
     * PriceOptimizer object created by the method getChairData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Mesh"
     */
    @Test
    public void testDatabaseIO_testValidChairDataTypeMesh(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {175, 75, 75, 50};
        boolean [][] parts = {{true, false, true, true},
                {true, false, false, false} ,{false, false, true, false},
                {false, true, false, true}};

        PriceOptimizer arrays = database.getChairData("Mesh");

        assertTrue("Chair data obtained for " +
                        "type Mesh is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));
    }

    /**
     * testDatabaseIO_testInvalidChairData asserts that if an
     * invalid type is entered into the method getLampData,
     * the PriceOptimizer object created is null.
     */
    @Test
    public void testDatabaseIO_testInvalidChairData(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertNull("The given type for chair does actually exist " +
                "in the database." ,database.getChairData("invalid"));
    }

    /**
     * testDatabaseIO_testValidDeskDataTypeStanding asserts that the
     * PriceOptimizer object created by the method getDeskData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Standing"
     */
    @Test
    public void testDatabaseIO_testValidDeskDataTypeStanding(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"D1927", "D2341", "D3820", "D4438", "D9387"};
        int[] price = {200, 100, 150, 150, 250};
        boolean [][] parts = {{true, false, true}, {false, true, false}
                ,{true, false, false}, {false, true, true},
                {true, true, false}};

        PriceOptimizer arrays = database.getDeskData("Standing");

        assertTrue("Desk data obtained for type " +
                "Standing is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));
    }

    /**
     * testDatabaseIO_testValidDeskDataTypeTraditional asserts that the
     * PriceOptimizer object created by the method getDeskData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Traditional"
     */
    @Test
    public void testDatabaseIO_testValidDeskDataTypeTraditional(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"D0890", "D4231", "D8675", "D9352"};
        int[] price = {25, 50, 75, 75};
        boolean [][] parts = {{false, false, true}, {false, true, true}
                ,{true, true, false}, {true, false, true}};

        PriceOptimizer arrays = database.getDeskData("Traditional");

        assertTrue("Desk data obtained for type " +
                        "Traditional is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));
    }

    /**
     * testDatabaseIO_testInvalidDeskData asserts that if an
     * invalid type is entered into the method getDeskData,
     * the PriceOptimizer container created is null.
     */
    @Test
    public void testDatabaseIO_testInvalidDeskData(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertNull("The given type for desk does actually exist " +
                "in the database.", database.getDeskData("invalid"));
    }

    /**
     * testDatabaseIO_testValidLampDataTypeSwingArm asserts that the
     * PriceOptimizer container created by the method getLampData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Swing Arm"
     */
    @Test
    public void testDatabaseIO_testValidLampDataTypeSwingArm(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"L053", "L096", "L487", "L879"};
        int[] price = {27, 3, 27, 3};
        boolean [][] parts = {{true, false}, {false, true}
                ,{true, false}, {false, true}};

        PriceOptimizer arrays = database.getLampData("Swing Arm");

        assertTrue("Lamp data obtained for type " +
                        "Swing Arm is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));
    }

    /**
     * testDatabaseIO_testValidLampDataTypeStudy asserts that the
     * PriceOptimizer container created by the method getLampData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Study"
     */
    @Test
    public void testDatabaseIO_testValidLampDataTypeStudy(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"L223", "L928", "L980", "L982"};
        int[] price = {2, 10, 2, 8};
        boolean [][] parts = {{false, true}, {true, true}
                ,{false, true}, {true, false}};

        PriceOptimizer arrays = database.getLampData("Study");

        assertTrue("Lamp data obtained for type " +
                        "Study is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));
    }

    /**
     * testDatabaseIO_testInvalidLampData asserts that if an
     * invalid type is entered into the method getLampData,
     * the PriceOptimizer container created is null.
     */
    @Test
    public void testDatabaseIO_testInvalidLampData(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertNull("The given type for lamp does actually exist " +
                "in the database.", database.getLampData("invalid"));
    }

    /**
     * testDatabaseIO_testValidFilingDataTypeLarge asserts that the
     * PriceOptimizer container created by the method getFilingData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Large"
     */
    @Test
    public void testDatabaseIO_testValidFilingDataTypeLarge(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"F003", "F010", "F011", "F012", "F015"};
        int[] price = {150, 225, 225, 75, 75};
        boolean [][] parts = {{false, false, true}, {true, false, true}
                ,{false, true, true}, {false, true, false},
                {true, false, false}};

        PriceOptimizer arrays = database.getFilingData("Large");

        assertTrue("Filing data obtained for type " +
                        "Large is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));
    }

    /**
     * testDatabaseIO_testValidFilingDataTypeSmall asserts that the
     * PriceOptimizer container created by the method getFilingData produces
     * the correct String array of ID's, int array of prices, and boolean
     * 2D array of parts for the type "Small"
     */
    @Test
    public void testDatabaseIO_testValidFilingDataTypeSmall(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] id = {"F001", "F004", "F005", "F006", "F013"};
        int[] price = {50, 75, 75, 50, 50};
        boolean [][] parts = {{true, true, false}, {false, true, true}
                ,{true, false, true}, {true, true, false},
                {false, false, true}};

        PriceOptimizer arrays = database.getFilingData("Small");

        assertTrue("Filing data obtained for type " +
                        "Small is not the same " +
                "as listed in the database.",
                Arrays.equals(id, arrays.getId())
                && Arrays.equals(price, arrays.getPrice())
                && Arrays.deepEquals(parts, arrays.getParts()));
    }

    /**
     * testDatabaseIO_testInvalidFilingData asserts that if an
     * invalid type is entered into the method getLampData,
     * the PriceOptimizer container created is null.
     */
    @Test
    public void testDatabaseIO_testInvalidFilingData(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertNull("The given type for filing does actually exist " +
                "in the database.", database.getFilingData("invalid"));
    }

    /**
     * testDatabaseIO_testSuggestedManufacturersChair asserts
     * that the String ArrayList created by the method suggestedManufacturers
     * produces the correct String ArrayList of the suggested
     * manufacturers for chairs.
     */
    @Test
    public void testDatabaseIO_testSuggestedManufacturersChair(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] checkChair = {"Office Furnishings", "Chairs R Us",
                "Furniture Goods", "Fine Office Supplies"};
        ArrayList<String> test
                = database.suggestedManufacturers("chair");
        String[] testArray = test.toArray(new String[0]);

        assertArrayEquals("The incorrect manufacturers " +
                "were listed for any " +
                "Chair furniture.", checkChair, testArray);
    }

    /**
     * testDatabaseIO_testSuggestedManufacturersLamp asserts
     * that the String ArrayList created by the method suggestedManufacturers
     * produces the correct String ArrayList of the suggested
     * manufacturers for lamps.
     */
    @Test
    public void testDatabaseIO_testSuggestedManufacturersLamp(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        String[] checkChair = {"Office Furnishings", "Furniture Goods",
                "Fine Office Supplies"};
        ArrayList<String> test
                = database.suggestedManufacturers("lamp");
        String[] testArray = test.toArray(new String[0]);

        assertArrayEquals("The incorrect manufacturers were " +
                "listed for any " +
                "Lamp furniture.", checkChair, testArray);
    }

    /**
     * testDatabaseIO_testSuggestedManufacturersInvalid asserts
     * that if an invalid furniture is entered into the method
     * suggestedManufacturers, the String Arraylist created is null.
     */
    @Test
    public void testDatabaseIO_testSuggestedManufacturersInvalid(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertNull("Manufacturers were listed for a table that " +
                        "does not " +
                        "exist in the database."
                ,database.suggestedManufacturers("invalid"));
    }

    /**
     * testDatabaseIO_testGetSizeOfDeskTypeStanding asserts
     * that the int created by the method getSize produces
     * the correct int of the number of standing desks.
     */
    @Test
    public void testDatabaseIO_testGetSizeOfDeskTypeStanding(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertEquals("The number of standing desks in the" +
                        " database is " +
                        "not of the correct value", 5,
                database.getSize("desk", "Standing"));
    }

    /**
     * testDatabaseIO_testGetSizeOfLampTypeDesk asserts
     * that the int created by the method getSize produces
     * the correct int of the number of desk lamps.
     */
    @Test
    public void testDatabaseIO_testGetSizeOfLampTypeDesk(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertEquals("The number of desk lamps in the database is " +
                        "not of the correct value.", 7,
                database.getSize("lamp", "Desk"));
    }

    /**
     * testDatabaseIO_testGetSizeOfFilingInvalidType asserts
     * that if an invalid type is entered into the method getSize,
     * the int created is 0.
     */
    @Test
    public void testDatabaseIO_testGetSizeOfFilingInvalidType(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertEquals("The number of invalid filing's should not " +
                        "be greater than or less than zero.", 0,
                database.getSize("filing", "invalid"));
    }

    /**
     * testDatabaseIO_testTypeMediumExistsOfFiling asserts that
     * the correct table name "Filing" and furniture type "Medium"
     * is entered into the method typeExists.
     */
    @Test
    public void testDatabaseIO_testTypeMediumExistsOfFiling(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertTrue("The type Medium does not exist in the " +
                        "filing table in the database."
                ,database.typeExists("filing", "Medium"));
    }

    /**
     * testDatabaseIO_testTypeAdjustableOfDesk asserts that
     * the correct table name "Desk" and furniture type "Adjustable"
     * is entered into the method typeExists.
     */
    @Test
    public void testDatabaseIO_testTypeAdjustableOfDesk(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertTrue("The type Adjustable does not exist in the " +
                        "desk table in the database."
                , database.typeExists("desk", "Adjustable"));
    }

    /**
     * testDatabaseIO_testTypeInvalidOfChair asserts that
     * the correct table name "Chair" but incorrect furniture
     * type "invalid" is entered into the method typeExists.
     */
    @Test
    public void testDatabaseIO_testTypeInvalidOfChair(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        assertFalse("The type Invalid does exist in the " +
                        "chair table in the database but it should not " +
                        "as it is an invalid type.",
                database.typeExists("chair", "invalid"));
    }

    /**
     * testDatabaseIO_removeItemChairOfTypeKneeling() asserts that
     * the specified item is removed from that database when the
     * correct itemID and table is entered into method removeItem
     */
    @Test
    public void testDatabaseIO_removeItemChairOfTypeKneeling(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        database.removeItem("chair", "C1320");

        PriceOptimizer priceOptimizer = database.getChairData("Kneeling");
        String[] idBefore = priceOptimizer.getId();
        int[] priceBefore = priceOptimizer.getPrice();
        boolean[][] partsBefore = priceOptimizer.getParts();


        try{
            Connection dbConnect =
                    DriverManager.getConnection(database.getDbUrl(),
                            database.getUsername(), database.getPassword());
            String query = "INSERT INTO chair " +
                    "(ID, Type, Legs, Arms, Seat, Cushion, Price, ManuID) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement chairStmt = dbConnect.prepareStatement(query);

            chairStmt.setString(1, "C1320");
            chairStmt.setString(2, "Kneeling");
            chairStmt.setString(3, "Y");
            chairStmt.setString(4, "N");
            chairStmt.setString(5, "N");
            chairStmt.setString(6, "N");
            chairStmt.setInt(7, 50);
            chairStmt.setString(8, "002");

            chairStmt.executeUpdate();
            chairStmt.close();
            dbConnect.close();
        } catch (SQLException e){
            System.out.println("Database Error");
        }

        priceOptimizer = database.getChairData("Kneeling");
        String[] idAfter = priceOptimizer.getId();
        int[] priceAfter = priceOptimizer.getPrice();
        boolean[][] partsAfter = priceOptimizer.getParts();

        assertFalse("The item with ID C1320 was not removed " +
                        "from the database" +
                        " or did not exist in the database.",
                Arrays.equals(idBefore, idAfter)
                        || Arrays.deepEquals(partsBefore, partsAfter));
    }

    /**
     * testDatabaseIO_removeTwoItemLampOfTypeStudy asserts that
     * the two specified items are removed from that database when the
     * correct itemID and table is entered into method removeItem
     */
    @Test
    public void testDatabaseIO_removeTwoItemLampOfTypeStudy(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        database.removeItem("lamp", "L980");
        database.removeItem("lamp", "L982");

        PriceOptimizer priceOptimizer = database.getLampData("Study");
        String[] idBefore = priceOptimizer.getId();
        int[] priceBefore = priceOptimizer.getPrice();
        boolean[][] partsBefore = priceOptimizer.getParts();


        try{
            Connection dbConnect =
                    DriverManager.getConnection(database.getDbUrl(),
                            database.getUsername(), database.getPassword());
            String query = "INSERT INTO lamp " +
                    "(ID, Type, Base, Bulb, Price, ManuID) " +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement lampStmt = dbConnect.prepareStatement(query);

            lampStmt.setString(1, "L980");
            lampStmt.setString(2, "Study");
            lampStmt.setString(3, "N");
            lampStmt.setString(4, "Y");
            lampStmt.setInt(5, 2);
            lampStmt.setString(6, "004");

            lampStmt.executeUpdate();
            lampStmt = dbConnect.prepareStatement(query);

            lampStmt.setString(1, "L982");
            lampStmt.setString(2, "Study");
            lampStmt.setString(3, "Y");
            lampStmt.setString(4, "N");
            lampStmt.setInt(5, 8);
            lampStmt.setString(6, "002");


            lampStmt.executeUpdate();
            lampStmt.close();
            dbConnect.close();
        } catch (SQLException e){
            System.out.println("Database Error");
        }

        priceOptimizer = database.getLampData("Study");
        String[] idAfter = priceOptimizer.getId();
        int[] priceAfter = priceOptimizer.getPrice();
        boolean[][] partsAfter = priceOptimizer.getParts();

        assertFalse("The items with ID L980 and L982 were not" +
                        " removed from the database" +
                        " or did not exist in the database.",
                Arrays.equals(idBefore, idAfter)
                        || Arrays.deepEquals(partsBefore, partsAfter));
    }

    /**
     * testDatabaseIO_removeInvalidItemFilingOfTypeLarge asserts that
     * the specified item is not removed from that database when the
     * incorrect itemID but correct table is entered into method removeItem
     */
    @Test
    public void testDatabaseIO_removeInvalidItemFilingOfTypeLarge(){
        DatabaseIO database = new DatabaseIO();
        database.createConnection();

        PriceOptimizer priceOptimizer = database.getFilingData("Large");
        String[] idBefore = priceOptimizer.getId();
        int[] priceBefore = priceOptimizer.getPrice();
        boolean[][] partsBefore = priceOptimizer.getParts();

        database.removeItem("filing", "F999");

        priceOptimizer = database.getFilingData("Large");
        String[] idAfter = priceOptimizer.getId();
        int[] priceAfter = priceOptimizer.getPrice();
        boolean[][] partsAfter = priceOptimizer.getParts();

        assertTrue("An item with an invalid ID altered the database " +
                "even though the ID does not correspond to any " +
                "furniture item.", Arrays.equals(idBefore, idAfter)
                && Arrays.deepEquals(partsBefore, partsAfter));
    }
}
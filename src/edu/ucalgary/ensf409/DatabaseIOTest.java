package edu.ucalgary.ensf409;

import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class DatabaseIOTest {

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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertNull(database.getChairData("invalid"));
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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertNull(database.getDeskData("invalid"));
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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertNull(database.getLampData("invalid"));
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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertTrue(Arrays.equals(id, arrays.getId())
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

        assertNull(database.getFilingData("invalid"));
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
        ArrayList<String> test = database.suggestedManufacturers("chair");
        String[] testArray = test.toArray(new String[0]);

        assertArrayEquals(checkChair, testArray);
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
        ArrayList<String> test = database.suggestedManufacturers("lamp");
        String[] testArray = test.toArray(new String[0]);

        assertArrayEquals(checkChair, testArray);
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

        assertNull(database.suggestedManufacturers("invalid"));
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

        assertEquals(5, database.getSize("desk", "Standing"));
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

        assertEquals(7, database.getSize("lamp", "Desk"));
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

        assertEquals(0, database.getSize("filing", "invalid"));
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

        assertTrue(database.typeExists("filing", "Medium"));
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

        assertTrue(database.typeExists("desk", "Adjustable"));
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

        assertFalse(database.typeExists("chair", "invalid"));
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
            Connection dbConnect = DriverManager.getConnection(database.getDbUrl(),
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

        assertFalse(Arrays.equals(idBefore, idAfter)
                || Arrays.deepEquals(partsBefore, partsAfter)
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
            Connection dbConnect = DriverManager.getConnection(database.getDbUrl(),
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

        assertFalse(Arrays.equals(idBefore, idAfter)
                || Arrays.deepEquals(partsBefore, partsAfter)
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

        assertTrue(Arrays.equals(idBefore, idAfter)
                && Arrays.deepEquals(partsBefore, partsAfter)
                && Arrays.deepEquals(partsBefore, partsAfter));
    }
}
package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

/**
 * DatabaseIO is a class used to
 */

public class DatabaseIO {
    private Connection dbConnect;
    private ResultSet results;

    private String dbUrl;
    private String username;
    private String password;

    /**
     * Constructor for DatabaseIO in the case that no username or password has been entered.
     * Sets the username variable as "scm" and the password variable as "ensf409".
     */
    public DatabaseIO(){
        this.dbUrl = "jdbc:mysql://localhost/inventory";
        this.username = "scm";
        this.password = "ensf409";
    }

    /**
     * Constructor for DatabaseIO in the case that the user has inputted their username and password.
     * The DatabaseIO object can be constructed using the given data.
     * @param username String username is the user-entered variable of their username
     * @param password String password is the user-entered variable of their password
     */
    public DatabaseIO(String username, String password){
        this.dbUrl = "jdbc:mysql://localhost/inventory";
        this.username = username;
        this.password = password;
    }

    /**
     * Getter method for the database URL
     * @return string of database URL
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * Getter method for the users mySQL username
     * @return string of mySQL username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method for the users mySQL password
     * @return string of mySQL password
     */
    public String getPassword() {
        return password;
    }

    /**
     * The method initializeConnection promises to create a connection to the database otherwise
     * it will output an error.
     */
    public void createConnection(){
        try{
            this.dbConnect = DriverManager.getConnection(this.dbUrl, this.username, this.password);
        } catch (SQLException e){
            System.out.println("Unable to create a connection.");
            e.printStackTrace();
        }
    }


    /**
     * The method getChairData takes in a String of the furniture type.
     * The method promises to return an object of FurnitureConfigurationData which contains
     * the list of prices, list of parts and list of ID's for the furniture type given.
     * @param type String of the furniture type
     * @return object FurnitureConfigurationData containing data from the Chair table
     */
    public FurnitureConfigurationData getChairData(String type){
        int size = getSize("chair", type);
        String [] idList = new String[size];
        int [] priceList = new int[size];
        boolean [][] partList = new boolean[size][4];

        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM chair");

            for(int i = 0; i < size; i++){
                if(results.getString("type").equals(type)){
                    idList[i] = results.getString("id");
                    priceList[i] = results.getInt("price");
                    partList[i][0] = results.getString("legs").equals("Y");
                    partList[i][0] = results.getString("arms").equals("Y");
                    partList[i][0] = results.getString("seat").equals("Y");
                    partList[i][0] = results.getString("cushion").equals("Y");
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }

    /**
     * The method getDeskData takes in a String of the furniture type.
     * The method promises to return an object of FurnitureConfigurationData which contains
     * the list of prices, list of parts and list of ID's for the furniture type given.
     * @param type String of the furniture type
     * @return object FurnitureConfigurationData containing data from the Desk table
     */
    public FurnitureConfigurationData getDeskData(String type){
        int size = getSize("desk", type);
        String [] idList = new String[size];
        int [] priceList = new int[size];
        boolean [][] partList = new boolean[size][3];

        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM desk");

            for(int i = 0; i < size; i++){
                if(results.getString("type").equals(type)){
                    idList[i] = results.getString("id");
                    priceList[i] = results.getInt("price");
                    partList[i][0] = results.getString("legs").equals("Y");
                    partList[i][1] = results.getString("top").equals("Y");
                    partList[i][2] = results.getString("drawer").equals("Y");
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }

    /**
     * The method getLampData takes in a String of the furniture type.
     * The method promises to return an object of FurnitureConfigurationData which contains
     * the list of prices, list of parts and list of ID's for the furniture type given.
     * @param type String of the furniture type
     * @return object FurnitureConfigurationData containing data from the Lamp table
     */
    public FurnitureConfigurationData getLampData(String type){
        int size = getSize("lamp", type);
        String [] idList = new String[size];
        int [] priceList = new int[size];
        boolean [][] partList = new boolean[size][2];

        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM lamp");

            for(int i = 0; i < size; i++){
                if(results.getString("type").equals(type)){
                    idList[i] = results.getString("id");
                    priceList[i] = results.getInt("price");
                    partList[i][0] = results.getString("base").equals("Y");
                    partList[i][1] = results.getString("bulb").equals("Y");
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }

    /**
     * The method getFilingData takes in a String of the furniture type.
     * The method promises to return an object of FurnitureConfigurationData which contains
     * the list of prices, list of parts and list of ID's for the furniture type given.
     * @param type String of the furniture type
     * @return object FurnitureConfigurationData containing data from the Filing table
     */
    public FurnitureConfigurationData getFilingData(String type){
        int size = getSize("filing", type);
        String [] idList = new String[size];
        int [] priceList = new int[size];
        boolean [][] partList = new boolean[size][3];

        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM filing");

            for(int i = 0; i < size; i++){
                if(results.getString("type").equals(type)){
                    idList[i] = results.getString("id");
                    priceList[i] = results.getInt("price");
                    partList[i][0] = results.getString("rails").equals("Y");
                    partList[i][1] = results.getString("drawers").equals("Y");
                    partList[i][2] = results.getString("cabinet").equals("Y");
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }


    /**
     * The method removeItem deletes the the furniture item corresponding to the ID given
     * from the given table name.
     * @param tableName String variable of the type of furniture
     * @param ID String variable of the ID of the furniture
     */
    public void removeItem(String tableName, String ID){
        try {
            String query = "DELETE FROM " + tableName + " WHERE " + ID + " = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, ID);

            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException e) {
            System.out.println("Database error while removing ");
            e.printStackTrace();
        }
    }

    /**
     * The method suggestedManufacturers creates a list of suggested manufacturers to choose from if a user
     * request is not fulfilled for a certain furniture.
     * @param tableName String variable of the furniture
     * @return String ArrayList of the suggested manufacturers of a furniture
     */
    public ArrayList<String> suggestedManufacturers(String tableName){
        ArrayList<String> manuList = new ArrayList<String>();
        ArrayList<String> suggestList = new ArrayList<String>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + tableName);

            while(results.next()){
                String tempManu = results.getString("manuid");
                if(!manuList.contains(tempManu)){
                    manuList.add(tempManu);
                }
            }

            results = myStmt.executeQuery("SELECT * FROM manufacturer");

            while(results.next()){
                String tempManu = results.getString("manuid");
                if(manuList.contains(tempManu)){
                    suggestList.add(results.getString("name"));
                }
            }

        } catch(SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return suggestList;
    }

    /**
     * The method getSize takes in a table corresponding to a certain furniture and also obtains
     * the furniture type. The method promises to return an int of the number of a certain furniture type
     * in the given table.
     * @param table String variable of furniture name
     * @param type String variable of type of furniture
     * @return number of furniture of type specified by user
     */
    public int getSize(String table, String type){
        int size = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + table);

            while(results.next()){
                if(results.getString("type").equals(type)){
                    size++;
                }
            }
        } catch(SQLException e){
            System.out.println();
        }
        return size;
    }


    /**
     * The method close promises to close the database connection.
     */
    public void close(){
        try{
            dbConnect.close();
            results.close();
        } catch (SQLException e){
            System.out.println("Unable to close the connection");
            e.printStackTrace();
        }
    }
}

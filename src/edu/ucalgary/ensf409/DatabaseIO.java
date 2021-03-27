package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseIO {
    private Connection dbConnect;
    private ResultSet results;

    private String dbUrl;
    private String username;
    private String password;

    public DatabaseIO(){
        this.dbUrl = "jdbc:mysql://localhost/inventory";
        this.username = "scm";
        this.password = "ensf409";
    }

    public DatabaseIO(String username, String password){
        this.dbUrl = "jdbc:mysql://localhost/inventory";
        this.username = username;
        this.password = password;
    }

    public void createConnection(){
        try{
            this.dbConnect = DriverManager.getConnection(this.dbUrl, this.username, this.password);
        } catch (SQLException e){
            System.out.println("Unable to create a connection.");
            e.printStackTrace();
        }
    }


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
                    partList[i][0] = getBoolean(results.getString("legs"));
                    partList[i][0] = getBoolean(results.getString("arms"));
                    partList[i][0] = getBoolean(results.getString("seat"));
                    partList[i][0] = getBoolean(results.getString("cushion"));
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }

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
                    partList[i][0] = getBoolean(results.getString("legs"));
                    partList[i][1] = getBoolean(results.getString("top"));
                    partList[i][2] = getBoolean(results.getString("drawer"));
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }

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
                    partList[i][0] = getBoolean(results.getString("base"));
                    partList[i][1] = getBoolean(results.getString("bulb"));
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }

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
                    partList[i][0] = getBoolean(results.getString("rails"));
                    partList[i][1] = getBoolean(results.getString("drawers"));
                    partList[i][2] = getBoolean(results.getString("cabinet"));
                }
            }

        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return new FurnitureConfigurationData(idList, partList, priceList);
    }


    public void removeItem(String tableName, String ID){
        try {
            String query = "DELETE FROM " + tableName + " WHERE " + ID + " = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, ID);

            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

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
                    suggestList.add(tempManu);
                }
            }

        } catch(SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }

        return suggestList;
    }

    public void close(){
        try{
            dbConnect.close();
            results.close();
        } catch (SQLException e){
            System.out.println("Unable to close the connection");
            e.printStackTrace();
        }
    }

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

    public boolean getBoolean(String bool){
        if(bool.equals("Y")){
            return true;
        } else{
            return false;
        }
    }

}

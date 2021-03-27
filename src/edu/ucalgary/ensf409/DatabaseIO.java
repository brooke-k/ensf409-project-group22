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

    public ArrayList<String> getID(String tableName, String type){
        ArrayList<String> idList = new ArrayList<String>();

        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + tableName);

            while(results.next()){
                if(results.getString("type").equals(type)){
                    idList.add(results.getString("id"));
                }
            }

        } catch (SQLException e){
            System.out.println("Database does not exist");
            e.printStackTrace();
        }

        return idList;
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

    public void close(){
        try{
            dbConnect.close();
        } catch (SQLException e){
            System.out.println("Unable to close the connection");
            e.printStackTrace();
        }
    }

}

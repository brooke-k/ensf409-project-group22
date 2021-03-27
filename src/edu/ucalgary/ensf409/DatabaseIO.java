package edu.ucalgary.ensf409;

import java.sql.*;

public class DatabaseIO {
    private Connection dbConnect;
    private String dbUrl;
    private String username;
    private String password;

    public DatabaseIO(){
        this.dbUrl = "jdbc:mysql://localhost/inventory";
        this.username = "";
        this.password = "";
    }

    public void createConnection(){
        try{
            this.dbConnect = DriverManager.getConnection(this.dbUrl, this.username, this.password);
        } catch (SQLException e){
            System.out.println("Unable to create a connection.");
            e.printStackTrace();
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
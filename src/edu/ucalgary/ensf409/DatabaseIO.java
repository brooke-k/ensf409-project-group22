package edu.ucalgary.ensf409;
import java.sql.*;
public class DatabaseIO {
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection myConnect;
    /**
     * Tries to initialize the connection with the database.
     */
    public void  initializeConnection(){
        try {
            myConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

package edu.ucalgary.ensf409;
import java.sql.*;
public class DatabaseIO {
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection myConnect;

    /**
     *
     * @param DBURL The location at which the database exits.
     * @param USERNAME The user's username.
     * @param PASSWORD The user's password.
     */
    public DatabaseIO(String DBURL, String USERNAME, String PASSWORD){
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }
    public String getDburl() {
        return DBURL;
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }

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

    /**
     * Closes the connection to the database.
     */
    public void close(){
        try {
            myConnect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

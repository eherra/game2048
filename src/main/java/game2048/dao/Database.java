/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2048.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

    /**
     * Class to create a connection to the database and handles of creating the database.
     */
public class Database {
    private String databaseUrl;
    
    public Database() {
        databaseUrl = "jdbc:sqlite:main.db";
    }
    
    /**
     * Method for getting connection.
     * @return connection object
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            System.out.println("Getting database connection failed");
        }
        return conn;
    }
    /**
     * Creates SQLtable with columns of id, score, boardsize, date.
     */
    public void createHighscoreTable() {
        try {
            Connection db = getConnection();    
            Statement s = db.createStatement();
            s.execute("CREATE TABLE Scores (id INTEGER PRIMARY KEY, score Integer, boardsize Integer, date String)");
            System.out.println("Database added succesfully");
            s.close();
            db.close();
        } catch (Exception e) {
            System.out.println("Table existing already");
        }
    }

 }
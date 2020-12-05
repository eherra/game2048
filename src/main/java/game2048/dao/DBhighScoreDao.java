package game2048.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBhighScoreDao implements HighscoreDao  {
    private Database database;
    
    public DBhighScoreDao() {
        database = new Database();
        database.createHighscoreTable(); // ei tartte joka kerralla tehdä
    }
    
    @Override 
    public void addScore(int value, int boardSize, String date) {
        try {
            Connection db = database.getConnection();    
            PreparedStatement p = db.prepareStatement("INSERT INTO Scores(score, boardsize, date) VALUES (?,?,?)");
            p.setInt(1, value);
            p.setInt(2, boardSize);
            p.setString(3, date);
            p.executeUpdate();
            db.close();
            System.out.println("Score added succesfully");
        } catch (Exception e) {
            System.out.println("adding score didn't work");
        }
    }
    
    @Override 
    public List<String> getTopFiveScores(int boardSize) {
        ArrayList<String> topScores = new ArrayList();
        try {
            Connection db = database.getConnection();    
            Statement s = db.createStatement();
            ResultSet r = s.executeQuery("SELECT score AS score, date AS date FROM Scores WHERE boardsize=" + boardSize + " ORDER BY score DESC LIMIT 5");
            int rankIndex = 1;
            while (r.next()) {
                StringBuilder build = new StringBuilder();
                build.append(rankIndex + ". "  + (r.getInt("score")) + "p, ");
                build.append(r.getString("date"));
                topScores.add(build.toString());
                rankIndex++;
            }
            db.close();
            s.close();
        } catch (Exception e) {
            System.out.println("didnt work scorefetch");
        }
        
        return topScores;
    }

    @Override
    public int getTopScore(int boardSize) {
        int topScore = 0;
        try {
            Connection db = database.getConnection();    
            Statement s = db.createStatement();
            ResultSet r = s.executeQuery("SELECT MAX(score) AS maxScore FROM Scores WHERE boardsize=" + boardSize);
            while (r.next()) {
                topScore = r.getInt("maxScore");
            }
            s.close();
            db.close();
            System.out.println("TopScore fetched succesfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            topScore = 0;
        }
        
        return topScore;
    }
    
    @Override 
    public int getFifthScore(int boardSize) {
        int fifthScore = 0;
        try {
            Connection db = database.getConnection();    
            Statement s = db.createStatement();
            ResultSet r = s.executeQuery("SELECT score AS fifthScore FROM Scores WHERE boardsize=" + boardSize + " AND rowid = 5");
            while (r.next()) {
                fifthScore = r.getInt("fifthScore");
            }
            s.close();
            db.close();
            System.out.println("fifthScore fetched succesfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return fifthScore;
    }
}
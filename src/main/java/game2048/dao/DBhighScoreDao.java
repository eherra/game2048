package game2048.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for making queries to the database.
 */
public class DBhighScoreDao implements HighscoreDao  {
    private Database database;
    
    public DBhighScoreDao(boolean testing) {
        database = new Database(testing);
        database.createHighscoreTable(); 
    }
    
    /**
     * Adds value(game points) to the sql table.
     */
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
    
    /**
     * Get top5 scores from sql database.
     * @return List of 5 top scores in a string modified with rank number, points and date when made.
     */
    @Override 
    public List<String> getTopFiveScores(int boardSize) {
        ArrayList<String> topScores = new ArrayList();
        try {
            Connection db = database.getConnection();    
            PreparedStatement p = db.prepareStatement("SELECT id, score AS score, date AS date FROM Scores WHERE boardsize=? GROUP BY id ORDER BY score DESC LIMIT 5");
            p.setInt(1, boardSize);
            int rankIndex = 1;
            ResultSet r = p.executeQuery();
            while (r.next()) {
                StringBuilder build = new StringBuilder();
                build.append(rankIndex + ". "  + (r.getInt("score")) + "p, " + r.getString("date"));
                topScores.add(build.toString());
                rankIndex++;
            }
            db.close();
            p.close();
        } catch (Exception e) {
            System.out.println("didnt work scorefetch");
        }
        
        return topScores;
    }

    /**
     * Get top score from database.
     * @return best score from database.
     */
    @Override
    public int getTopScore(int boardSize) {
        int topScore = 0;
        try {
            Connection db = database.getConnection();    
            PreparedStatement p = db.prepareStatement("SELECT MAX(score) AS maxScore FROM Scores WHERE boardsize=?");
            p.setInt(1, boardSize);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                topScore = r.getInt("maxScore");
            }
            p.close();
            db.close();
            System.out.println("TopScore fetched succesfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            topScore = 0;
        }
        
        return topScore;
    }
    
    /**
     * Get fifth best score from database.
     * Method used to check if made score on top 5 scores.
     */
    @Override 
    public int getFifthScore(int boardSize) {
        int fifthScore = 0;
        try {
            Connection db = database.getConnection();    
            PreparedStatement p = db.prepareStatement("SELECT score AS fifthScore FROM Scores WHERE boardsize=? ORDER BY score DESC LIMIT 1 OFFSET 4");
            p.setInt(1, boardSize);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                fifthScore = r.getInt("fifthScore");
            }
            p.close();
            db.close();
            System.out.println("fifthScore fetched succesfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fifthScore;
    }
    /**
     * For testing.
     * To delete test database.
     */
    public void deleteDatabase() {
        database.deleteTestDatabase();
    }
}
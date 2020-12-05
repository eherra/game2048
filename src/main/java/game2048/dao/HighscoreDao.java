package game2048.dao;

import java.sql.SQLException;
import java.util.List;

public interface HighscoreDao {
    void addScore(int value, int boardSize, String date) throws ClassNotFoundException, SQLException;
    
    List<String> getTopFiveScores(int boardSize) throws ClassNotFoundException, SQLException;
    
    int getTopScore(int boardSize) throws ClassNotFoundException, SQLException;
    int getFifthScore(int boardSize) throws ClassNotFoundException, SQLException;
}

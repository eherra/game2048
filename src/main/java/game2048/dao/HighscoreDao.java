package game2048.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for DBhighScoreDao class.
 */
public interface HighscoreDao {
    void addScore(int value, int boardSize, String date);
    List<String> getTopFiveScores(int boardSize);
    int getTopScore(int boardSize);
    int getFifthScore(int boardSize);
}

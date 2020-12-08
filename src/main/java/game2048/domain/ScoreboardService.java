
package game2048.domain;

import game2048.dao.DBhighScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle game points and highscore counting.
 */
public class ScoreboardService {
    private int highscore;
    private int currentScore; 
    private DBhighScoreDao dBhighScoreDao;
    
    public ScoreboardService(int boardSize, DBhighScoreDao db) {
        dBhighScoreDao = db;
        highscore = dBhighScoreDao.getTopScore(boardSize); 
    }
    
    /**
    * Adding points to current score.
    * If currentScore has reached to same level as high score, updates highscore at same time.
    * @param add value to add 2x to current score.
    */
    public void addCurrentPoints(int add) {
        currentScore += add * 2;
        if (currentScore >= highscore) {
            highscore = currentScore;
        }
    }
        
    public int getCurrentScore() {
        return currentScore;
    }
    
    public void addHighScore(int highscore, int boardSize, String date) {
        dBhighScoreDao.addScore(highscore, boardSize, date);
    }
    
    public List<String> getTopFiveScores(int boardSize) {
        return dBhighScoreDao.getTopFiveScores(boardSize);
    }
    
    public int getHighScore() {
        return highscore;
    }
    
    public void setToZeroCurrentScore() {
        currentScore = 0;
    }
    
    public void setHighScore(int high) {
        highscore = high;
    }
    
}

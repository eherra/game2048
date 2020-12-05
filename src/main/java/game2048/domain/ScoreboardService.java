
package game2048.domain;

import game2048.dao.DBhighScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardService {
    private int highscore;
    private int currentScore; 
    private DBhighScoreDao highScoreService;
    
    public ScoreboardService(int boardSize, DBhighScoreDao db) {
        highScoreService = db;
        highscore = highScoreService.getTopScore(boardSize); 
    }
        
    public int getCurrentScore() {
        return currentScore;
    }
    
    public void updateHighScore(int highscore, int boardSize, String date) {
        highScoreService.addScore(highscore, boardSize, date);
    }
    
    public List<String> getTopFiveScores(int boardSize) {
        return highScoreService.getTopFiveScores(boardSize);
    }
    
    public void addCurrentPoints(int add) {
        currentScore += add * 2;
        if (currentScore >= highscore) {
            highscore = currentScore;
        }
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

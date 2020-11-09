
package game2048.domain;

public class Scoreboard {
    private int highscore;
    private int currentScore; 
    
    public Scoreboard() {
        highscore = 0; // this needs to be fetch from database
    }
        
    public int getCurrentScore() {
        return currentScore;
    }
    
    public void addCurrentPoints(int add) {
        currentScore += add * 2;
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

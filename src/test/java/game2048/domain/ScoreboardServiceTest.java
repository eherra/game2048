
package game2048.domain;

import game2048.dao.DBhighScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreboardServiceTest {
    private ScoreboardService scoreB;
    private DBhighScoreDao testDb;
    
    
    @Before
    public void setUp() {
        testDb = new DBhighScoreDao(true);
        scoreB = new ScoreboardService(4, testDb);
    }
    
    @Test
    public void testAddCurrentPoints() throws SQLException {
        scoreB.addCurrentPoints(2);
        assertEquals(4, scoreB.getCurrentScore());
        scoreB.addCurrentPoints(4);
        assertEquals(12, scoreB.getCurrentScore());
        scoreB.addCurrentPoints(8);
        assertEquals(28, scoreB.getCurrentScore());
    }
    
    @After
    public void deleteDatabase() {
        testDb.deleteDatabase();
    }

    @Test
    public void testGetHighScore() {
        scoreB.setHighScore(1000);
        assertEquals(1000, scoreB.getHighScore());   
    }

    @Test
    public void testSetHighScore() {
        scoreB.setHighScore(9999);
        scoreB.setHighScore(1000000);
        scoreB.setHighScore(2);
        assertEquals(2, scoreB.getHighScore());   
    }

    @Test
    public void testAddHighScore() {
        scoreB.addHighScore(10000, 4, "");
        assertEquals(10000, testDb.getTopScore(4));   
        
        scoreB.addHighScore(99, 3, "");
        assertEquals(99, testDb.getTopScore(3));  
        
        scoreB.addHighScore(66, 5, "");
        assertEquals(66, testDb.getTopScore(5));  
    }

    @Test
    public void testGetTopFiveScores() {
        scoreB.addHighScore(10000, 4, "test");
        scoreB.addHighScore(9999, 4, "test");
        scoreB.addHighScore(9998, 4, "test");
        scoreB.addHighScore(9997, 4, "test");
        scoreB.addHighScore(9996, 4, "test");
        scoreB.addHighScore(12, 4, "test");
        
        List<String> testSet = scoreB.getTopFiveScores(4);
        assertEquals(5, testSet.size());
        
        int testCount = 10001;
        for (int i = 1; i <= testSet.size(); i++) {
            String testString = i + ". " + (testCount - i) + "p, test";
            assertEquals(testString, testSet.get(i-1));
        }
    }

}


package game2048.domain;

import game2048.dao.DBhighScoreDao;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreboardTest {
    private ScoreboardService scoreB;
    private DBhighScoreDao db;
    
    @Test
    public void testAddCurrentPoints() throws SQLException {
        db = new DBhighScoreDao();
        scoreB = new ScoreboardService(4, db);
        scoreB.addCurrentPoints(2);
        assertEquals(4, scoreB.getCurrentScore());
        scoreB.addCurrentPoints(4);
        assertEquals(12, scoreB.getCurrentScore());
        scoreB.addCurrentPoints(8);
        assertEquals(28, scoreB.getCurrentScore());
    }

    @Test
    public void testGetHighScore() {
    }

    @Test
    public void testSetHighScore() {
    }
    
}

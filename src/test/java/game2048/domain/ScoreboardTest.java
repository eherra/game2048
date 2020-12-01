
package game2048.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreboardTest {
    private Scoreboard scoreB;
    
    @Test
    public void testAddCurrentPoints() {
        scoreB = new Scoreboard();
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

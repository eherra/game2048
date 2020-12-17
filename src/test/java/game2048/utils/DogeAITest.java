
package game2048.utils;

import game2048.dao.DBhighScoreDao;
import game2048.domain.GameLogic;
import game2048.domain.MoveExecutor;
import javafx.scene.input.KeyCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DogeAITest {
    private GameLogic testLogic;
    private MoveExecutor testMoveController;
    private DBhighScoreDao testDb;
    private int n;
    private DogeAI dogeAItest;
    
    @Before
    public void setUp() {
        testDb = new DBhighScoreDao(true);
        testLogic = new GameLogic(4, testDb);
        testMoveController = new MoveExecutor(testLogic);
        n = testLogic.getTableSize();
        dogeAItest = new DogeAI(testMoveController);
    }
    
    
    @After
    public void deleteDatabase() {
        testDb.deleteDatabase();
    }
   
    @Test
    public void testGetBestMoveRight() {
        int[][] testSet = {{16, 0, 0, 0},
                            {8, 0, 0, 0},
                            {4, 0, 0, 0},
                            {2, 0, 0, 0}};
        
        testLogic.setTable(testSet);
        assertEquals(KeyCode.RIGHT, dogeAItest.getBestMove());
    }
    
    
    @Test
    public void testGetBestMoveLeft() {
        int[][] testSet = {{16, 0, 0, 0},
                            {8, 2, 8, 0},
                            {4, 0, 0, 0},
                            {0, 2, 0, 0}};
        
        testLogic.setTable(testSet);
        assertEquals(KeyCode.LEFT, dogeAItest.getBestMove());
    }
    
    
    @Test
    public void testGetBestMoveDown() {
        int[][] testSet = {{16, 8, 0, 0},
                            {8, 2, 0, 0},
                            {4, 0, 0, 0},
                            {0, 0, 0, 0}};
        
        testLogic.setTable(testSet);
        assertEquals(KeyCode.DOWN, dogeAItest.getBestMove());
    }
    
    
    @Test
    public void testGetBestMoveUp() {
        int[][] testSet = {{8, 0, 0, 0},
                            {8, 0, 0, 0},
                            {4, 8, 0, 0},
                            {2, 0, 0, 0}};
        
        testLogic.setTable(testSet);
        assertEquals(KeyCode.UP, dogeAItest.getBestMove());
    }
    
}


package game2048.domain;

import game2048.dao.DBhighScoreDao;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveExecutorTest {
    private GameLogic testLogic;
    private MoveExecutor testMoveController;
    private DBhighScoreDao testDb;
    private int n;

    
    @Before
    public void setUp() throws SQLException {
        testDb = new DBhighScoreDao(true);
        testLogic = new GameLogic(4, testDb);
        testMoveController = new MoveExecutor(testLogic);
        n = testLogic.getTableSize();
    }

    
    @After
    public void deleteDatabase() {
        testDb.deleteDatabase();
    }
    
    public int[][] getTableForMovingMethods() {
        int[][] k = {{2, 4, 4, 2},
                    {2, 4, 2, 2},
                    {2, 8, 2, 2},
                    {2, 8, 4, 4}};
        
        return k;
    }
    
    public int[][] getGameOverTable() {
        int[][] k = {{2, 4, 8, 16},
                    {32, 64, 128, 256},
                    {512, 1024, 2048, 2},
                    {2, 8, 4, 16}};
        
        return k;
    }

    @Test
    public void testMoveUp() {
        testLogic.setTable(getTableForMovingMethods());
        testMoveController.moveUp(false);
        int differences = 0;
        
        // the values which should be after a moveUp method.
        int[][] k = {{4, 8, 4, 4},
                    {4, 16, 4, 2},
                    {0, 0, 4, 4},
                    {0, 0, 0, 0}};
                
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!(testLogic.getValueFromBoard(i, j) == k[i][j])) {
                    differences++;
                }
            }
        }
        assertEquals(true, differences == 1); // difference should be one since 1 random square is added to table after every move
    }

    @Test
    public void testMoveDown() {
        testLogic.setTable(getTableForMovingMethods());
        testMoveController.moveDown(false);
        // the values which should be after a moveDown method.
        int[][] k = {{0, 0, 0, 0},
                    {0, 0, 4, 2},
                    {4, 8, 4, 4},
                    {4, 16, 4, 4}};
                
        int differences = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!(testLogic.getValueFromBoard(i, j) == k[i][j])) {
                    differences++;
                }
            }
        }
        assertEquals(true, differences == 1); 
    }

    @Test
    public void testMoveLeft() {
        testLogic.setTable(getTableForMovingMethods());
        testMoveController.moveLeft(false);
        int differences = 0;
        
        // the values which should be after a moveLeft method.
        int[][] k = {{2, 8, 2, 0},
                    {2, 4, 4, 0},
                    {2, 8, 4, 0},
                    {2, 8, 8, 0}};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!(testLogic.getValueFromBoard(i, j) == k[i][j])) {
                    differences++;
                }
            }
        }
        assertEquals(true, differences == 1);
    }
    
    @Test
    public void testMoveRight() {
        testLogic.setTable(getTableForMovingMethods());
        testMoveController.moveRight(false);
        int differences = 0;

        // the values which should be after a moveLeft method.
        int[][] k = {{0, 2, 8, 2},
                    {0, 2, 4, 4},
                    {0, 2, 8, 4},
                    {0, 2, 8, 8}};
                
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!(testLogic.getValueFromBoard(i, j) == k[i][j])) {
                    differences++;
                }
            }
        }
        assertEquals(true, differences == 1);
    }
 
    @Test
    public void testIsGameOver() {
        testLogic.setTable(getGameOverTable());
        assertEquals(true, testMoveController.isGameOver());
    }
    
}

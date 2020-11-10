/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2048.domain;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author balooza
 */
public class GameLogicTest {
    private GameLogic testLogic;
    
    public GameLogicTest() {
        
        
    }
    
    @Before
    public void setUp() {
        testLogic = new GameLogic(4);   // creating board size 4x4 with 2 values in random coordinates on int[][] array. 
                                        // Making testing a bit harder but is workaroundable
    }

    @Test
    public void testInitializeStartBoard() {
        int amountOfTwos = 0;
        int amountOfZeroes = 0;
        
        for (int i = 0; i < testLogic.getBoard().length; i++) {
            for (int j = 0; j < testLogic.getBoard().length; j++) {
                if (testLogic.getValueFromBoard(i, j) == 0) amountOfZeroes++;
                if (testLogic.getValueFromBoard(i, j) == 2) amountOfTwos++;
            }
        }
        
        int amountOfSquares = testLogic.getBoard().length * testLogic.getBoard().length;
        
        assertEquals(true, amountOfTwos == 2);
        assertEquals(true, amountOfZeroes == amountOfSquares - 2);
    }

    @Test
    public void testFindEmptyCoordinates() {
    }

    @Test
    public void testMoveUp() {
        
    }

    @Test
    public void testMoveDown() {
    }

    @Test
    public void testMoveLeft() {
    }

    @Test
    public void testMoveRight() {
    }

    @Test
    public void testUpdateBoard() {
    }

    @Test
    public void testAddRandomValue() {
    }
    
    @Test
    public void testGetBoard() {
    }


    @Test
    public void testGetValueFromBoard() {
    }

    @Test
    public void testGetRandomCoordinate() {
    }

    /**
     * Test of isGameOver method, of class GameLogic.
     */
    @Test
    public void testIsGameOver() {
    }

    /**
     * Test of getTableSize method, of class GameLogic.
     */
    @Test
    public void testGetTableSize() {
    }

    /**
     * Test of getHighScore method, of class GameLogic.
     */
    @Test
    public void testGetHighScore() {
    }

    /**
     * Test of getGamePoints method, of class GameLogic.
     */
    @Test
    public void testGetGamePoints() {
    }

    /**
     * Test of setNewGame method, of class GameLogic.
     */
    @Test
    public void testSetNewGame() {
    }
    
}

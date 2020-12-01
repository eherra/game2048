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
import org.junit.Ignore;

public class GameLogicTest {
    private GameLogic testLogic;
    private MoveExecutor testMoveController;
    private int n;

    @Before
    public void setUp() {
        testLogic = new GameLogic(4);   // creating board size 4x4 with 2 values in random coordinates on int[][] array. 
                                        // Making testing a bit harder but is workaroundable
        n = testLogic.getTableSize();
        testMoveController = new MoveExecutor(testLogic);
    }
    
    /**
    * Helper method to check if board is on starting settings (2 random placed '2' values).
    */
    public int[] checkingStartBoard() {
        int[] arrayToReturn = new int[2];
        
        int amountOfTwos = 0;
        int amountOfZeroes = 0;
        int tableSize = testLogic.getTableSize();

        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                if (testLogic.getValueFromBoard(i, j) == 0) amountOfZeroes++;
                if (testLogic.getValueFromBoard(i, j) == 2) amountOfTwos++;
            }
        }

        int amountOfSquares = tableSize * tableSize;
        arrayToReturn[0] = amountOfZeroes;
        arrayToReturn[1] = amountOfTwos;
        return arrayToReturn;
    }

    @Test
    public void testInitializeStartBoard() {
        int tableSize = testLogic.getTableSize();
        int amountOfSquares = tableSize * tableSize;
        
        int[] values = checkingStartBoard();
        assertEquals(true, values[1] == 2);
        assertEquals(true, values[0] == amountOfSquares - 2);
    }

    @Test
    public void testFindEmptyCoordinates() {
        
    }
 
    @Test
    public void testUpdateBoard() {
    }

    @Test
    public void testAddRandomValue() {
    }
    
    @Test
    public void testGetRandomCoordinate() {
    }

    @Test
    public void testSetNewGame() {
        for (int i = 0; i < 5; i++) {
            testMoveController.moveLeft(false);
            testMoveController.moveRight(false);
        }
        
        testLogic.setNewGame();
        
        int tableSize = testLogic.getTableSize();
        int amountOfSquares = tableSize * tableSize;
        
        int[] values = checkingStartBoard();
        assertEquals(true, values[1] == 2);
        assertEquals(true, values[0] == amountOfSquares - 2);
    }

    
}

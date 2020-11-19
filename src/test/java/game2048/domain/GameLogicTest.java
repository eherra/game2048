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
    private int n;

    @Before
    public void setUp() {
        testLogic = new GameLogic(4);   // creating board size 4x4 with 2 values in random coordinates on int[][] array. 
                                        // Making testing a bit harder but is workaroundable
        n = testLogic.getTableSize();
    }
    
    /**
    * Helper method to check if board is on starting settings (2 random placed '2' values).
    */
    public int[] checkingStartBoard() {
        int[] ret = new int[2];
        
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
        ret[0] = amountOfZeroes;
        ret[1] = amountOfTwos;
        return ret;
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

    /**
    * Creating a specific board setup and testing the moving methods for it in order to simplify the testing.
    */
    public int[][] getTableForMovingMethods() {
        int[][] k = {{2, 4, 4, 2},
                    {2, 4, 2, 2},
                    {2, 8, 2, 2},
                    {2, 8, 4, 4}};
        
        return k;
    }
    @Test
    public void testMoveUp() {
        testLogic.setTable(getTableForMovingMethods());
        testLogic.moveUp(false);
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
        assertEquals(true, differences == 1); // difference should be one since 1 random square is coming after every move
    }

    @Test
    public void testMoveDown() {
        testLogic.setTable(getTableForMovingMethods());
        testLogic.moveDown(false);
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
        testLogic.moveLeft(false);
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
        testLogic.moveRight(false);
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
    public void testUpdateBoard() {
    }

    @Test
    public void testAddRandomValue() {
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
    
    public void print() {
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                System.out.print(testLogic.getValueFromBoard(i, j) + " ");
            }
            System.out.println("");
        }
        
    }

    /**
     * Test of setNewGame method, of class GameLogic.
     */
    @Test
    public void testSetNewGame() {
        for (int i = 0; i < 5; i++) {
            testLogic.moveLeft(false);
            testLogic.moveRight(false);
        }
        
        testLogic.setNewGame();
        
        int tableSize = testLogic.getTableSize();
        int amountOfSquares = tableSize * tableSize;
        
        int[] values = checkingStartBoard();
        assertEquals(true, values[1] == 2);
        assertEquals(true, values[0] == amountOfSquares - 2);
    }

    
}

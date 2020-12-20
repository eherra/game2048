
package game2048.domain;

/**
 * A class to handle movements and updates to the game table to the GameLogic class. 
 * The code is a bit hard to read since the move methods have quite complex algorithms for checking the rows and cols for 
 * matching values and adding them if they are possible to add according the rules of game 2048.
 */
public class MoveExecutor {
    public boolean isMoveMade;
    public int lastChangeNum, lastChangeNumIndex, tableLength; 
    public GameLogic gameLogic;

    public MoveExecutor(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        tableLength = gameLogic.getTableSize();
    }
    
    /**
     * Method to makes up move to the game table array.
     * @param gameOverTest if method is used to check if there are any moves available on board. (gameover if not moves left).
     */
    public boolean moveUp(boolean gameOverTest) {
        isMoveMade = false;
        for (int y = 0; y < tableLength; y++) {
            // helpers for making changes for the board according the rules of game 2048.
            lastChangeNum = -1; // when starting to check the row, default as -1 since not values changed yet
            lastChangeNumIndex = -20; // no changes yet, so index to be smaller than the gameboard size
            for (int x = 1; x < tableLength; x++) {
                for (int lastX = x; lastX >= 1; lastX--) {
                    int currentValue = gameLogic.getValueFromBoard(lastX, y);
                    int valueToMoveTo = gameLogic.getValueFromBoard(lastX - 1, y);

                    // if current square's value is 0 (empty), it doesn't need to be moved.
                    if (currentValue == 0) {
                        continue;
                    }
                    
                    // returns true if method used for game over testing
                    if (makeChangesToBoardDownAndUpMoves(lastX, y, currentValue, valueToMoveTo, gameOverTest, true)) {
                        return true;
                    }
                }
            }
        }
        
        // if there has been a move => update the game view
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    /**
     * Method to makes down move to the game table array.
     * @param gameOverTest if method is used to check if there are any moves available on board. (gameover if not moves left).
     */
    public boolean moveDown(boolean gameOverTest) {
        isMoveMade = false;
        for (int y = 0; y < tableLength; y++) {
            lastChangeNum = -1; 
            lastChangeNumIndex = -20;
            for (int x = tableLength - 2; x >= 0; x--) {
                for (int lastX = x; lastX < tableLength - 1; lastX++) {
                    int currentValue = gameLogic.getValueFromBoard(lastX, y); 
                    int valueToMoveTo = gameLogic.getValueFromBoard(lastX + 1, y);
                    if (currentValue == 0) {
                        continue;
                    }
                    if (makeChangesToBoardDownAndUpMoves(lastX, y, currentValue, valueToMoveTo, gameOverTest, false)) {
                        return true;
                    }
                }
            }
        }
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    /**
     * Method to makes right move to the game table array.
     * @param gameOverTest if method is used to check if there are any moves available on board. (gameover if not moves left).
     */
    public boolean moveRight(boolean gameOverTest) {
        isMoveMade = false;
        for (int x = 0; x < tableLength; x++) {
            lastChangeNum = -1; 
            lastChangeNumIndex = -20;
            for (int y = tableLength - 2; y >= 0; y--) {
                for (int lastY = y; lastY < tableLength - 1; lastY++) {
                    int currentValue = gameLogic.getValueFromBoard(x, lastY); 
                    int valueToMoveTo = gameLogic.getValueFromBoard(x, lastY + 1);                    
                    if (currentValue == 0) {
                        continue;
                    }
                    if (makeChangesToBoardLeftAndRightMoves(x, lastY, currentValue, valueToMoveTo, gameOverTest, false)) {
                        return true;
                    }
                }
            }
        }
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    /**
     * Method to makes left move to the game table array.
     * @param gameOverTest if method is used to check if there are any moves available on board. (gameover if not moves left).
     */
    public boolean moveLeft(boolean gameOverTest) {
        isMoveMade = false;
        for (int x = 0; x < tableLength; x++) {
            lastChangeNum = -1;
            lastChangeNumIndex = -20;
            for (int y = 1; y < tableLength; y++) {
                for (int lastY = y; lastY > 0; lastY--) {
                    int currentValue = gameLogic.getValueFromBoard(x, lastY);
                    int valueToMoveTo = gameLogic.getValueFromBoard(x, lastY - 1);  
                    if (currentValue == 0) {
                        continue;
                    }
                    if (makeChangesToBoardLeftAndRightMoves(x, lastY, currentValue, valueToMoveTo, gameOverTest, true)) {
                        return true;
                    }
                }
            }
        }
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    /**
     * Helping method to up and down moves to update the board.
     * @return true if method is used for checking gameOverTest
     */
    public boolean makeChangesToBoardDownAndUpMoves(int lastX, int y, int currentValue, int positionToMoveValue, boolean gameOverTest, boolean isUpMove) {
        // 1 is upMove, 2 downMove
        int keycode = isUpMove ? 1 : 2;
        
        if (positionToMoveValue == 0) {
            if (gameOverTest) {
                return true;
            }     
            isMoveMade = true;
            updateBoardWithValue(keycode, true, lastX, y, currentValue);
        } else if (sameValuesAddingLegally(currentValue, positionToMoveValue) || 
                sameValuesAddingWithNoIncorrectIndexing(currentValue, positionToMoveValue, lastX, isUpMove)) { 
            if (gameOverTest) {
                return true;
            } 
            isMoveMade = true;
            lastChangeNum = currentValue;
            lastChangeNumIndex = lastX - 1;
            updateBoardWithValue(keycode, false, lastX, y, currentValue);
            gameLogic.addValuesToScoreboard(currentValue);
        }
        return false;
    }
    
    /**
     * Helping method to right and left moves to update the board.
     * @return true if method is used for checking gameOverTest
     */
    public boolean makeChangesToBoardLeftAndRightMoves(int x, int lastY, int currentValue, int positionToMoveValue, boolean gameOverTest, boolean isLeftMove) {
        // 3 is leftMove, 4 rightMove
        int keycode = isLeftMove ? 3 : 4;
        
        if (positionToMoveValue == 0) {
            if (gameOverTest) {
                return true;
            }     
            isMoveMade = true;
            updateBoardWithValue(keycode, true, x, lastY, currentValue);
        } else if (sameValuesAddingLegally(currentValue, positionToMoveValue) || 
                sameValuesAddingWithNoIncorrectIndexing(currentValue, positionToMoveValue, lastY, isLeftMove)) { 
            if (gameOverTest) {
                return true;
            } 
            isMoveMade = true;
            lastChangeNum = currentValue;
            lastChangeNumIndex = lastY - 1;
            updateBoardWithValue(keycode, false, x, lastY, currentValue);
            gameLogic.addValuesToScoreboard(currentValue);
        }
        return false;
    }
    
    public void updateBoardWithValue(int keycode, boolean normalAdding, int lastX, int lastY, int currentValue) {
        int valueToAddToBoard = normalAdding ? currentValue : currentValue * 2;
        
        switch (keycode) {
            case 1:
                gameLogic.setValueOnBoard(lastX - 1, lastY, valueToAddToBoard);
                break;
            case 2:
                gameLogic.setValueOnBoard(lastX + 1, lastY, valueToAddToBoard); 
                break;
            case 3:
                gameLogic.setValueOnBoard(lastX, lastY - 1, valueToAddToBoard);
                break;
            case 4:
                gameLogic.setValueOnBoard(lastX, lastY + 1, valueToAddToBoard);
                break;
        }
        // the spot where the value was orginally moved to one forward -> updates it to empty (0).
        gameLogic.setValueOnBoard(lastX, lastY, 0);        
    }
    
    /**
     * Checks if 2 values can be added according to 2048 rules.
     */
    public boolean sameValuesAddingLegally(int positionToMoveValue, int currentValue) {
        return positionToMoveValue == currentValue && currentValue != lastChangeNum * 2;
    }
    
    /**
     * Checks if 2 values can be added according to 2048 rules.
     * In a case where two values were added earlier on same checking line. (rules of 2048)
     */
    public boolean sameValuesAddingWithNoIncorrectIndexing(int currentValue, int positionToMoveValue, int toCheckIndex, boolean minusOrPlus) {
        int differenceInIndex = minusOrPlus ? toCheckIndex - lastChangeNumIndex : lastChangeNumIndex - toCheckIndex;
        return positionToMoveValue == currentValue && currentValue == lastChangeNum * 2 && differenceInIndex > 1;
    }
    
    /**
     * Checks if there any moves left on board.
     */
    public boolean isGameOver() {
        return !moveRight(true) && !moveUp(true) && !moveDown(true) && !moveLeft(true);
    }
}

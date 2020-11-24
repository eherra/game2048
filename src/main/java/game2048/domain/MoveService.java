
package game2048.domain;

/**
 * A class to handle movements and updates to the game table. 
 * The code is a bit hard to read since the move methods have quite complex algorithms for checking the rows and cols for 
 * matching values and adding them if yes they are possible to add according the rules of game 2048.
 */
public class MoveService {
    public boolean isMoveMade;
    public int lastChangeNum, lastChangeNumIndex, tableLength;
    public GameLogic gameLogic;

    public MoveService(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        tableLength = gameLogic.getTableSize();
    }
    
    public boolean moveUp(boolean gameOverTest) {
        isMoveMade = false;
        for (int y = 0; y < tableLength; y++) {
            lastChangeNum = -1;
            lastChangeNumIndex = -10;
            for (int x = 1; x < tableLength; x++) {
                for (int lastX = x; lastX >= 1; lastX--) {
                    int currentValue = gameLogic.getValueFromBoard(lastX, y);
                    int valueToMoveTo = gameLogic.getValueFromBoard(lastX - 1, y);

                    if (currentValue == 0) continue;
                    if (makeChangesToBoardDownAndUpMoves(lastX, y, currentValue, valueToMoveTo, gameOverTest, true)) return true;
                }
            }
        }
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    public boolean moveDown(boolean gameOverTest) {
        isMoveMade = false;
        for (int y = 0; y < tableLength; y++) {
            lastChangeNum = -1;
            lastChangeNumIndex = -10;
            for (int x = tableLength - 2; x >= 0; x--) {
                for (int lastX = x; lastX < tableLength - 1; lastX++) {
                    int currentValue = gameLogic.getValueFromBoard(lastX, y);
                    int valueToMoveTo = gameLogic.getValueFromBoard(lastX + 1, y);
                    
                    if (currentValue == 0) continue;
                    if (makeChangesToBoardDownAndUpMoves(lastX, y, currentValue, valueToMoveTo, gameOverTest, false)) return true;
                }
            }
        }
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    public boolean moveRight(boolean gameOverTest) {
        isMoveMade = false;
        for (int x = 0; x < tableLength; x++) {
            lastChangeNum = -1;
            lastChangeNumIndex = -10;
            for (int y = tableLength - 2; y >= 0; y--) {
                for (int lastY = y; lastY < tableLength - 1; lastY++) {
                    int currentValue = gameLogic.getValueFromBoard(x, lastY);
                    int valueToMoveTo = gameLogic.getValueFromBoard(x, lastY + 1);                    
                    if (currentValue == 0) continue;
                    if (makeChangesToBoardLeftAndRightMoves(x, lastY, currentValue, valueToMoveTo, gameOverTest, false)) return true;
                }
            }
        }
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    public boolean moveLeft(boolean gameOverTest) {
        isMoveMade = false;
        for (int x = 0; x < tableLength; x++) {
            lastChangeNum = -1;
            lastChangeNumIndex = -10;
            for (int y = 1; y < tableLength; y++) {
                for (int lastY = y; lastY > 0; lastY--) {
                    int currentValue = gameLogic.getValueFromBoard(x, lastY);
                    int valueToMoveTo = gameLogic.getValueFromBoard(x, lastY - 1);  
                    
                    if (currentValue == 0) continue;
                    if (makeChangesToBoardLeftAndRightMoves(x, lastY, currentValue, valueToMoveTo, gameOverTest, true)) return true;
                }
            }
        }
        if (isMoveMade) {
            gameLogic.updateBoard();
        }
        return false;
    }
    
    public boolean makeChangesToBoardDownAndUpMoves(int lastX, int y, int currentValue, int positionToMoveValue, boolean gameOverTest, boolean isUpMove) {
        if (positionToMoveValue == 0) {
            if (gameOverTest) {
                return true;
            }     
            isMoveMade = true;
            updateBoardFromMoveUpDown(isUpMove, true, lastX, y, currentValue);
        } else if (sameValuesAddingLegally(currentValue, positionToMoveValue) || 
                sameValuesAddingWithNoIncorrectMove(currentValue, positionToMoveValue, lastX, isUpMove)) { 
            if (gameOverTest) {
                return true;
            } 
            isMoveMade = true;
            lastChangeNum = currentValue;
            lastChangeNumIndex = lastX - 1;
            updateBoardFromMoveUpDown(isUpMove, false, lastX, y, currentValue);
            gameLogic.addValuesToScoreboard(currentValue);
        }
        return false;
    }
    
    public boolean makeChangesToBoardLeftAndRightMoves(int x, int lastY, int currentValue, int positionToMoveValue, boolean gameOverTest, boolean isLeftMove) {
        if (positionToMoveValue == 0) {
            if (gameOverTest) {
                return true;
            }     
            isMoveMade = true;
            updateBoardFromMoveLeftRight(isLeftMove, true, x, lastY, currentValue);
        } else if (sameValuesAddingLegally(currentValue, positionToMoveValue) || 
                sameValuesAddingWithNoIncorrectMove(currentValue, positionToMoveValue, lastY, isLeftMove)) { 
            if (gameOverTest) {
                return true;
            } 
            isMoveMade = true;
            lastChangeNum = currentValue;
            lastChangeNumIndex = lastY - 1;
            updateBoardFromMoveLeftRight(isLeftMove, false, x, lastY, currentValue);
            gameLogic.addValuesToScoreboard(currentValue);
        }
        return false;
    }
    
    public void updateBoardFromMoveUpDown(boolean isUp, boolean normalAdding, int lastX, int y, int currentValue) {
        if (isUp) {
            int toAdd = normalAdding ? currentValue : currentValue * 2;
            gameLogic.setValueOnBoard(lastX - 1, y, toAdd);
        } else {
            int toAdd = normalAdding ? currentValue : currentValue * 2;
            gameLogic.setValueOnBoard(lastX + 1, y, toAdd);        
        }
        gameLogic.setValueOnBoard(lastX, y, 0);        
    }
    
    public void updateBoardFromMoveLeftRight(boolean isLeft, boolean normalAdding, int x, int lastY, int currentValue) {
        if (isLeft) {
            int toAdd = normalAdding ? currentValue : currentValue * 2;
            gameLogic.setValueOnBoard(x, lastY - 1, toAdd);
        } else {
            int toAdd = normalAdding ? currentValue : currentValue * 2;
            gameLogic.setValueOnBoard(x, lastY + 1, toAdd);
        }
        gameLogic.setValueOnBoard(x, lastY, 0);        
    }
    
    public boolean sameValuesAddingLegally(int positionToMoveValue, int currentValue) {
        return positionToMoveValue == currentValue && currentValue != lastChangeNum * 2;
    }
    
    public boolean sameValuesAddingWithNoIncorrectMove(int currentValue, int positionToMoveValue, int toCheckIndex, boolean minusOrPlus) {
        int differenceInIndex = minusOrPlus ? toCheckIndex - lastChangeNumIndex : lastChangeNumIndex - toCheckIndex;
        return positionToMoveValue == currentValue && currentValue == lastChangeNum * 2 && differenceInIndex > 1;
    }
    
    public boolean isGameOver() {
        return !moveRight(true) && !moveUp(true) && !moveDown(true) && !moveLeft(true);
    }
}

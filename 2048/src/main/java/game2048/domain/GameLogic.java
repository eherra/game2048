
package game2048.domain;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private final int[][] gameTable;
    private final Random ran;
    private ArrayList<PairXY> emptySpots;
    private boolean isGameOver;
    private int tableLength, gamePoints;
    
    public GameLogic(int size) {
        gamePoints = 0;
        tableLength = size;
        isGameOver = false;
        gameTable = new int[size][size];
        ran = new Random();
        emptySpots = createEmptySpots();
        initializeStartBoard();
    }
    
    public void initializeStartBoard() {
        for (int i = 0; i < 2; i++) {
            PairXY k = getRandomCoordinates();
            gameTable[k.getX()][k.getY()] = 2;
        }
    }
    
    public ArrayList<PairXY> createEmptySpots() {
        ArrayList<PairXY> k = new ArrayList();
        for (int i = 0; i < tableLength; i++) {
            for (int j = 0; j < tableLength; j++) {
                if (gameTable[i][j] == 0) {
                    k.add(new PairXY(i,j));                
                }
            }
        }
        return k;
    }
    
    public boolean moveUp(boolean gameOverTest) {
        boolean isMoveMade = false;
        for (int y = 0; y < tableLength; y++) {
            int lastChangeNum = -1;
            for (int x = 1; x < tableLength; x++) {
                for (int lastX = x; lastX >= 1; lastX--) {
                    int current = gameTable[lastX][y];
                    int comp = gameTable[lastX-1][y];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        if (gameOverTest) return true;                           
                        gameTable[lastX-1][y] = current;
                        gameTable[lastX][y] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) { 
                        isMoveMade = true;
                        if (gameOverTest) return true; 
                        lastChangeNum = current;
                        gameTable[lastX-1][y] = comp * 2;
                        gameTable[lastX][y] = 0;
                        gamePoints += comp * 2;
                    }
                }
            }
        }
        if (isMoveMade) updateBoard();
        return false;
    }
    
    public boolean moveDown(boolean gameOverTest) {
        boolean isMoveMade = false;
        for (int y = 0; y < tableLength; y++) {
            int lastChangeNum = -1;
            for (int x = tableLength - 2; x >= 0; x--) {
                for (int lastX = x; lastX < tableLength - 1; lastX++) {
                    int current = gameTable[lastX][y];
                    int comp = gameTable[lastX+1][y];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        if (gameOverTest) return true;
                        gameTable[lastX+1][y] = current;
                        gameTable[lastX][y] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) {
                        isMoveMade = true;
                        if (gameOverTest) return true;                        
                        lastChangeNum = current;
                        gameTable[lastX+1][y] = comp * 2;
                        gameTable[lastX][y] = 0;
                        gamePoints += comp * 2;
                    }
                }
            }
        }
        if (isMoveMade) updateBoard();
        return false;
    }
    
    public boolean moveLeft(boolean gameOverTest) {
        boolean isMoveMade = false;
        for (int x = 0; x < tableLength; x++) {
            int lastChangeNum = -1;
            for (int y = 1; y < tableLength; y++) {
                for (int lastY = y; lastY > 0; lastY--) {
                    int current = gameTable[x][lastY];
                    int comp = gameTable[x][lastY-1];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        if (gameOverTest) return true;
                        gameTable[x][lastY-1] = current;
                        gameTable[x][lastY] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) {
                        isMoveMade = true;
                        if (gameOverTest) return true;
                        lastChangeNum = current;
                        gameTable[x][lastY-1] = comp * 2;
                        gameTable[x][lastY] = 0;
                        gamePoints += comp * 2;
                    }
                }
            }
        }
        if (isMoveMade) updateBoard();
        return false;
    }
    
    public boolean moveRight(boolean gameOverTest) {
        boolean isMoveMade = false;
        for (int x = 0; x < tableLength; x++) {
            int lastChangeNum = -1;
            for (int y = tableLength - 2; y >= 0; y--) {
                for (int lastY = y; lastY < tableLength - 1; lastY++) {
                    int current = gameTable[x][lastY];
                    int comp = gameTable[x][lastY+1];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        if (gameOverTest) return true;                        
                        gameTable[x][lastY+1] = current;
                        gameTable[x][lastY] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) {
                        isMoveMade = true;
                        if (gameOverTest) return true;                        
                        lastChangeNum = current;
                        gameTable[x][lastY+1] = comp * 2;
                        gameTable[x][lastY] = 0;
                        gamePoints += comp * 2;
                    }
                }
            }
        }
        if (isMoveMade) updateBoard();
        return false;
    }
    
    public void updateBoard() {
        emptySpots = createEmptySpots();
        addRandomValue();
    }

    public void addRandomValue() {
        PairXY k = getRandomCoordinates();
        if (Math.random() < 0.1) {
            gameTable[k.getX()][k.getY()] = 4;
        } else {
            gameTable[k.getX()][k.getY()] = 2;
        }
    }
    
    public int[][] getBoard() {
        return gameTable;
    }
    
    public int getValueFromBoard(int x, int y) {
        return gameTable[x][y];
    }
     
    public PairXY getRandomCoordinates() {
        int randomInd = ran.nextInt(emptySpots.size());
        PairXY ret = emptySpots.get(randomInd);
        emptySpots.remove(randomInd);
        return ret;
    }
    
    public boolean isGameOver() {
        return !(moveUp(true) || moveDown(true) || moveLeft(true) || moveRight(true));
    }
    
    public int getTableSize() {
        return tableLength;
    }
    
    public int getGamePoints() {
        return gamePoints;
    }
    
    public void print2DArray() {
        for (int i = 0; i < gameTable.length; i++) {
            for (int j = 0; j < gameTable.length; j++) {
                System.out.print(gameTable[i][j] + " ");
            }
            System.out.println("");
        }
    }
}


//    public int[] moveNumbers(int[] nums) {
//        int[] arrayToReturn = new int[nums.length];
//        for (int i = 1; i < nums.length; i++) {
//            int helpInd = i;
//            
//            while (helpInd != 0) {
//                int next = nums[helpInd-1];
//                int current = nums[helpInd];
//                System.out.println(next + " on next, " + current);
//                
//                if (next == 0 && current != 0) {
//                System.out.println(next + " on next, " + current +" eka if");
//                    arrayToReturn[helpInd-1] = current;
//                    arrayToReturn[helpInd] = 0;
//                } else if (next == current) {
//                System.out.println(next + " on next, " + current +" toka if");
//                    arrayToReturn[helpInd-1] = current * 2;
//                    arrayToReturn[helpInd] = 0;
//                    System.out.println(ArraysarrayToReturn);
//                }
//                helpInd--;
//            }
//        }
//        System.out.println(Arrays.toString((arrayToReturn)));
//        return arrayToReturn;
//    }
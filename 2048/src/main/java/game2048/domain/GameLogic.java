
package game2048.domain;

import game2048.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class GameLogic {
    private int[][] taulu;
    private ArrayList<PairXY> emptySpots;
    private Random ran;
    private boolean isGameOver;
    
    public GameLogic(int koko) {
        isGameOver = false;
        taulu = new int[koko][koko];
        ran = new Random();
        emptySpots = createEmptySpots();
        initializeStartBoard();
    }
    
    public void initializeStartBoard() {
        for (int i = 0; i < 2; i++) {
            PairXY k = getRandomCoordinates();
            taulu[k.getX()][k.getY()] = 2;
        }
    }
    
    public ArrayList<PairXY> createEmptySpots() {
        ArrayList<PairXY> k = new ArrayList();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (taulu[i][j] == 0) {
                    k.add(new PairXY(i,j));                
                }
            }
        }
        return k;
    }
    
    public void moveUp() {
        boolean isMoveMade = false;
        for (int y = 0; y < 4; y++) {
            int lastChangeNum = -1;
            for (int x = 1; x < 4; x++) {
                int apuInd = x; 
                while (apuInd != 0) {
                    int current = taulu[apuInd][y];
                    int comp = taulu[apuInd-1][y];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        taulu[apuInd-1][y] = current;
                        taulu[apuInd][y] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) { 
                        lastChangeNum = current;
                        isMoveMade = true;
                        taulu[apuInd-1][y] = comp * 2;
                        taulu[apuInd][y] = 0;
                    }
                    apuInd--;
                }
            }
        }
        if (isMoveMade) updateBoard();
        else if (!isMoveMade && emptySpots.size() == 0) isGameOver = true;
    }
    
    public void moveDown() {
        boolean isMoveMade = false;
        for (int y = 0; y < 4; y++) {
            int lastChangeNum = -1;
            for (int x = 2; x >= 0; x--) {
                int apuInd = x; 
                while (apuInd != 3) {
                    int current = taulu[apuInd][y];
                    int comp = taulu[apuInd+1][y];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        taulu[apuInd+1][y] = current;
                        taulu[apuInd][y] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) {
                        lastChangeNum = current;
                        isMoveMade = true;
                        taulu[apuInd+1][y] = comp * 2;
                        taulu[apuInd][y] = 0;
                    }
                    apuInd++;
                }
            }
        }
        if (isMoveMade) updateBoard();
        else if (!isMoveMade && emptySpots.size() == 0) isGameOver = true;

    }
    public void moveLeft() {
        boolean isMoveMade = false;
        for (int x = 0; x < 4; x++) {
            int lastChangeNum = -1;
            for (int y = 1; y < 4; y++) {
                int apuInd = y; 
                while (apuInd != 0) {
                    int current = taulu[x][apuInd];
                    int comp = taulu[x][apuInd-1];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        taulu[x][apuInd-1] = current;
                        taulu[x][apuInd] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) {
                        lastChangeNum = current;
                        isMoveMade = true;
                        taulu[x][apuInd-1] = comp * 2;
                        taulu[x][apuInd] = 0;
                    }
                    apuInd--;
                }
            }
        }
        if (isMoveMade) updateBoard();
        else if (!isMoveMade && emptySpots.size() == 0) isGameOver = true;

        
    }
    public void moveRight() {
        boolean isMoveMade = false;
        for (int x = 0; x < 4; x++) {
            int lastChangeNum = -1;
            for (int y = 2; y >= 0; y--) {
                int apuInd = y; 
                while (apuInd != 3) {
                    int current = taulu[x][apuInd];
                    int comp = taulu[x][apuInd+1];
                    if (comp == 0 && current != 0) {
                        isMoveMade = true;
                        taulu[x][apuInd+1] = current;
                        taulu[x][apuInd] = 0;
                    } else if (comp == current && current != 0  && current != lastChangeNum * 2) {
                        lastChangeNum = current;
                        isMoveMade = true;
                        taulu[x][apuInd+1] = comp * 2;
                        taulu[x][apuInd] = 0;
                    }
                    apuInd++;
                }
            }
        }
        if (isMoveMade) updateBoard();
        else if (!isMoveMade && emptySpots.size() == 0) isGameOver = true;
    }
    
    
    public void updateBoard() {
        emptySpots = createEmptySpots();
        addRandomValue();
    }

    public void addRandomValue() {
        PairXY k = getRandomCoordinates();
        
        if (Math.random() < 0.1) {
            taulu[k.getX()][k.getY()] = 4;
        } else {
            taulu[k.getX()][k.getY()] = 2;
        }
    }
    
    public int[][] getBoard() {
        return taulu;
    }
    
    public int getValueFromBoard(int x, int y) {
        return taulu[x][y];
    }
     
    public PairXY getRandomCoordinates() {
        int randomInd = ran.nextInt(emptySpots.size());
        PairXY ret = emptySpots.get(randomInd);
        emptySpots.remove(randomInd);
        return ret;
    }
    
    public boolean isGameOver() {
        return isGameOver;
    }
    
    public void print2DArray() {
        for (int i = 0; i < taulu.length; i++) {
            for (int j = 0; j < taulu.length; j++) {
                System.out.print(taulu[i][j] + " ");
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
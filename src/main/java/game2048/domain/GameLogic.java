
package game2048.domain;

import game2048.dao.DBhighScoreDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private int[][] gameTable;
    private final Random ran;
    private ArrayList<byte[]> emptyCoordinates;
    private int tableLength;
    private ScoreboardService scoreboard;
    public boolean isMoveMade;
    public int lastChangeNum, lastChangeNumIndex;
    
    public GameLogic(int size, DBhighScoreDao db) {
        scoreboard = new ScoreboardService(size, db);
        tableLength = size;
        gameTable = new int[size][size];
        ran = new Random();
        findEmptyCoordinates();
        initializeStartBoard();
    }
    
    public void initializeStartBoard() {
        for (int i = 0; i < 2; i++) {
            byte[] coordinatesXY = getRandomCoordinate();
            emptyCoordinates.remove(coordinatesXY);
            gameTable[coordinatesXY[0]][coordinatesXY[1]] = 2;
        }
    }
    
    public void updateBoard() {
        findEmptyCoordinates();
        addRandomSquare();
    }
    
    public void findEmptyCoordinates() {
        emptyCoordinates = new ArrayList();
        for (int x = 0; x < tableLength; x++) {
            for (int y = 0; y < tableLength; y++) {
                if (gameTable[x][y] == 0) {
                    byte[] coordinates = {(byte) x, (byte) y};
                    emptyCoordinates.add(coordinates);   
                }
            }
        }
    }

    public void addRandomSquare() {
        byte[] coordinate = getRandomCoordinate();
        gameTable[coordinate[0]][coordinate[1]] = Math.random() <= 0.1 ? 4 : 2;
    }
    
    public void setNewGame() {
        scoreboard.setToZeroCurrentScore();
        gameTable = new int[tableLength][tableLength];
        findEmptyCoordinates();
        initializeStartBoard();
    }
    
    public int[][] getTable() {
        return gameTable;
    }
    
    public void setTable(int[][] k) {
        gameTable = k;
    }
    
    public int getValueFromBoard(int x, int y) {
        return gameTable[x][y];
    }
    
    public void setValueOnBoard(int x, int y, int value) {
        gameTable[x][y] = value;
    }
     
    public byte[] getRandomCoordinate() {
        return emptyCoordinates.get(ran.nextInt(emptyCoordinates.size()));
    }
    
    public boolean isMoveableSquares() {
        return emptyCoordinates.size() != 1;
    }
    
    public int getTableSize() {
        return tableLength;
    }
    
    public int getHighScore() {
        return scoreboard.getHighScore();
    }
    
    public int getGamePoints() {
        return scoreboard.getCurrentScore();
    }
    
    public void addValuesToScoreboard(int current) {
        scoreboard.addCurrentPoints(current);
    }
    
    public List<String> getTopFive(int boardSize) {
        return scoreboard.getTopFiveScores(boardSize);
    }
    
    public void updateHighScore(int highscore, int boardSize) {
        String dateAsString = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        scoreboard.updateHighScore(highscore, boardSize, dateAsString);
    }
}

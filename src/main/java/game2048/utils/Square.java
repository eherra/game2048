
package game2048.utils;

import game2048.ui.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Class to make correctly styled and valued squares to the game board.
 */
public class Square {
    private StackPane squareStack;
    private int value;
    
    public Square(int value) {
        squareStack = new StackPane();
        this.value = value;
    }
    
    /**
     * Creates a stackpane which consists color, shape and value.
     * @param tableSize square size depends on tableSize parameter.
     */
    public StackPane getSquareStack(int tableSize) {
        int squareSize = getSquareSizeOrFont(tableSize, false);
        Rectangle square = new Rectangle(squareSize, squareSize, squareSize, squareSize);
        square.setArcWidth(15);
        square.setArcHeight(15);
        square.setFill(Color.web(getSquareColour(value)));
        
        int fontSize = getSquareSizeOrFont(tableSize, true);
        Label fontLabel = value != 0 ? new Label(String.valueOf(value)) : new Label();
        fontLabel.setFont(new Font("Sans-Serif", fontSize));
        
        squareStack = new StackPane(square, fontLabel);
        return squareStack;
    }
    
    /**
     * To determine correct size or font for square.
     * @param size gametable size
     * @param isFont is true if used to determine font size of square
     */
    public int getSquareSizeOrFont(int size, boolean isFont) {
        switch (size) {
            case 3:
                return isFont ? 30 : 120;
            case 4:
                return isFont ? 26 : 100;
            case 5: 
                return isFont ? 25 : 95;
            case 6:
                return isFont ? 23 : 90;
            case 7:
                return isFont ? 23 : 85;
            case 8:
                return isFont ? 22 : 75;
        }
        return isFont ? 21 : 70;
    }
    
    /**
     * To determine correct color for square.
     */
    public String getSquareColour(int size) {
        switch (size) {
            case 0:
                return "#cdc0b4";
            case 2:
                return "#eee4da";
            case 4: 
                return "#b2d8b2";
            case 8:
                return "#f3b27a";
            case 16:
                return "#f29663";
            case 32:
                return "#ff7f7f";
            case 64:
                return "#ee5d39";
            case 128:
                return "#F9E79F";
            case 256:
                return "#F7DC6F";
            case 512:
                return "#F1C40F";
            case 1024:
                return "#B7950B";
        }
        return "7D3C98"; // colour of 2048 square
    }
}

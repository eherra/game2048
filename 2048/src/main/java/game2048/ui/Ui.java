
package game2048.ui;

import game2048.domain.GameLogic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Ui extends Application {
    private GameLogic logic;
    private GridPane gridForSquares;
    private StackPane squareStack, gameOverStack;
    private BorderPane rootSetting;
    private BorderPane mainTop;
    private VBox mainTopRight;
    private Label currentScoreLabel, highScoreLabel;
    private Button newGameButton;
    private double sceneHeigth, sceneWidth;
   
    public Ui() {
        logic = new GameLogic(4);
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameOverStack = new StackPane();
        rootSetting = new BorderPane();
        gridForSquares = getUpdatedAndStyledPane();
        mainTop = new BorderPane();
        squareStack = new StackPane(mainTop);
        mainTopRight = new VBox();
        HBox scoreShow = new HBox();
        
        newGameButton = getNewGameButton();
        newGameButton.setFocusTraversable(false);
        Label leftTopLabel = new Label("Game 2048");
        leftTopLabel.setUnderline(true);
        leftTopLabel.setPadding(new Insets(20, 20, 20, 20));
        leftTopLabel.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 30));
        highScoreLabel = new Label("High Score \n " + logic.getHighScore());
        highScoreLabel.setFont(new Font("Sans-Serif", 15));
        highScoreLabel.setTextFill(Color.web("#ffffff"));
        
        currentScoreLabel = new Label("Current Score \n " + logic.getGamePoints());
        currentScoreLabel.setFont(new Font("Sans-Serif", 15));
        currentScoreLabel.setTextFill(Color.web("#ffffff"));

        scoreShow.getChildren().addAll(currentScoreLabel, highScoreLabel);
        scoreShow.setSpacing(10);
        mainTopRight.getChildren().addAll(scoreShow, newGameButton);
        mainTop.setRight(mainTopRight);
        mainTop.setLeft(leftTopLabel);
        
        squareStack.setStyle("-fx-background-color:#bbada0");
        rootSetting.setStyle("-fx-background-color:#008080");
        squareStack.getChildren().add(gridForSquares);
        rootSetting.setTop(mainTop);
        rootSetting.setCenter(squareStack);

        Scene skene = new Scene(rootSetting);
        
        skene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.UP) {
                logic.moveUp(false);
            } else if (event.getCode() == KeyCode.DOWN) {
                logic.moveDown(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                logic.moveRight(false);
            } else if (event.getCode() == KeyCode.LEFT) {
                logic.moveLeft(false);
            } else {
                return;
            }
            gridForSquares = getUpdatedAndStyledPane();
            currentScoreLabel.setText("Current Score \n " + logic.getGamePoints());
            highScoreLabel.setText("High Score \n " + logic.getHighScore()); // metodin hakemaan highScoren
            squareStack.getChildren().add(gridForSquares);
            
            if (logic.isGameOver()) {
                gameOverStack = getGameOverStack();
                squareStack.getChildren().add((gameOverStack));
            }
        });      
        
        stage.setScene(skene);
        stage.show();
        sceneHeigth = skene.getHeight();
        sceneWidth = skene.getWidth();
    }
    
    public StackPane getGameOverStack() {
        StackPane gameOverStack = new StackPane();
        Rectangle square = new Rectangle(sceneHeigth - 100, sceneWidth -200, sceneHeigth - 100, sceneWidth - 200);
        Label gameOverLabel = new Label("You lost!");
        square.setFill(Color.web("#2F4F4F"));
        gameOverLabel.setFont(new Font("Sans-Serif", 30));
        gameOverLabel.setTextFill(Color.web("#FFFFFF", 0.9));
        square.setArcWidth(15);
        square.setArcHeight(15);

        VBox k = new VBox();
        k.getChildren().addAll(gameOverLabel, getNewGameButton());
        k.setSpacing(20);
        gameOverStack.getChildren().addAll(square, k);
        k.setAlignment(Pos.CENTER);
        return gameOverStack;
    }
    
    public GridPane getUpdatedAndStyledPane() {
        GridPane toReturnPane = setSquares();
        toReturnPane.setPadding(new Insets(5, 5, 5, 5));
        toReturnPane.setHgap(10);
        toReturnPane.setVgap(10);
        return toReturnPane;
    }
    
    public GridPane setSquares() {
        GridPane gridToReturn = new GridPane();
        for (int i = 0; i < logic.getTableSize(); i++) {
            for (int j = 0; j < logic.getTableSize(); j++) {
                StackPane squareStack = getSquareStack(logic.getValueFromBoard(i, j));
                gridToReturn.add(squareStack, j, i);
            }
        }
        return gridToReturn;
    }
    
    public Button getNewGameButton() {
        Button newGameButton = new Button("New game");
        newGameButton.setFont(new Font("Sans-Serif", 15));
        newGameButton.setStyle("-fx-background-color: #679fd3; ");
        newGameButton.setOnMouseEntered(e -> newGameButton.setStyle("-fx-background-color: #aacef7"));
        newGameButton.setOnMouseExited(e -> newGameButton.setStyle("-fx-background-color: #679fd3"));

        newGameButton.setOnMouseClicked((event) -> {
            logic.setNewGame();
            gridForSquares = getUpdatedAndStyledPane();
            squareStack.getChildren().add(gridForSquares);
            currentScoreLabel.setText("Current Score \n " + logic.getGamePoints());
            gameOverStack.setVisible(false);
        });
        return newGameButton;
    }
    
    public StackPane getSquareStack(int size) {
        Rectangle square = new Rectangle(100,100,100,100);
        StackPane stackToReturn = new StackPane();
        square.setArcWidth(15);
        square.setArcHeight(15);
        square.setFill(Color.web(getRectangleColour(size)));
        Label fontLabel = size != 0 ? new Label(String.valueOf(size)) : new Label();
        fontLabel.setFont(new Font("Sans-Serif", 30));
        stackToReturn.getChildren().addAll(square, fontLabel);
        return stackToReturn;
    }
    
    public String getRectangleColour(int size) {
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
                return "#b25858";
            case 128:
                return "#F9E79F";
            case 256:
                return "#F7DC6F";
            case 512:
                return "#F1C40F";
            case 1024:
                return "#B7950B";
            case 2048:
                return "#7D3C98";
        }
        return "";
    }
   
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

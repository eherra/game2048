
package game2048.ui;

import game2048.domain.GameLogic;
import game2048.domain.MoveService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Ui extends Application {
    private GameLogic logic;
    private MoveService moveService;
    private GridPane gridForSquares, gridToReturn, toReturnPane;
    private StackPane squareStack, gameOverStack, stackToReturn;
    private BorderPane rootSetting, mainTop;
    private VBox mainTopRight;
    private Label currentScoreLabel, highScoreLabel, fontLabel;
    private Button topNewGameButton, topMainMenuButton;
    private Rectangle square;
    private double sceneHeigth, sceneWidth;
    
    @Override
    public void init() {
        logic = new GameLogic(4);
        moveService = new MoveService(logic);
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameOverStack = new StackPane();
        
        // stack for moving squares
        squareStack = new StackPane();
        squareStack.setStyle("-fx-background-color:#bbada0");

        // root for game view
        rootSetting = new BorderPane();
        rootSetting.setStyle("-fx-background-color:#008080");
        
        // top right new game button
        topNewGameButton = getNewGameButton();
        topNewGameButton.setFocusTraversable(false);

        //back to menu button
        topMainMenuButton = getTopMenuButton();
        topMainMenuButton.setFocusTraversable(false);

        // top labels
        Label leftTopLabel = new Label("Game 2048");
        currentScoreLabel = new Label("Current Score \n" + logic.getGamePoints());
        highScoreLabel = new Label("High Score \n" + logic.getHighScore());
        
        // top labels styling
        leftTopLabel.setUnderline(true);
        leftTopLabel.setPadding(new Insets(20, 20, 20, 20));
        leftTopLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 30));
        highScoreLabel.setFont(new Font("Sans-Serif", 15));
        highScoreLabel.setTextFill(Color.web("#ffffff"));
        currentScoreLabel.setFont(new Font("Sans-Serif", 15));
        currentScoreLabel.setTextFill(Color.web("#ffffff"));
        
        // top right scoreshow
        HBox scoreShow = new HBox();
        HBox buttonShow = new HBox();
        
        // right top
        scoreShow.getChildren().addAll(currentScoreLabel, highScoreLabel);
        scoreShow.setSpacing(10);
        buttonShow.getChildren().addAll(topNewGameButton, topMainMenuButton);
        buttonShow.setSpacing(5);

        // top setting
        mainTop = new BorderPane();
        mainTopRight = new VBox();

        mainTopRight.getChildren().addAll(scoreShow, buttonShow);
        mainTop.setRight(mainTopRight);
        mainTop.setLeft(leftTopLabel);
        
        gridForSquares = getUpdatedAndStyledPane();
        
        squareStack.getChildren().add(gridForSquares);
        rootSetting.setTop(mainTop);
        rootSetting.setCenter(squareStack);

        Scene skene = new Scene(rootSetting);
        
        skene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.UP) {
                moveService.moveUp(false);
            } else if (event.getCode() == KeyCode.DOWN) {
                moveService.moveDown(false);
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveService.moveRight(false);
            } else if (event.getCode() == KeyCode.LEFT) {
                moveService.moveLeft(false);
            } else {
                return;
            }
            gridForSquares = getUpdatedAndStyledPane();
            currentScoreLabel.setText("Current Score \n" + logic.getGamePoints());
            highScoreLabel.setText("High Score \n" + logic.getHighScore()); // metodin hakemaan highScoren
            squareStack.getChildren().add(gridForSquares);
            
            if (!logic.isMoveableSquares() && moveService.isGameOver()) {
                topNewGameButton.setDisable(true);
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
        
        Label gameOverLabel = new Label("You lost!");
        gameOverLabel.setFont(new Font("Sans-Serif", 30));
        gameOverLabel.setTextFill(Color.web("#FFFFFF", 0.9));
        
        Label endScore = new Label("Final score: " + logic.getGamePoints());
        endScore.setFont(new Font("Sans-Serif", 25));
        endScore.setTextFill(Color.web("#FFFFFF", 0.9));
        
        Rectangle square = new Rectangle(sceneHeigth - 100, sceneWidth - 150, sceneHeigth - 100, sceneWidth - 150);
        square.setFill(Color.web("#2F4F4F"));
        square.setArcWidth(15);
        square.setArcHeight(15);
        
        // opacity button
        Button opacityForStackButton = new Button("hold for boardview");
        opacityForStackButton.setFont(new Font("Sans-Serif", 10));
        opacityForStackButton.setStyle("-fx-background-color: #F9E79F; ");
        opacityForStackButton.setOnMousePressed((event) -> {gameOverStack.setOpacity(0.3);});
        opacityForStackButton.setOnMouseReleased((event) -> {gameOverStack.setOpacity(1);});
        opacityForStackButton.setOnMouseEntered(e -> opacityForStackButton.setStyle("-fx-background-color: #FEF9E7"));
        opacityForStackButton.setOnMouseExited(e -> opacityForStackButton.setStyle("-fx-background-color: #F9E79F"));
        
        //menu button, not finished
        Button highScoresButton = new Button("High score");
        highScoresButton.setFont(new Font("Sans-Serif", 15));
        highScoresButton.setStyle("-fx-background-color: #679fd3; ");        
        highScoresButton.setOnMouseEntered(e -> highScoresButton.setStyle("-fx-background-color: #aacef7"));
        highScoresButton.setOnMouseExited(e -> highScoresButton.setStyle("-fx-background-color: #679fd3"));
        
        VBox rows = new VBox();
        HBox col = new HBox();
        col.getChildren().addAll(getNewGameButton(), highScoresButton);
        col.setSpacing(20);
        col.setAlignment(Pos.CENTER);
        rows.getChildren().addAll(opacityForStackButton, gameOverLabel, endScore, col);
        rows.setSpacing(20);
        rows.setAlignment(Pos.CENTER);
        gameOverStack.getChildren().addAll(square, rows);

        return gameOverStack;
    }
    
    public GridPane getUpdatedAndStyledPane() {
        toReturnPane = setSquares();
        toReturnPane.setPadding(new Insets(5, 5, 5, 5));
        toReturnPane.setHgap(10);
        toReturnPane.setVgap(10);
        return toReturnPane;
    }
    
    public GridPane setSquares() {
        gridToReturn = new GridPane();
        StackPane squareStack = new StackPane();
        for (int i = 0; i < logic.getTableSize(); i++) {
            for (int j = 0; j < logic.getTableSize(); j++) {
                squareStack = getSquareStack(logic.getValueFromBoard(i, j));
                gridToReturn.add(squareStack, j, i);
            }
        }
        return gridToReturn;
    }
       
    public StackPane getSquareStack(int size) {
        square = new Rectangle(100,100,100,100);
        stackToReturn = new StackPane();
        square.setArcWidth(15);
        square.setArcHeight(15);
        square.setFill(Color.web(getSquareColour(size)));
        fontLabel = size != 0 ? new Label(String.valueOf(size)) : new Label();
        fontLabel.setFont(new Font("Sans-Serif", 30));
        stackToReturn.getChildren().addAll(square, fontLabel);
        return stackToReturn;
    }
    
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
       
    public Button getNewGameButton() {
        Button newGameButton = new Button("New game");
        newGameButton.setFont(new Font("Sans-Serif", 15));
        newGameButton.setStyle("-fx-background-color: #679fd3; ");
        newGameButton.setOnMouseEntered(e -> newGameButton.setStyle("-fx-background-color: #aacef7"));
        newGameButton.setOnMouseExited(e -> newGameButton.setStyle("-fx-background-color: #679fd3"));

        newGameButton.setOnMouseClicked((event) -> {
            gameOverStack.setVisible(false);
            topNewGameButton.setDisable(false);
            logic.setNewGame();
            gridForSquares = getUpdatedAndStyledPane();
            squareStack.getChildren().add(gridForSquares);
            currentScoreLabel.setText("Current Score \n" + logic.getGamePoints());
        });
        return newGameButton;
    }
    
    public Button getTopMenuButton() {
        Button topMainMenuButton = new Button("Back");
        topMainMenuButton.setFont(new Font("Sans-Serif", 15));
        topMainMenuButton.setStyle("-fx-background-color: #CD5C5C; ");
        topMainMenuButton.setOnMouseEntered(e -> topMainMenuButton.setStyle("-fx-background-color: #F08080"));
        topMainMenuButton.setOnMouseExited(e -> topMainMenuButton.setStyle("-fx-background-color: #CD5C5C"));
        topMainMenuButton.setFocusTraversable(false);
        
        // set on mouse clicked, change skene to main menu
        
        return topMainMenuButton;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

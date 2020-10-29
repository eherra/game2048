
package game2048.ui;

import game2048.domain.GameLogic;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Ui extends Application {
    private GameLogic logic;
    private GridPane pane;
    private StackPane stakki;
   
    public Ui() {
        logic = new GameLogic(4);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stakki = new StackPane();
        pane = setSquares();
        stakki.setStyle("-fx-background-color:#bbada0");
        styleGrid();

        Scene skene = new Scene(stakki);
        stakki.getChildren().add(pane);

        skene.setOnKeyPressed((KeyEvent event) -> {
            stakki.getChildren().remove(pane);
            if (event.getCode() == KeyCode.UP) {
                logic.moveUp(false);
                pane = setSquares();
            } else if (event.getCode() == KeyCode.DOWN) {
                logic.moveDown(false);
                pane = setSquares();
            } else if (event.getCode() == KeyCode.RIGHT) {
                logic.moveRight(false);
                pane = setSquares();
            } else if (event.getCode() == KeyCode.LEFT) {
                logic.moveLeft(false);
                pane = setSquares();
            }
            styleGrid();
            stakki.getChildren().add(pane);
            if (logic.isGameOver()) {
                System.out.println("PELILOPPU"); // tähän takin päälle uusipeli? ja peliloppu teksti . Opacity stakki 
            }
        });        

        stage.setScene(skene);
        stage.show();
    }
    
    public void styleGrid() {
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(10);
        pane.setVgap(10);
    }
    
    public GridPane setSquares() {
        GridPane pane = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                StackPane s = getRectangle(logic.getValueFromBoard(i, j));
                pane.add(s, j, i);
            }
        }
        return pane;
    }
    
    public StackPane getRectangle(int size) {
        Rectangle rec = new Rectangle(100,100,100,100);
        StackPane pane = new StackPane();
        rec.setArcWidth(15);
        rec.setArcHeight(15);
        Label fontLabel = size != 0 ? new Label(String.valueOf(size)) : new Label();
        fontLabel.setFont(new Font("Arial", 30));
        switch (size) {
            case 0:
                rec.setFill(Color.web("#cdc0b4"));
                break;
            case 2:
                rec.setFill(Color.web("#eee4da"));
                break;
            case 4: 
                rec.setFill(Color.web("#b2d8b2"));
                break;
            case 8:
                rec.setFill(Color.web("#f3b27a"));
                break;
            case 16:
                rec.setFill(Color.web("#f29663"));
                break;
            case 32:
                rec.setFill(Color.web("#ff7f7f"));
                break;
            case 64:
                rec.setFill(Color.web("#b25858"));
                break;
            case 128:
                rec.setFill(Color.web("#F9E79F"));
                break;
            case 256:
                rec.setFill(Color.web("#F7DC6F"));
                break;
            case 512:
                rec.setFill(Color.web("#F1C40F"));
                break;           
            case 1024:
                rec.setFill(Color.web("#B7950B"));
                break;
            case 2048:
                rec.setFill(Color.web("#7D3C98"));
                break;
        }
        pane.getChildren().addAll(rec, fontLabel);
        return pane;
    }
   
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

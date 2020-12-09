
package game2048.utils;

import game2048.domain.MoveExecutor;
import javafx.scene.input.KeyCode;

public class DogeAI {
    private MoveExecutor moveExetur;
    
    public DogeAI(MoveExecutor moveExecutor) {
        moveExetur = moveExecutor;
    }
    
    public KeyCode getBestMove() {
        if (moveExetur.moveLeft(true)) {
            return KeyCode.LEFT;
        } else if (moveExetur.moveUp(true)) {
            return KeyCode.UP;
        } else if (moveExetur.moveDown(true)) {
            return KeyCode.DOWN;
        } else if (moveExetur.moveRight(true)) {
            return KeyCode.RIGHT;
        }
        return null;
    }
}

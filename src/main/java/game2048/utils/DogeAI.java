
package game2048.utils;

import game2048.domain.MoveExecutor;
import javafx.scene.input.KeyCode;

/**
 * Class to calculate the moves to Doge AI on Doge AI mode. 
 */
public class DogeAI {
    private MoveExecutor moveExetur;
    
    public DogeAI(MoveExecutor moveExecutor) {
        moveExetur = moveExecutor;
    }
    
    /**
     * To determine the best move for doge AI at the current situation of the game.
     */
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

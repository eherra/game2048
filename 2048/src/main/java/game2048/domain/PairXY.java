
package game2048.domain;

import game2048.*;


public class PairXY {
    private int x, y;
    
    public PairXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String toString() {
        return x + ", " + y;
    }
}

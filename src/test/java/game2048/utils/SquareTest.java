
package game2048.utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {
    private Square square;

    @Before
    public void setUp() {
        square = new Square(4);
    }

    @Test
    public void testGetSquareSizeOrFont() {
        assertEquals(30, square.getSquareSizeOrFont(3, true));
        assertEquals(26, square.getSquareSizeOrFont(4, true));
        assertEquals(25, square.getSquareSizeOrFont(5, true));
        assertEquals(23, square.getSquareSizeOrFont(6, true));
        assertEquals(23, square.getSquareSizeOrFont(7, true));
        assertEquals(22, square.getSquareSizeOrFont(8, true));
        assertEquals(21, square.getSquareSizeOrFont(9, true));
        
        assertEquals(120, square.getSquareSizeOrFont(3, false));
        assertEquals(100, square.getSquareSizeOrFont(4, false));
        assertEquals(95, square.getSquareSizeOrFont(5, false));
        assertEquals(90, square.getSquareSizeOrFont(6, false));
        assertEquals(85, square.getSquareSizeOrFont(7, false));
        assertEquals(75, square.getSquareSizeOrFont(8, false));
        assertEquals(70, square.getSquareSizeOrFont(9, false));
    }

    @Test
    public void testGetSquareColour() {
        assertEquals("#cdc0b4", square.getSquareColour(0));
        assertEquals("#eee4da", square.getSquareColour(2));
        assertEquals("#b2d8b2", square.getSquareColour(4));
        assertEquals("#f3b27a", square.getSquareColour(8));
        assertEquals("#f29663", square.getSquareColour(16));
        assertEquals("#ff7f7f", square.getSquareColour(32));
        assertEquals("#ee5d39", square.getSquareColour(64));
        assertEquals("#F9E79F", square.getSquareColour(128));
        assertEquals("#F7DC6F", square.getSquareColour(256));
        assertEquals("#F1C40F", square.getSquareColour(512));
        assertEquals("#B7950B", square.getSquareColour(1024));
        assertEquals("7D3C98", square.getSquareColour(2048));
    }
}

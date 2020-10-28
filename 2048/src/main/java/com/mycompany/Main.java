
package com.mycompany;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        GameLogic p = new GameLogic(4);
        Scanner luk = new Scanner(System.in);
        p.print2DArray();

        while (true) {
            String komento = luk.nextLine();
            if (komento.equals("V")) {
                p.moveLeft();
                p.print2DArray();
            } else if (komento.equals("O")) {
                p.moveRight();
                p.print2DArray();
            } else if (komento.equals("Y")) {
                p.moveUp();
                p.print2DArray();
            } else if (komento.equals("A")) {
                p.moveDown();
                p.print2DArray();
            }
        }
    }
    
}

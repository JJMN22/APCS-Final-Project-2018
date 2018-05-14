package FinalProject.EventHandling;

import FinalProject.GameBoard.Map;
import FinalProject.Squares.*;

import java.awt.*;

public class Auxilliary {
    public static int iteration = 1;
    //updates Map's buttons
    public static void updateButtons(Map map) {
        map.removeAll();
        int x=0;
        int y=0;

        for (int i = 0; i < Map.SIZE; i++) {
            for (int j = 0; j < Map.SIZE; j++){
                Map.gameMap[i][j].setBackground(null);
                map.add(Map.gameMap[i][j]);
                Map.gameMap[i][j].setFocusable(false);
            }
        }
        if (Map.selected!=null){
            Map.selected.setOpaque(true);
            Map.selected.setBackground(Color.RED);
        }
        map.validate();
        map.repaint();
        iteration++;
    }
    //Destroys squares, returns the new square in its place
    public static Square destroy(Square sq){
        Point pos = sq.getPosition();
        int xPos = (int) pos.getX();
        int yPos = (int) pos.getY();
        Map.gameMap[xPos][yPos] = new Grass(null, xPos, yPos);
        return Map.gameMap[xPos][yPos];
    }
}

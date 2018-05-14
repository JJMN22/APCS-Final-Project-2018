package FinalProject.EventHandling;
import FinalProject.GameBoard.*;
import FinalProject.Squares.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//working on getting troop to stay a troop after moving
public class ClickAction extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e){
        Square sq = (Square) e.getSource();
        if (SwingUtilities.isRightMouseButton(e))
        {
            System.out.println("my name jefff");
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            //selects a tile
            if (Map.selected == null) {
                int xPos = (int) sq.getPosition().getX();
                int yPos = (int) sq.getPosition().getY();
                Map.selected = Map.gameMap[xPos][yPos];
            } else {
                //Deselects if Square is selected
                if (Map.selected == sq) {
                    Map.selected = null;
                } //Automatically deselects after calling move, whether troop has moved or not
                else if (Map.selected instanceof Troop) {
                    Troop tempSelected = (Troop) Map.selected;
                    tempSelected.move(sq);
                    Map.selected=null;
                }
            }

        }
        Auxilliary.updateButtons(Board.map);
    }
}

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
            if (Map.selected != null) { //selected exists
                if (Map.selected == sq) { //selected is clicked
                    Map.selected = null;
                } //selected is a troop and a player clicks on a different tile
                else if (Map.selected instanceof Troop) {
                    Troop tempSelected = (Troop) Map.selected;
                    if (tempSelected.canMove(sq)){
                        tempSelected.move(sq);
                        Map.selected=null;
                        Map.nextRound();
                    }
                } else { //selected is a Grass tile and the player clicks on a different tile
                    Map.selected = sq;
                }
            } else { //selected doesn't exist
                //either [sq is not a troop] or [sq is on the correct team to move]
                if (!(sq instanceof Troop) || ((Troop) sq).getTeam()==Map.turnToMove()) {
                    int xPos = (int) sq.getPosition().getX();
                    int yPos = (int) sq.getPosition().getY();
                    Map.selected = Map.gameMap[xPos][yPos];
                }
            }
        }
        Board.map.updateButtons();
    }
}

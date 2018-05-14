package FinalProject.Squares.Units;
import FinalProject.GameBoard.Map;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

import javax.swing.*;
import java.awt.*;

public class King extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redStar.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueStar.png"));
    public King(int xPos, int yPos, boolean team){
        super(null, xPos, yPos, team);
        if (team){
            this.setIcon(redIcon);
        } else {
            this.setIcon(blueIcon);
        }
    }
    public boolean canMove(Square target){
        double distance = distance(this,target);
        if(distance <= Math.sqrt(2)){
            return true;
        }
        return false;
    }
}

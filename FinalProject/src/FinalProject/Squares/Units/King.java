package FinalProject.Squares.Units;
import FinalProject.GameBoard.Map;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

import javax.swing.*;
import java.awt.*;

public class King extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redKing.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueKing.png"));
    
    int health = 2500;
    int damage = 10;
    
    public King(int xPos, int yPos, boolean team){
        super(null, xPos, yPos, team);
        if (team){
            this.setIcon(redIcon);
        } else {
            this.setIcon(blueIcon);
        }
    }
    public boolean canMove(Square target){
        double distance = distance(target);
        if(distance <= Math.sqrt(2)){
            return true;
        }
        return false;
    }
    
    public boolean canMove(int x, int y){
        Square target = new Square(null, x, y);
        return canMove(target);
    }
    
    public boolean attack(Troop enemy){
    	enemy.health = enemy.health - damage;
    	if(enemy.health <= 0){
    		enemy.beDestroyed();
    	}
    }
}
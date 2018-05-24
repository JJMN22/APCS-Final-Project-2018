package FinalProject.Squares.Units;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

public class Cavalry extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redCavalry.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueCavalry.png"));
    public Cavalry(int xPos, int yPos, boolean team){
        super(null, xPos, yPos, team);
        if (team){
            this.setIcon(redIcon);
        } else {
            this.setIcon(blueIcon);
        }
    }
    
    public boolean canMove(Square target){
        if(xDist(target) == 1 && yDist(target) == 3){
            return true;
        } else if (xDist(target) == 3 && yDist(target) == 1) {
            return true;
        }
        return false;
    }
    
    public boolean canMove(int x, int y){
        Square target = new Square(null, x, y);
        return canMove(target);
    }
}
package FinalProject.Squares.Units;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

public class Sniper extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redSniper.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueSniper.png"));
    public Sniper(int xPos, int yPos, boolean team){
        super(null, xPos, yPos, team);
        if (team){
            this.setIcon(redIcon);
        } else {
            this.setIcon(blueIcon);
        }
    }
    
    public boolean canMove(Square target){
        if(xDist(target) + yDist(target) <= 4){
            return true;
        }
        else if(xDist(target) == yDist(target) && xDist(target) <= 6)
        else{
        	return false;
        }
    }
    
    public boolean canMove(int x, int y){
        Square target = new Square(null, x, y);
        return canMove(target);
    }
}
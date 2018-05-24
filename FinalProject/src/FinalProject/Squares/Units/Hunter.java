package FinalProject.Squares.Units;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

public class Hunter extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redHunter.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueHunter.png"));
    public Hunter(int xPos, int yPos, boolean team){
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
        else{
        	return false;
        }
    }
    
    public boolean canMove(int x, int y){
        Square target = new Square(null, x, y);
        return canMove(target);
    }
}
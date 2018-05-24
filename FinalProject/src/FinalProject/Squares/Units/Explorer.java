package FinalProject.Squares.Units;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

public class Explorer extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redExplorer.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueExplorer.png"));
    
    int health = 90;
    int damage = 35;
    int cost = 60;
    
    public Explorer(int xPos, int yPos, boolean team){
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
    
    public boolean attack(Troop enemy){
    	enemy.health = enemy.health - damage;
    	if(enemy.health <= 0){
    		enemy.beDestroyed();
    	}
    }
}
package FinalProject.Squares.Units;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

public class Tank extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redTank.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueTank.png"));
    
    int health = 500;
    int damage = 175;
    int cost = 450;
    
    public Tank(int xPos, int yPos, boolean team){
        super(null, xPos, yPos, team);
        if (team){
            this.setIcon(redIcon);
        } else {
            this.setIcon(blueIcon);
        }
    }
    
    public boolean canMove(Square target){
        if(xDist(target) == 1 ||xDist(target) == 2 && yDist(target) == 4){
            return true;
        } else if (xDist(target) == 4 && yDist(target) == 1 || yDist(target) == 2) {
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
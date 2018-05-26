package FinalProject.Squares.Units;
import FinalProject.Squares.*;
import javax.swing.ImageIcon;

public class Bazooka extends Troop {
    private ImageIcon redIcon = new ImageIcon(getClass().getResource("redBazooka.png"));
    private ImageIcon blueIcon = new ImageIcon(getClass().getResource("blueBazooka.png"));
    
    int health = 225;
    int damage = 115;
    int cost = 250;
    
    public Bazooka(int xPos, int yPos, boolean team){
        super(null, xPos, yPos, team);
        if (team){
            this.setIcon(redIcon);
        } else {
            this.setIcon(blueIcon);
        }
    }
    
    public boolean canMove(Square target){
        if(xDist(target) + yDist(target) <= 2){
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
    
    public boolean canAttack(Troop target){
    	if(xDist(target) + yDist(target) <= 2){
    		if(occupiedBySameTeam(target) == false){
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean attack(Troop enemy){
    	enemy.health = enemy.health - damage;
    	if(enemy.health <= 0){
    		enemy.beDestroyed();
    	}
    }
    
    public void move(Square target) {
        int targetX = (int) target.getPosition().getX();
        int targetY = (int) target.getPosition().getY();

        if (this.canMove(target)){
        	//move
            if (target !instanceof Troop){
                Troop temp = this;
                Point oldPosition = position;
                int xPos = (int) oldPosition.getX();
                int yPos = (int) oldPosition.getY();
                Map.gameMap[targetX][targetY] = temp;
                Map.gameMap[targetX][targetY].setPosition(targetX,targetY);
                Map.gameMap[xPos][yPos] = new Grass(null,xPos, yPos);
                Map.gameMap[xPos][yPos].setPosition(xPos,yPos);
            }
        }
        if (this.canAttack(target)){
        	if(target instanceof Troop){
        		Troop enemy = (Troop) target;
        		attack(enemy);
        	}
        }
    }
}
package FinalProject.Squares;
import FinalProject.GameBoard.Map;
import FinalProject.EventHandling.Auxilliary;


import javax.swing.*;
import java.awt.*;

public abstract class Troop extends Square{

    //red = true; blue = false;
	public boolean team;

    /* Extraneous variables
    * public int range;
    * public int maxHealth;
	* public int currHealth;
	* public int maxMoves;
	* public int currMoves;
	* public int damage;
	* public int health;
	*/

    /*Extraneous methods
    public void attack(Troop enemy){
        //show menu with info displaying health (%) and one attack button and one move button
        double distance = distance(this,enemy);
        //can only attack adjacent troops; all troops within sqrt(2) distance are adjacent
    	if(distance <= Math.sqrt(2) && enemy.team != team){
    		if(moves > 0){
    			enemy.health = enemy.health - this.damage;
    			this.health = health - enemy.damage;
    			if(enemy.health <= 0){
                    Auxilliary.destroy(enemy);
                }
                if(this.health <= 0){
                    Auxilliary.destroy(this);
                }
    			moves--;
    		}
    	}
    }
     */

    public abstract boolean canMove(Square target);

    public void move(Square target) {
        int targetX = (int) target.getPosition().getX();
        int targetY = (int) target.getPosition().getY();

        if (this.canMove(target)){
            if (target instanceof Troop){
                Troop enemy = (Troop) target;
                move(attack(enemy));
            } else {
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
    }

    //Attacks squares, returns the destroyed square
    public Square attack(Troop enemy){
        return Auxilliary.destroy(enemy);
    }

    //Constructor
    public Troop(ImageIcon icon, int xPos, int yPos, boolean team){
        super(icon,xPos,yPos);
        this.team = team;
        this.setIcon(icon);
    }

    //Accessor Methods
    public boolean getTeam(){
        return this.team;
    }
}
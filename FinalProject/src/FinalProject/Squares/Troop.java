package FinalProject.Squares;
import FinalProject.GameBoard.Map;


import javax.swing.*;
import java.awt.*;
import java.lang.Math;

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
    public abstract boolean canMove(Square target); //if Troop can move to the target square
    public abstract boolean canMove(int x, int y);
    public boolean canAttack(Troop target){ //if Troop can attack the target square
        return !occupiedBySameTeam(target);
    }

    public void move(Square target) {
        int targetX = (int) target.getPosition().getX();
        int targetY = (int) target.getPosition().getY();

        if (this.canMove(target)){
            if (target instanceof Troop){
                Troop enemy = (Troop) target;
                if (canAttack(enemy)){
                    move(attack(enemy));
                }
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
        return enemy.beDestroyed();
    }

    //destroys a Square and replaces it with another
    public Square beDestroyed(){
        int xPos = this.fetchX();
        int yPos = this.fetchY();
        Map.gameMap[xPos][yPos] = new Grass(null, xPos, yPos);
        return Map.gameMap[xPos][yPos];
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
    
    //Movement Methods
    //---------------------------------------------
    //util method
    public int xDistance(Square otherSquare){
        return this.position.getX() - otherSquare.position.getX();
    }
    //util method
    public int yDistance(Square otherSquare){
        return this.position.getY() - otherSquare.position.getY();
    }

    //like a queen in chess
    public boolean isQueenMove(Square otherSquare, int range){
        return (isDiagonalMove(otherSquare, range) || isLinearMove(otherSquare, range);
    }

    //like a bishop in chess
    public boolean isDiagonalMove(Square otherSquare, int range){
        if(Math.abs(xDistance(otherSquare)) == abs(yDistance(otherSquare))){
            if(abs(xDistance(otherSquare)) <= range){
                return true;
            }
        }
        return false;
    }

    //like a rook in chess
    public boolean isLinearMove(Square otherSquare, int range){
        if(xDistance(otherSquare) <= range && yDistance(otherSquare) <= range){
            return(xDistance(otherSquare) == 0 || yDistance(otherSquare) == 0);
        }
        return false;
    }

    //like a knight in chess
    public boolean isLShaped(Square otherSquare){
        if(abs(xDistance(otherSquare)) == 1 && abs(yDistance(otherSquare)) == 2){
            return true;
        }
        if(abs(xDistance(otherSquare)) == 2 && abs(yDistance(otherSquare)) == 1){
            return true;
        }
        return false;
    }

    //in this context, a L shape is any line with one square outside
    public boolean isLShaped(Square otherSquare, int range){
        if(abs(xDistance(otherSquare)) == 1 && abs(yDistance(otherSquare)) == range){
            return true;
        }
        if(abs(xDistance(otherSquare)) == range && abs(yDistance(otherSquare)) == 1){
            return true;
        }
        return false;
    }

    //manhattan distance
    public boolean isInRange(Square otherSquare, int range){
        if(abs(xDistance(otherSquare)) + abs(yDistance(otherSquare)) <= range){
            return true;
        }
        return false;
    }

    public boolean isOccupied(Square otherSquare){
        if(otherSquare instanceof Troop){
            return true;
        }
        return false;
    }

    public boolean occupiedBySameTeam(Square otherSquare){
        if(otherSquare instanceof Troop){
            if(otherSquare.team == this.team){
                return true;
            }
        }
        return false;
    }

    public boolean occupiedByDifferentTeam(Square otherSquare){
        if(otherSquare instanceof Troop){
            if(otherSquare.team != this.team){
                return true;
            }
        }
        return false;
    }
	public void move(Square target) {
		int targetX = (int) target.getPosition().getX();
		int targetY = (int) target.getPosition().getY();

		if (this.canMove(target)){
			if (target instanceof Troop){
				Troop enemy = (Troop) target;
				if (canAttack(enemy)){
					move(attack(enemy));
				}
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

}

package FinalProject.Squares;
import FinalProject.GameBoard.Map;


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
    
    public boolean canMove(Square otherSquare){
		switch(this.type){
			//tier 1
			case "SWORDSMAN":
				return isInRange(otherSquare, 2);
			case "ARCHER":
				return isInRange(otherSquare, 2);
			case "HORSEMAN":
				return isLShaped(otherSquare);
			case "SCOUT":
				return isInRange(otherSquare, 3);
			//tier 2
			case "LONGSWORDSMAN":
				return isLinearMove(otherSquare, 3);
			case "CROSSBOW":
				return isQueenMove(otherSquare, 3);
			case "KNIGHT":
				return isLShaped(otherSquare, 3);
			case "EXPLORER":
				return isInRange(otherSquare, 4);
			//tier 3
			case "MUSKETEER":
				return isLinearMove(otherSquare, 4);
			case "FIELD_CANNON":
				return isQueenMove(otherSquare, 3);
			case "CAVALRY":
				return (isDiagonalMove(otherSquare, 4) || isLShaped(otherSquare, 5));
			case "HUNTER":
				return isInRange(otherSquare, 4);
			//tier 4
			case "INFANTRY":
				return isQueenMove(otherSquare, 4);
			case "BAZOOKA":
				return isInRange(otherSquare, 3);
			case "TANK":
				return (isDiagonalMove(otherSquare, 6) || isLShaped(otherSquare, 6));
			case "SPY":
				return isInRange(otherSquare, 5);
		}
	}
    
    //public abstract boolean canMove(Square target); //if Troop can move to the target square
    //public abstract boolean canMove(int x, int y);
    public boolean canAttack(Troop target){ //if Troop can attack the target square
        return !occupiedBySameTeam(target);
    }
    
    //need to add is ranged
    //move method is also the attack method
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
    //NEED to ADD : String type. Do this later
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
        if(abs(xDistance(otherSquare)) == abs(yDistance(otherSquare))){
            if(abs(xDistance(otherSquare)) <= range){
                return true;
            }
        }
        return false;
    }

    //like a rook in chess
    public boolean isLinearMove(Square otherSquare, int range){
        if(xDistance(otherSquare) <= range && yDistance(otherSquare) <= range){
            return(xDistance(otherSquare) == 0 || yDistnace(otherSquare) == 0);
        }
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
        if(otherSqure instanceof Troop){
            return truel
        }
        return false;
    }

    public boolean occupiedBySameTeam(Square otherSquare){
        if(otherSqure instanceof Troop){
            if(otherSquare.team == this.team){
                return true;
            }
        }
        return false;
    }

    public boolean occupiedByDifferentTeam(Square otherSquare){
        if(otherSqure instanceof Troop){
            if(otherSquare.team != this.team){
                return true;
            }
        }
        return false;
    }


}

import javax.swing.*;
import java.awt.*;
import java.math.*;

public class Troop extends Square{
	
	public boolean team;
	public int damage;
	
	//all troops have 100 nominal health (true health depends on how good the troop is)
	//the way damage is handled is more complex than health - damage
	public int health = 100;
	public String type;
	public boolean canPerformAction = true;
	
	//constructor
	public Troop(String name, ImageIcon icon, int xPos, int yPos, boolean team, int damage, String type){
		super(name, icon, xPos, yPos);
		this.team = team;
		this.damage = damage;
		this.type = type;
	}
	
	//called in the master class
	public void nextTurn(){
		health += 5;
		canPerformAction = true;
	}
	
	public void mouseClicked(MouseEvent e){
		//show menu with info displaying health (%) and one attack button and one move button
	}
	
	public void move(Square otherSquare){
		if(isValidMove(otherSquare) && canPerformAction = true){
			if(!(gameMap[otherSquare.position.getX()][otherSquare.position.getY()] instanceOf Troop)){
				//set OG position to grass, set otherSquare to this... 
			}
		}
	}
	
	public void attack(Square otherSquare){
		if(gameMap[otherSquare.position.getX()][otherSquare.position.getY()] instanceOf Troop && canPerformAction = true){
			if(type == "ARCHER" || type == "CROSSBOW" || type == "SPY" || type == "INFANTRY"){
				if(isInRange(otherSquare, 2)){
					//NEED to add, highlight all squares that can be attacked
					damageMultiplier = this.damage - otherSquare.damage + 5;
					otherSquare.health = otherSquare.health - (int)(Math.pow(1.25, damageMultiplier)*9);
					canPerformAction = false;
					//add DIE
				}
			}
			if(type == "FIELD_CANNON" || type == "BAZOOKA"){
				if(isInRange(otherSquare, 3)){
					//NEED to add, highlight all squares that can be attacked
					damageMultiplier = this.damage - otherSquare.damage + 5;
					otherSquare.health = otherSquare.health - (int)(Math.pow(1.25, damageMultiplier)*9);
					canPerformAction = false;
					//add DIE
				}
			}
			else{
				if(isInRange(otherSquare, 1)){
					//NEED to add, highlight all squares that can be attacked
					damageMultiplier = this.damage - otherSquare.damage + 5;
					otherSquare.health = otherSquare.health - (int)(Math.pow(1.25, damageMultiplier)*9);
					health = health - (int)(Math.pow(1.25, damageMultiplier)*3);
					canPerformAction = false;
					//add DIE
				}
			}
		}
		
	}
	
	public boolean isValidMove(Square otherSquare){
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
				return isLShaped(otherSquare, 4);
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

package FinalProject.GameBoard;

public class manaWell extends manaTile {
	//there will be no checking to make sure that the V2.manaWell is adjacent to a V2.manaTile
	//the checking will be done in the V2.manaTile class
	public int maxHealth = 100;
	public int currHealth = 100;
	public int manaPerTurn = 50;
	public boolean team;
	
	public manaWell(String name, int xPosition, int yPosition, boolean team){
		super(name, xPosition, yPosition);
		this.team = team;
	}
	
	public void nextRound(){
		//regains 10% of its health every round
		currHealth = maxHealth/10 + currHealth;
		if(team){
			Map.blueMana += manaPerTurn;
		}
		else{
			Map.redMana += manaPerTurn;
		}
	}
	
	/*public void die(){
		Map.gameMap[position.getX()][position.getY()] = new Grass();
	}*/
}

package FinalProject.GameBoard;/*Changelog
 *Version 1.0 - Created basic stuff
 *Version 1.1 - (Henry) Removed all extraneous code. Made map fit with the new and improved Square class, fixed formatting issues
 *(continued) added nextTurn, currency
 *Version 1.2 - (Mathew) Fixing up Henry's code in Troop.java
 *Version 1.3 - (Henry) Adding random things to Troop class, possibly revamping?
 * Version 1.4 - (Mathew) Moving event handling over to Auxillary and ClickAction classes
<<<<<<< HEAD
 * Version 1.5 - (Mathew) Implementing turns and turnhandling, redesigning Clickaction, Redesigning Board
 */
/*
*Todo: redesign Map so GUI and handling are two separate classes, make teams String based and not boolean based
* Todo: (less important) implement mana, sidebar, and dropping-we can make chess already, so that is our backup
=======
>>>>>>> parent of b80123a... Implementing turns and turnhandling, redesigning Clickaction, Redesigning Board
 */

import FinalProject.Squares.Square;
import FinalProject.Squares.*;
import FinalProject.Squares.Units.*;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.BorderFactory;


public class Map extends JPanel{
	//each round is two turns (blue moves, then red moves), we need two data members to improve readability
	public static int roundNumber = 0;
	//true = blue
	public static boolean activePlayer = true;
	
	public static int blueMana = 100;
	public static int redMana = 100;

	public static final Color backgroundColor=null;
	public static final Color moveToColor=Color.YELLOW;
	public static final Color selectedColor=new Color(148,0,211);
	public static final Color attackColor=Color.RED;
    public static final int SIZE = 9;
    public static Square[][] gameMap = new Square[SIZE][SIZE];
    public static Square selected;

    
    //Constructor, Initializes UI
    public Map(){
        for(int i = 0;i < SIZE; i++){
            for(int j = 0; j< SIZE; j++){
                gameMap[i][j] = new Grass(null,i, j);
            }
        }
        gameMap[2][2] = new King(2, 2, false);
        gameMap[1][1] = new King(1, 1, true);
        gameMap[5][5] = new Knight(5,5,true);
        initializeUI();
    }
    //Initializes UI by placing buttons on JPanel
    private void initializeUI(){
        setLayout(new GridLayout (SIZE, SIZE));
        for(int i = 0;i < SIZE; i++){
            for(int j = 0;j < SIZE; j++){
                add(gameMap[i][j]);
                gameMap[i][j].setFocusable(false);
            }
        }
        setPreferredSize(new Dimension(Square.SQUARE_LENGTH*SIZE, Square.SQUARE_LENGTH*SIZE));
        setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.ORANGE));
        setVisible(true);
    }
    //Redraws the Map
    public void updateButtons() {
        this.removeAll();
        for (int i = 0; i < Map.SIZE; i++) {
            for (int j = 0; j < Map.SIZE; j++){
                gameMap[i][j].setOpaque(true);
                gameMap[i][j].setBackground(backgroundColor);
                /* If Statements check three cases
                * 1: SELECTED does exist
                * 2: SELECTED is the Square that is clicked; SELECTED's color is changed to selectedColor
                * 3: A square besides SELECTED is clicked and SELECTED is a Troop; color is changed accordingly
                 */
                if (selected!=null) { //make sure selected exists & selected is a troop
                    if (selected == gameMap[i][j]) { //if gamemap[i][j] is Map.selected
                        selected.setBackground(selectedColor);
                    } else { //thisSquare is created in case gamemap[i][j] is a Grass object.
                        if (selected instanceof Troop){ //
                            Troop selectedTroop = (Troop) selected;
                            Square thisSquare = gameMap[i][j];
                            int thisX = (int) gameMap[i][j].position.getX();
                            int thisY = (int) gameMap[i][j].position.getY();
                            if (selectedTroop.canMove(thisX,thisY)) { //if gameMap[i][j] is in selectedTroop's movement pattern
                                if (!thisSquare.occupiedBySameTeam(selectedTroop)||!thisSquare.isOccupied()) {
                                    if (gameMap[i][j] instanceof Troop){
                                        gameMap[i][j].setBackground(attackColor);
                                    } else {
                                        gameMap[i][j].setBackground(moveToColor);

                                    }
                                }
                            }
                        }
                    }
                }
                Map.gameMap[i][j].setFocusable(false);
                this.add(gameMap[i][j]);
            }
        }
        this.validate();
        this.repaint();
    }

    //destroys a Square and replaces it with another
    public static Square destroy(Square sq){
        Point pos = sq.getPosition();
        int xPos = (int) pos.getX();
        int yPos = (int) pos.getY();
        Map.gameMap[xPos][yPos] = new Grass(null, xPos, yPos);
        return Map.gameMap[xPos][yPos];
    }
    //increments round number
    public void nextRound(){
    	for(int i = 0; i< SIZE; i++){
    		for(int j = 0; j < SIZE; j++){
    			roundNumber++;
    		}
    	}
    }
    //toString method prints out selected Tile for debugging purposes
    public String toString(){
        if (selected!=null){
            int x = (int) selected.position.getX();
            int y = (int) selected.position.getY();
            boolean j = selected instanceof Troop;
            return "Selected Tile position: "+x+" "+y+"; selected tile instanceof Troop: "+j;
        } else {
            return "No selected tile";
        }
    }
}

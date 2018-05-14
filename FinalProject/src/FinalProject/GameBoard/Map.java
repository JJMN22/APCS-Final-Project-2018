package FinalProject.GameBoard;/*Changelog
 *Version 1.0 - Created basic stuff
 *Version 1.1 - (Henry) Removed all extraneous code. Made map fit with the new and improved Square class, fixed formatting issues
 *(continued) added nextTurn, currency
 *Version 1.2 - (Mathew) Fixing up Henry's code in Troop.java
 *Version 1.3 - (Henry) Adding random things to Troop class, possibly revamping?
 * Version 1.4 - (Mathew) Moving event handling over to Auxillary and ClickAction classes
 */

import FinalProject.Squares.Square;
import FinalProject.Squares.*;
import FinalProject.Squares.Units.King;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Color;



public class Map extends JPanel{
	//each round is two turns (blue moves, then red moves), we need two data members to improve readability
	public static int roundNumber = 0;
	//true = blue
	public static boolean activePlayer = true;
	
	public static int blueMana = 100;
	public static int redMana = 100;
	
    public static final int SIZE = 9;
    public static Square[][] gameMap = new Square[SIZE][SIZE];
    public static Square selected;
    
    //Constructor, Initalizes UI
    public Map(){
        for(int i = 0;i < SIZE; i++){
            for(int j = 0; j< SIZE; j++){
                gameMap[i][j] = new Grass(null,i, j);
            }
        }
        gameMap[2][2] = new King(2, 2, false);
        gameMap[1][1] = new King(1, 1, true);
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
    
    public void nextRound(){
    	for(int i = 0; i< SIZE; i++){
    		for(int j = 0; j < SIZE; j++){
    			roundNumber++;
    		}
    	}
    }
}

package FinalProject.Squares;

import javax.swing.*;

public class Grass extends Square {
    private ImageIcon grassIcon = new ImageIcon(getClass().getResource("grass.png"));
    public Grass(ImageIcon icon, int xPosition, int yPosition) {
    	super(icon, xPosition, yPosition);
    	//this.setIcon(grassIcon);
    }

}

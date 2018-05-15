package FinalProject.Squares;
import FinalProject.GameBoard.Map;
import FinalProject.EventHandling.*;


import javax.swing.*;
import java.awt.*;

//Simple Square whose child classes will tile the V2.Map
public class Square extends JButton {
    //length of side of square
    public static final int SQUARE_LENGTH = 79;
    //Point class to store x position and y position within map; position is initialized in V2.Map.java
    public Point position = new Point(0,0);

    //sets position
    public void setPosition(int i, int j){
        position.setLocation(i,j);
    }

    //Utility Methods
    public double distance(Square s){
        double X1 = s.position.getX();
        double Y1 = s.position.getY();
        double X2 = this.position.getX();
        double Y2 = this.position.getY();
        double xDist = X1-X2;
        double yDist = Y1-Y2;
        //Distance Formula
        double distance = Math.sqrt(Math.pow(xDist,2) + Math.pow(yDist,2));
        return distance;
    }
    public double xDist(Square s){
        return Math.abs(this.position.getX()-s.position.getX());
    }
    public double yDist(Square s){
        return Math.abs(this.position.getY()-s.position.getY());
    }
    //If square is occupied
    public boolean isOccupied(){
        return this instanceof Troop;
    }
    //If occupied squares are on the same team
    public boolean occupiedBySameTeam(Square otherSquare) {
        if (this.isOccupied() && otherSquare.isOccupied()) {
            Troop t1 = (Troop) this;
            Troop t2 = (Troop) otherSquare;
            return t1.getTeam() == t2.getTeam();
        }
        return false;
    }

    //Constructor
    public Square(ImageIcon icon, int xPos, int yPos) {
        super("");
        position.setLocation(xPos,yPos);
        ClickAction clickHandler = new ClickAction();
        this.addMouseListener(clickHandler);
        this.setIcon(icon);
    }


    //Accessor methods
    public Point getPosition(){
        return this.position;
    }
}

package FinalProject.GameBoard;
import FinalProject.Squares.Square;


import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    public static Map map = new Map();
    public static InfoBar infobar = new InfoBar();
    //Constructor; places Map onto Board
    public Board() {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Boardgame");
        setSize(Square.SQUARE_LENGTH*Map.SIZE+200,Square.SQUARE_LENGTH*Map.SIZE);
        add(infobar, BorderLayout.NORTH);
        add(map, BorderLayout.CENTER);
        add(infobar, BorderLayout.LINE_START);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Board();
    }
}

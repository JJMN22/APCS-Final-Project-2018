package FinalProject.GameBoard;

import FinalProject.Squares.Square;

import javax.swing.*;
import java.awt.*;

public class InfoBar extends JPanel{
    public InfoBar(){
        initializeUI();
    }
    private void initializeUI(){
        setLayout(new GroupLayout(this));
        setPreferredSize(new Dimension(200, Square.SQUARE_LENGTH*Map.SIZE));
        setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.ORANGE));
        setVisible(true);
    }
}

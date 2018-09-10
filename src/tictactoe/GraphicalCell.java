package tictactoe;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author William Taylor <musicmrman99@gmail.com>
 */
public class GraphicalCell extends JButton {
    private final Color colour = java.awt.Color.LIGHT_GRAY;
    public final int row;
    public final int col;
    
    public GraphicalCell(int row, int col) {
        super();
        
        this.setBackground(colour);
        this.setFont(new Font("Arial", Font.PLAIN, 40));
        
        this.row = row;
        this.col = col;
    }
}

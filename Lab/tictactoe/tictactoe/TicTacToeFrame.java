import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 */

public class TicTacToeFrame extends TicTacToe 
{ 
    private JTextArea status; // text area to print game status
    private JScrollPane sp;
    /** 
     * Constructs a new Tic-Tac-Toe board and sets up the basic
     * JFrame containing a JTextArea in a JScrollPane GUI.
     */
    public TicTacToeFrame()
    { 
        super();
        JFrame frame = new JFrame("TicTacToe");
        Container contentPane =frame.getContentPane();
        frame.setResizable(true);
        frame.setSize(300,300);
        
        sp = new JScrollPane();
        
        status = new JTextArea();
        status.setVisible(true);
        
        sp.getViewport().add(status);
        
        frame.add(sp);
        frame.pack();
        frame.setVisible(true); 
    }

    /**
     * Prints the board to the GUI using toString().
     */
    public void print() 
    {  
        status.setText(null);
        status.append(toString());
    }
}
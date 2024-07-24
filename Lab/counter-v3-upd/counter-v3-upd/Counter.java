/**
 * An up/down counter with a simple GUI.
 * 
 * @author Lynn Marshall
 * @version November 17, 2012
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Counter implements ActionListener
{
    /* The current value of the counter. */
    private int count;

    // The constants
    public static final int MINIMUM = 0;
    public static final int RESET_TO = 5;
    public static final int MAXIMUM = 10;

    /* A JTextField displays the current value of the counter. */
    private JTextField counterDisplay;

    /* The button that is clicked to increment the counter. */
    private JButton upButton;

    /* The button that is clicked to decrement the counter. */
    private JButton downButton;

    /* The button that is clicked to reset the counter. */
   private JButton resetButton;

   private JButton randomButton;

   /* The reset menu item */
   private JMenuItem resetItem;

   /* The quit menu item */
   private JMenuItem quitItem;

   private JMenuItem clearItem;

   /* The history area */
   private JTextArea history;
   private JLabel current;

   public Counter() {
      // model
      count = 0;

      // JFrame
      JFrame frame = new JFrame("Counter");
      Container contentPane = frame.getContentPane(); 
      contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS)); // use border layout (default)

      JMenuBar menubar = new JMenuBar();
      frame.setJMenuBar(menubar); // add menu bar to our frame
      JPanel buttonPanel = new JPanel();
      //contentPane.add(buttonPanel);
      JPanel current = new JPanel();
      //contentPane.add(current);
      
      JMenu fileMenu = new JMenu("Options"); // create a menu
      menubar.add(fileMenu); // and add to our menu bar

      resetItem = new JMenuItem("Reset"); // create a menu item called "Reset"
      fileMenu.add(resetItem); // and add to our menu

      quitItem = new JMenuItem("Quit"); // create a menu item called "Quit"
      fileMenu.add(quitItem); // and add to our menu

      clearItem = new JMenuItem("Clear");
      fileMenu.add(clearItem);

      // this allows us to use shortcuts (e.g. Ctrl-R and Ctrl-Q)
      final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); // to save typing
      resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, SHORTCUT_MASK));
      quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
      clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, SHORTCUT_MASK));

      // listen for menu selections
      resetItem.addActionListener(this); 
      quitItem.addActionListener(new ActionListener() // create an anonymous inner class
        { // start of anonymous subclass of ActionListener
          // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0); // quit
            }
        } // end of anonymous subclass
      ); // end of addActionListener parameter list and statement
      clearItem.addActionListener(this);
      // add a label
      JLabel label = new JLabel("the Counter is");
      label.setHorizontalAlignment(JLabel.RIGHT); // right justified
      current.add(label,BorderLayout.WEST); // west side 

      // Middle area (counterDisplay): current counter value
      counterDisplay = new JTextField(5); // text field is 5 characters wide
      counterDisplay.setEditable(false); // we cannot edit this field
      counterDisplay.setFont(new Font(null, Font.BOLD, 18)); // bold 18pt font
      counterDisplay.setHorizontalAlignment(JTextField.RIGHT); // right justified
      current.add(counterDisplay, BorderLayout.EAST); // east side

      /* Update the view to reflect the initial state of the model. */
      counterDisplay.setText("" + count); 

      // Top Area (buttonPanel): buttons
      
      buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS)); // 1 x 3 grid
      // The Up, Down and Reset buttons are arranged horizontally in a JPanel

      upButton = new JButton("Up");
      buttonPanel.add(upButton);

      downButton = new JButton("Down");
      buttonPanel.add(downButton);

      /* Initially the Down button is disabled. */
      downButton.setEnabled(false);      

      resetButton = new JButton("Reset");
      buttonPanel.add(resetButton);

      randomButton = new JButton("Random");
      buttonPanel.add(randomButton);

      /* Place the buttons at the top. */
      contentPane.add(buttonPanel, BorderLayout.NORTH); // north side

      // register buttons as listeners
      upButton.addActionListener(this); 
      downButton.addActionListener(this);
      resetButton.addActionListener(this); 
      randomButton.addActionListener(this);

      // Add a scrollable JTextArea (i.e. put it in a JScrollPane)
      // and use it to display the history
      
      contentPane.add(buttonPanel);
      contentPane.add(current);
      
      history = new JTextArea(20,10);
      JScrollPane pane = new JScrollPane(history); // put text area in a scroll pane
      contentPane.add(pane); // south side
      history.append("\nThe counter value is: " + count);
      history.setCaretPosition(history.getDocument().getLength()); // move cursor to end

      // finish setting up the frame
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit when we hit the "X"
      frame.pack(); // pack everthing into our frame
      frame.setResizable(false); // we can resize it
      frame.setVisible(true); // it's visible
   }

   /** This action listener is called when the user clicks on 
    * any of the GUI's buttons. 
    */
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource(); // get the action 
        Random r = new Random();

        // see if it's a JButton
        if (o instanceof JButton) {

            JButton button = (JButton)o;

            if (button == downButton) { // decrement
                count--;
            } else if (button == upButton) { // increment
                count++;
            } else if (button == resetButton) { // reset
                count = RESET_TO;
            } else if (button == randomButton){
                count = r.nextInt(MAXIMUM-MINIMUM+1)+MINIMUM;
            }
        } else { // it's a JMenuItem

            JMenuItem item = (JMenuItem)o;

            if (item == resetItem) { // reset
                count = RESET_TO;
            //} else if (item == quitItem) { // quit
            //    System.exit(0);
            }else if (item == clearItem){
                history.setText("");
            }               
        }
        // Ensure that the right buttons are enabled.
        // the down button is enabled as long as count isn't minimum, etc.
        downButton.setEnabled(count != MINIMUM);
        upButton.setEnabled(count != MAXIMUM);
        resetButton.setEnabled(count != RESET_TO);
        randomButton.setEnabled(true);

        // update the display
        counterDisplay.setText("" + count); 
        history.append("\nThe counter value is: " + count);
        history.setCaretPosition(history.getDocument().getLength());
   }    
}
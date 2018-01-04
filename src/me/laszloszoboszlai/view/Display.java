package me.laszloszoboszlai.view;

import javax.swing.*;
import java.awt.*;

/**
 * Simple GUI to display the result of using the recycling machine.
 */
public class Display extends JFrame implements PrinterInterface {
    /**
     * A serialVersionUID is required by the JFrame class.
     */
    private static final long serialVersionUID = -8505887234618184162L;
    private JTextArea outputWindow;

    /**
     * Basic window is created using awt.
     */
    public Display() {
        super();
        this.setTitle("Display");
        this.pack();
        this.setLocation(0, 0);

        setSize(300, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        outputWindow = new JTextArea();
        outputWindow.setForeground(Color.GREEN);
        getContentPane().add(outputWindow);
        setVisible(true);
    }

    /**
     * Prints the text str to the screen. Any previous text will be overwritten.
     *
     * @see PrinterInterface#print(java.lang.String)
     */
    public void print(String str) {
        outputWindow.setText(str);
        outputWindow.repaint();
    }

}
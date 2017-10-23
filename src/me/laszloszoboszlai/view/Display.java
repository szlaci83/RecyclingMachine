package me.laszloszoboszlai.view;

import me.laszloszoboszlai.service.CustomerPanel;

import java.awt.Color;
import javax.swing.*;
/**
 * Simple GUI to display the result of using the recycling machine.
 */
public class Display extends JFrame implements CustomerPanel.PrinterInterface {
    /**
     * A serialVersionUID is required by the JFrame class.
     */
    private static final long serialVersionUID = -8505887234618184162L;
    private JTextArea outputWindow;

    /**
     *  Basic window is created using awt.
     */
    public Display() {
        super();
        setSize(200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        outputWindow = new JTextArea();
        outputWindow.setForeground(Color.MAGENTA);
        getContentPane().add(outputWindow);
        setVisible(true);
    }
    /**
     * Prints the text str to the screen. Any previous text will be overwritten.
     * @see CustomerPanel.PrinterInterface#print(java.lang.String)
     */
    public void print(String str) {
        outputWindow.setText(str);
        outputWindow.repaint();
    }

}
package me.laszloszoboszlai.view.GUI;

import org.jfree.chart.ChartFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoteGUI extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {



    }

    public RemoteGUI(){
        super();
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        ChartFactory.createBarChart("Machine 1", "Dates", "Pieces",  null, null, true, true, true);
    }
    public static void main(String [] args ) {
        RemoteGUI remoteGUI = new RemoteGUI();
        remoteGUI.setVisible(true);
    }
}


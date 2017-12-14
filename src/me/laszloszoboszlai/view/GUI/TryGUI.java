package me.laszloszoboszlai.view.GUI;

import javax.swing.*;

public class TryGUI  extends JFrame{
    private JLabel lblChartplaceholder = new JLabel("");

    public TryGUI(){
        this.pack();
        this.setSize(420,340);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Admin Dashboard");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        lblChartplaceholder.setBounds(120, 0, 600, 400);
        panel.add(lblChartplaceholder);


        getContentPane().add(panel);
        panel.repaint();

    }

    public static void main(String[] args) {
        TryGUI gui = new TryGUI();
        gui.setVisible(true);
    }
}

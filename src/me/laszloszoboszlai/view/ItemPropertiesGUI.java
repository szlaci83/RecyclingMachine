package me.laszloszoboszlai.view;

import me.laszloszoboszlai.service.CustomerPanel;

import javax.swing.*;

public class ItemPropertiesGUI extends JFrame{
    private JButton empty = new JButton("Empty");
    private JButton up = new JButton("▲");
    private JButton down = new JButton("▼");
    private JButton set = new JButton("Set");

    private JLabel valueLabel = new JLabel("Value:");
    private JLabel depositedLabel = new JLabel("Deposited:");

    private JLabel value = new JLabel();
    private JLabel deposited = new JLabel();

    public ItemPropertiesGUI(CustomerPanel customerPanel, String name){
        if (customerPanel == null) {
            customerPanel = new CustomerPanel(new Display());
        }

        this.pack();
        this.setSize(420,340);
        this.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(null);

        depositedLabel.setBounds(20, 20, 120, 80);
        panel.add(depositedLabel);

        deposited.setText("55");
        deposited.setBounds(20, 40, 120, 80);
        panel.add(deposited);
        empty.setBounds(20, 120, 120, 80);
        panel.add(empty);

        set.setBounds(240, 120, 120, 80);
        panel.add(set);


        up.setBounds(180, 60, 45,45);
        panel.add(up);
        value.setText("12");
        value.setBounds(180, 140, 45,45);
        panel.add(value);
        down.setBounds(180, 220, 45,45);
        panel.add(down);
        valueLabel.setBounds(180, - 40, 85,145);
        panel.add(valueLabel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(name + " properties settings.");

        getContentPane().add(panel);
        panel.repaint();
    }
    public static void main(String [] args ) {
        ItemPropertiesGUI myGUI = new ItemPropertiesGUI(null, null);
        myGUI.setVisible(true);
    }
}
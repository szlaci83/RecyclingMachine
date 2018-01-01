package me.laszloszoboszlai.view.GUI;

import me.laszloszoboszlai.remote.RecycleRemoteConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * GUI to display the properties of different depositable items.
 */
public class ItemPropertiesGUI extends JFrame implements ActionListener{
    private JButton empty = new JButton("Empty");
    private JButton up = new JButton("▲");
    private JButton down = new JButton("▼");
    private JButton set = new JButton("Set");
    private JButton modify = new JButton("Modify");

    private JLabel valueLabel = new JLabel("Value:");
    private JLabel depositedLabel = new JLabel("Deposited:");
    private JLabel capacityLabel = new JLabel();

    private JLabel value = new JLabel();
    private JLabel deposited = new JLabel();

    private Long status;
    private Long capacity;
    private RecycleRemoteConnection connection;
    private String name;
    private String token;
    private JTextField capacityText = new JTextField(6);

    /**
     * Helper method to return the slot number of a given item (by name)
     * @param name name of the item
     * @return the slot number of the item
     */
    private int nameToSlot(String name){
        switch (name) {
            case "Bottle":
                return 2;
            case "Can":
                return 1;
            case "Crate":
                return 3;
            case "Carton":
                return 4;
        }
        return 0;
    }

    /**
     * Eventhandler for the button presses.
     * @param e the event triggering the handler
     */
    public void actionPerformed(ActionEvent e) {
        int value = Integer.valueOf(this.value.getText());
        if (!capacityText.getText().isEmpty()) {
            try {
                capacity = Long.parseLong(capacityText.getText());
            }catch (NumberFormatException exc){
                JOptionPane.showMessageDialog(this, "Error, enter a number please!");
            }
        }
        String buttonName = e.getActionCommand();

        switch (buttonName){
            case "Empty" :
                try {
                    if (!this.connection.emptySlot(this.token, nameToSlot(this.name))){
                        JOptionPane.showMessageDialog(this, "Error, login required!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Counter for " + this.name + " reset to 0!");
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                }
                this.deposited.setText("0");
                break;
            case "▲" :
                this.value.setText(String.valueOf(++value));
                break;
            case "▼" :
                this.value.setText(String.valueOf(--value));
                break;
            case "Set" :
                try {
                    if (!this.connection.changeItemValue(this.token, this.name, value)){
                        JOptionPane.showMessageDialog(this, "Error, login required!");
                    } else {
                        JOptionPane.showMessageDialog(this, "New value set to: " + value +"p");
                    }

                } catch (RemoteException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                }
                break;
            case "Modify" :
                try {
                    if (!this.connection.setCapacity(this.token, this.name, capacity)){
                        JOptionPane.showMessageDialog(this, "Error, login required!");
                    }
                } catch (RemoteException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                }
                this.capacityLabel.setText("capacity: " + capacity);
                JOptionPane.showMessageDialog(this, "New capacity set to: " + capacity);
        }
        this.repaint();
    }

    /**
     * Constructor to create an ItemProperties GUI for a given item.
     * @param connection the remote connection to be used by the GUI
     * @param token the user-token of the logged in user to be used by the GUI
     * @param name name of the item we want to display the GUI for
     * @throws IOException
     */
    public ItemPropertiesGUI(RecycleRemoteConnection connection, String token, String name) throws IOException{
        this.name = name;
        this.token = token;
        this.connection = connection;
        Object ItemStatus =  this.connection.getStatus(this.token).get(name);
        if (ItemStatus != null) {
            this.status = Long.parseLong((String) this.connection.getStatus(this.token).get(name));
        } else {
            this.status = 0L;
        }
        this.capacity = Long.parseLong((String) this.connection.getCapacity(this.token).get(name));
        this.pack();
        this.setSize(420,440);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        depositedLabel.setBounds(20, 20, 120, 80);
        panel.add(depositedLabel);
        deposited.setText(status.toString());
        deposited.setBounds(20, 40, 120, 80);
        panel.add(deposited);
        empty.setBounds(20, 140, 80, 60);
        empty.addActionListener(this);
        panel.add(empty);

        set.setBounds(240, 140, 80, 60);
        set.addActionListener(this);
        panel.add(set);

        capacityLabel.setText("capacity: " + capacity.toString());
        capacityLabel.setBounds(20, 300, 160, 30);
        panel.add(capacityLabel);

        capacityText.setBounds(170, 300, 80, 30);
        panel.add(capacityText);

        modify.setBounds(255, 300, 80, 30);
        modify.addActionListener(this);
        panel.add(modify);


        up.setBounds(180, 60, 55,45);
        up.addActionListener(this);
        panel.add(up);
        int rs = this.connection.getItemValue(token, name);
        value.setText(String.valueOf(rs));
        value.setBounds(180, 140, 45,45);
        panel.add(value);
        down.setBounds(180, 220, 55,45);
        down.addActionListener(this);
        panel.add(down);
        valueLabel.setBounds(180, - 40, 85,145);
        panel.add(valueLabel);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle(name + " properties settings.");

        getContentPane().add(panel);
        panel.repaint();
    }
}
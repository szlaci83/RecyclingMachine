package me.laszloszoboszlai.view.GUI;

import me.laszloszoboszlai.exception.NotLoggedInException;
import me.laszloszoboszlai.remote.RecycleRemoteConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

public class ItemPropertiesGUI extends JFrame implements ActionListener{
    private JButton empty = new JButton("Empty");
    private JButton up = new JButton("▲");
    private JButton down = new JButton("▼");
    private JButton set = new JButton("Set");
    private JButton save = new JButton("Save");

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

    public void actionPerformed(ActionEvent e) {
        int value = Integer.valueOf(this.value.getText());
        if (!capacityText.getText().isEmpty()) {
            capacity = Long.parseLong(capacityText.getText());
        }
        String buttonName = e.getActionCommand();

        switch (buttonName){
            case "Empty" :
                try {
                    this.connection.emptySlot(this.token, nameToSlot(this.name));
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                } catch (NotLoggedInException e1) {
                    JOptionPane.showMessageDialog(this, "Error, login required!");
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
                    this.connection.changeItemValue(this.token, this.name, value);
                } catch (RemoteException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                } catch (NotLoggedInException e1) {
                    JOptionPane.showMessageDialog(this, "Error, login required!");
                    e1.printStackTrace();
                }
                break;
            case "Save" :
                try {
                    this.connection.setCapacity(this.token, this.name, capacity);
                } catch (RemoteException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    e1.printStackTrace();
                } catch (NotLoggedInException e1) {
                    JOptionPane.showMessageDialog(this, "Error, login required!");
                    e1.printStackTrace();
                }
                this.capacityLabel.setText(capacity.toString());
        }
        this.repaint();
    }

    public ItemPropertiesGUI(RecycleRemoteConnection rmi, String token, String name) throws IOException, NotLoggedInException {
        this.name = name;
        this.token = token;
        this.connection = rmi;
        Object ItemStatus =  this.connection.getStatus(this.token).get(name);
        if (ItemStatus != null) {
            this.status = Long.parseLong((String) this.connection.getStatus(this.token).get(name));
        } else {
            this.status = 0L;
        }
        this.capacity = Long.parseLong((String) this.connection.getCapacity(this.token).get(name));
        this.pack();
        this.setSize(420,340);
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

        capacityLabel.setText(capacity.toString());
        capacityLabel.setBounds(20, 300, 80, 30);
        panel.add(capacityLabel);

        capacityText.setBounds(80, 300, 80, 30);
        panel.add(capacityText);

        save.setBounds(165, 300, 80, 30);
        save.addActionListener(this);
        panel.add(save);


        up.setBounds(180, 60, 45,45);
        up.addActionListener(this);
        panel.add(up);
        int rs = this.connection.getItemValue(token, name);
        value.setText(String.valueOf(rs));
        value.setBounds(180, 140, 45,45);
        panel.add(value);
        down.setBounds(180, 220, 45,45);
        down.addActionListener(this);
        panel.add(down);
        valueLabel.setBounds(180, - 40, 85,145);
        panel.add(valueLabel);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle(name + " properties settings.");

        getContentPane().add(panel);
        panel.repaint();
    }
    public static void main(String [] args ) throws IOException {
        // ItemPropertiesGUI myGUI = new ItemPropertiesGUI(null, null);
        // myGUI.setVisible(true);
    }
}
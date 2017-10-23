package me.laszloszoboszlai.view;

import me.laszloszoboszlai.service.CustomerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    /**
     * A Simple Graphical User Interface for the Recycling Machine.
     * @author Marc Conrad
     *
     */
    public class RecyclingGUI extends JFrame implements ActionListener  {
        CustomerPanel myCustomerPanel;

        public void actionPerformed(ActionEvent e) {
            String buttonName = e.getActionCommand();
            switch (buttonName){
                case "Slot 1" :
                    myCustomerPanel.itemReceived(1);
                    break;
                case "Slot 2" :
                    myCustomerPanel.itemReceived(2);
                    break;
                case "Slot 3" :
                    myCustomerPanel.itemReceived(3);
                    break;
                case "Slot 4" :
                    myCustomerPanel.itemReceived(4);
                    break;
                case "Status" :
                    myCustomerPanel.printStatus();
                    break;
                case "Receipt":
                    myCustomerPanel.printReceipt();

            }
        }

        JButton slot1 = new JButton("Slot 1");
        JButton slot2 = new JButton("Slot 2");
        JButton slot3 = new JButton("Slot 3");
        JButton slot4 = new JButton("Slot 4");

        JButton status = new JButton("Status");
        JButton receipt = new JButton("Receipt");

        public RecyclingGUI() {
            super();
            setSize(400, 100);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            panel.add(slot1);
            panel.add(slot2);
            panel.add(slot3);
            panel.add(slot4);
            panel.add(receipt);
            panel.add(status);

            slot1.addActionListener(this);
            slot2.addActionListener(this);
            slot3.addActionListener(this);
            slot4.addActionListener(this);
            status.addActionListener(this);
            receipt.addActionListener(this);

            getContentPane().add(panel);
            panel.repaint();

            myCustomerPanel
                    = new CustomerPanel(new Display());

        }

        public static void main(String [] args ) {
            RecyclingGUI myGUI = new RecyclingGUI();
            myGUI.setVisible(true);
        }
    }
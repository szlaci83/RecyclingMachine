package me.laszloszoboszlai.view;

import me.laszloszoboszlai.service.CustomerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A Simple Graphical User Interface for the Recycling Machine maintanance.
 * @author Laszlo Szoboszlai
 *
 */
public class StatusGUI extends JFrame implements ActionListener{

        private static String PATH = "/me/laszloszoboszlai/img/";

        private JLabel lblImageplaceholder = new JLabel("");
        private Image img = new ImageIcon(this.getClass().getResource(PATH + "maintanance.png")).getImage();

        private Image canImg = new ImageIcon(this.getClass().getResource(PATH + "can.png")).getImage();
        private Image statusImg = new ImageIcon(this.getClass().getResource(PATH + "status.png")).getImage();
        private Image receiptImg = new ImageIcon(this.getClass().getResource(PATH + "receipt.png")).getImage();
        private Image bottleImg = new ImageIcon(this.getClass().getResource(PATH + "bottle.jpg")).getImage();
        private Image cartonImg = new ImageIcon(this.getClass().getResource(PATH + "carton.png")).getImage();
        private Image crateImg = new ImageIcon(this.getClass().getResource(PATH + "crate.png")).getImage();

        CustomerPanel myCustomerPanel;

        public void actionPerformed(ActionEvent e) {
            String buttonName = e.getActionCommand();
            switch (buttonName){
                case "Bottle" :
                    try {
                        myCustomerPanel.emptySlot(2);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Can" :
                    try {
                        myCustomerPanel.emptySlot(1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Crate" :
                    try {
                        myCustomerPanel.emptySlot(3);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Carton" :
                    try {
                        myCustomerPanel.emptySlot(4);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Status" :
                    myCustomerPanel.printStatus();
                    break;
                case "Receipt":
                    try {
                        myCustomerPanel.printReceipt();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

            }
        }


        JButton bottle = new JButton("Bottle");
        JButton can = new JButton("Can");

        JButton crate = new JButton("Crate");
        JButton carton = new JButton("Carton");

        JButton status = new JButton("Status");
        JButton receipt = new JButton("Receipt");


        private Image scaleDown(Image img){
            return img.getScaledInstance(50, 80, Image.SCALE_SMOOTH);
        }

        public StatusGUI() {
            this.pack();
            this.setSize(640,800);
            this.setLocationRelativeTo(null);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Maintanance panel");

            Image scaledBack = img.getScaledInstance(450, 460, Image.SCALE_SMOOTH);
            lblImageplaceholder.setBounds(100, 0, 500, 530);
            lblImageplaceholder.setIcon(new ImageIcon(scaledBack));


            JPanel panel = new JPanel();
            panel.setLayout(null);

            panel.add(lblImageplaceholder);


            can.setBounds(100, 500, 150, 100);

            can.setIcon(new ImageIcon(scaleDown(canImg)));
            panel.add(can);

            bottle.setBounds(265, 500, 150, 100);
            bottle.setIcon(new ImageIcon(scaleDown(bottleImg)));
            panel.add(bottle);


            crate.setBounds(100, 615, 150, 100);
            crate.setIcon(new ImageIcon(scaleDown(crateImg)));
            panel.add(crate);

            carton.setBounds(265, 615, 150, 100);
            carton.setIcon(new ImageIcon(scaleDown(cartonImg)));
            panel.add(carton);

            receipt.setBounds(430, 500, 150, 100);
            receipt.setIcon(new ImageIcon(scaleDown(receiptImg)));
            panel.add(receipt);


            status.setBounds(430, 615, 150, 100);
            status.setIcon(new ImageIcon(scaleDown(statusImg)));
            panel.add(status);

            can.addActionListener(this);
            bottle.addActionListener(this);
            crate.addActionListener(this);
            carton.addActionListener(this);
            status.addActionListener(this);
            receipt.addActionListener(this);

            getContentPane().add(panel);
            panel.repaint();

            myCustomerPanel
                    = new CustomerPanel(new Display());

        }

        public static void main(String [] args ) {
            me.laszloszoboszlai.view.StatusGUI myGUI = new me.laszloszoboszlai.view.StatusGUI();
            myGUI.setVisible(true);
        }
}

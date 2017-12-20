package me.laszloszoboszlai.view.GUI;

import me.laszloszoboszlai.remote.RecycleRemoteConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

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
        private Image receiptImg = new ImageIcon(this.getClass().getResource(PATH + "receipt.png")).getImage();
        private Image bottleImg = new ImageIcon(this.getClass().getResource(PATH + "bottle.jpg")).getImage();
        private Image cartonImg = new ImageIcon(this.getClass().getResource(PATH + "carton.png")).getImage();
        private Image crateImg = new ImageIcon(this.getClass().getResource(PATH + "crate.png")).getImage();
        private RecycleRemoteConnection connection;

        public void actionPerformed(ActionEvent e) {
            String buttonName = e.getActionCommand();
            if (buttonName.equals("Logout")){
                try {
                    this.connection.logout();
                    this.dispose();

                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            } else {
                ItemPropertiesGUI propertiesGUI = null;
                try {
                    propertiesGUI = new ItemPropertiesGUI(this.connection, buttonName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                propertiesGUI.setVisible(true);
            }
        }

        JButton bottle = new JButton("Bottle");
        JButton can = new JButton("Can");
        JButton crate = new JButton("Crate");
        JButton carton = new JButton("Carton");
        JButton logout = new JButton("Logout");

        private Image scaleDown(Image img){
            return img.getScaledInstance(50, 80, Image.SCALE_SMOOTH);
        }

        public StatusGUI(RecycleRemoteConnection caller) throws RemoteException {
            this.connection = caller;
            this.pack();
            this.setSize(640,800);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setTitle("Maintanance remotePanel");

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

            logout.setBounds(430, 500, 150, 100);
            logout.setIcon(new ImageIcon(scaleDown(receiptImg)));
            panel.add(logout);

            can.addActionListener(this);
            bottle.addActionListener(this);
            crate.addActionListener(this);
            carton.addActionListener(this);
            logout.addActionListener(this);

            getContentPane().add(panel);
            panel.repaint();
        }
}
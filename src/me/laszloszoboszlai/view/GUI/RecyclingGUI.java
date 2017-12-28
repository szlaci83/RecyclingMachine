package me.laszloszoboszlai.view.GUI;

import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.remote.RecycleRemoteConnectionRPC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
     * A Simple Graphical User Interface for the Recycling Machine.
     * @author Laszlo Szoboszlai
     *
     */
    public class RecyclingGUI extends JFrame implements ActionListener  {

    private static String PATH = "/me/laszloszoboszlai/img/";
    private String sessionCookie;
    public void setStatusGUI(StatusGUI statusGUI) {
        this.statusGUI = statusGUI;
    }

    public void setStatusGUIVisibility(boolean visibility){
        this.statusGUI.setVisible(visibility);
    }

    private StatusGUI statusGUI;
    RecycleRemoteConnection remoteConnection;

    private JLabel lblImageplaceholder = new JLabel("");
    private Image img = new ImageIcon(this.getClass().getResource(PATH + "recycle.png")).getImage();
    private Image canImg = new ImageIcon(this.getClass().getResource(PATH + "can.png")).getImage();
    private Image statusImg = new ImageIcon(this.getClass().getResource(PATH + "status.png")).getImage();
    private Image receiptImg = new ImageIcon(this.getClass().getResource(PATH + "receipt.png")).getImage();
    private Image bottleImg = new ImageIcon(this.getClass().getResource(PATH + "bottle.jpg")).getImage();
    private Image cartonImg = new ImageIcon(this.getClass().getResource(PATH + "carton.png")).getImage();
    private Image crateImg = new ImageIcon(this.getClass().getResource(PATH + "crate.png")).getImage();

    JButton bottle = new JButton("Bottle");
    JButton can = new JButton("Can");

    JButton crate = new JButton("Crate");
    JButton carton = new JButton("Carton");

    JButton status = new JButton("Status");
    JButton receipt = new JButton("Receipt");

    private Image scaleDown(Image img){
        return img.getScaledInstance(50, 80, Image.SCALE_SMOOTH);
    }

    public void actionPerformed(ActionEvent e) {
            String buttonName = e.getActionCommand();
            switch (buttonName){
                case "Bottle" :
                    try {
                        this.remoteConnection.classifyItem(2);
                    } catch (RemoteException e1) {
                        JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                        e1.printStackTrace();
                    }
                    break;
                case "Can" :
                    try {
                        this.remoteConnection.classifyItem(1);
                    } catch (RemoteException e1) {
                        JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                        e1.printStackTrace();
                    }
                    break;
                case "Crate" :
                    try {
                        this.remoteConnection.classifyItem(3);
                    } catch (RemoteException e1) {
                        JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                        e1.printStackTrace();
                    }
                    break;
                case "Carton" :
                    try {
                        this.remoteConnection.classifyItem(4);
                    } catch (RemoteException e1) {
                        JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                        e1.printStackTrace();
                    }
                    break;
                case "Status" : {
                        LoginGUI loginGUI = null;
                    System.out.println(sessionCookie);
                        if ( sessionCookie == null) {
                            loginGUI = new LoginGUI(this, sessionCookie);
                            loginGUI.setVisible(true);
                        }
                        else {
                            this.statusGUI.setVisible(true);
                        }
                }

                case "Receipt":
                    try {
                        this.remoteConnection.printReceipt();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error, couldn't connect to server!");
                    }
            }
        }

        public RecyclingGUI(String protocol) throws RemoteException {
            try {
                this.remoteConnection = connectToRemoteHost(protocol);
            } catch (NotBoundException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            } catch (MalformedURLException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            }
            this.pack();
            this.setSize(640,800);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Recycling machine 0.1");

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
            status.addActionListener(this);
            panel.add(status);

            can.addActionListener(this);
            bottle.addActionListener(this);
            crate.addActionListener(this);
            carton.addActionListener(this);
            receipt.addActionListener(this);

            getContentPane().add(panel);
            panel.repaint();
        }

    private RecycleRemoteConnection connectToRemoteHost(String protocol) throws RemoteException, NotBoundException, MalformedURLException {
        RecycleRemoteConnection connection = null;
        if (protocol.equals("RMI")) {
            connection = (RecycleRemoteConnection) Naming.lookup("rmi://localhost/RecycleService");
        }
        if (protocol.equals("XML-RPC")){
            connection = new RecycleRemoteConnectionRPC("localhost");
        }
        return connection;
    }

    public RecycleRemoteConnection getRemoteConnection() {
        return remoteConnection;
    }

}
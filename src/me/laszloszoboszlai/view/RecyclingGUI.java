package me.laszloszoboszlai.view;

import me.laszloszoboszlai.rmi.RecycleRMI;
import me.laszloszoboszlai.rmi.RecycleRmiImpl;
import me.laszloszoboszlai.service.CustomerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
     * A Simple Graphical User Interface for the Recycling Machine.
     * @author Laszlo Szoboszlai
     *
     */
    public class RecyclingGUI extends JFrame implements ActionListener  {

    private static String PATH = "/me/laszloszoboszlai/img/";

    private JLabel lblImageplaceholder = new JLabel("");
    private Image img = new ImageIcon(this.getClass().getResource(PATH + "recycle.png")).getImage();

    private Image canImg = new ImageIcon(this.getClass().getResource(PATH + "can.png")).getImage();
    private Image statusImg = new ImageIcon(this.getClass().getResource(PATH + "status.png")).getImage();
    private Image receiptImg = new ImageIcon(this.getClass().getResource(PATH + "receipt.png")).getImage();
    private Image bottleImg = new ImageIcon(this.getClass().getResource(PATH + "bottle.jpg")).getImage();
    private Image cartonImg = new ImageIcon(this.getClass().getResource(PATH + "carton.png")).getImage();
    private Image crateImg = new ImageIcon(this.getClass().getResource(PATH + "crate.png")).getImage();
    private CustomerPanel myCustomerPanel;



    JButton bottle = new JButton("Bottle");
    JButton can = new JButton("Can");

    JButton crate = new JButton("Crate");
    JButton carton = new JButton("Carton");

    JButton status = new JButton("Status");
    JButton receipt = new JButton("Receipt");


    public void actionPerformed(ActionEvent e) {
            String buttonName = e.getActionCommand();
            switch (buttonName){
                case "Bottle" :
                    myCustomerPanel.itemReceived(2);
                    break;
                case "Can" :
                    myCustomerPanel.itemReceived(1);
                    break;
                case "Crate" :
                    myCustomerPanel.itemReceived(3);
                    break;
                case "Carton" :
                    myCustomerPanel.itemReceived(4);
                    break;
                //case "Status" :
                    //myCustomerPanel.printStatus();
                  //  break;
                case "Receipt":
                    try {
                        myCustomerPanel.printReceipt();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

            }
        }

        private Image scaleDown(Image img){
        return img.getScaledInstance(50, 80, Image.SCALE_SMOOTH);
    }

        public RecyclingGUI() throws RemoteException {
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
            panel.add(status);

            can.addActionListener(this);
            bottle.addActionListener(this);
            crate.addActionListener(this);
            carton.addActionListener(this);

            receipt.addActionListener(this);

            status.addActionListener(ae -> {
                this.hide();
              //  JFrame login = new LoginGUI(this);
             //   login.setVisible(true);

            });


            getContentPane().add(panel);
            panel.repaint();

            myCustomerPanel
                    = new CustomerPanel(new Display());

            this.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    myCustomerPanel.closeConnection();
                }
            });
        }
    public CustomerPanel getPanel(){
        return this.myCustomerPanel;
    }


    public static void main(String [] args ) throws RemoteException {
            RecyclingGUI myGUI = new RecyclingGUI();
            myGUI.setVisible(true);

        // Starting up the RMI service:
        try {
            RecycleRmiImpl recycleImpl = new RecycleRmiImpl();
            recycleImpl.setPanel(myGUI.myCustomerPanel);

            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("RecycleService", (RecycleRMI) recycleImpl);
            System.out.println("Starting Recycling Service. Welcome to RMI!");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        }
    }
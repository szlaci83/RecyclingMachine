package me.laszloszoboszlai.view.GUI;

import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.utils.MD5Hasher;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

public class LoginGUI extends JFrame {
    private static String PATH = "/me/laszloszoboszlai/img/";
    private RecycleRemoteConnection connection;
    private JLabel lblImageplaceholder = new JLabel("");
    private Image img = new ImageIcon(this.getClass().getResource(PATH + "anon.png")).getImage();
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField userName = new JTextField(30);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField password = new JPasswordField(30);
    private JButton login = new JButton("Login");


    public LoginGUI(RecyclingGUI recyclingGUI, String cookie){
        this.connection = recyclingGUI.getRemoteConnection();
        this.pack();
     this.setSize(420,340);
     this.setLocationRelativeTo(null);

     this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
     this.setTitle("Login");

     JPanel panel = new JPanel();
     panel.setLayout(null);

     Image scaledBack = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
     lblImageplaceholder.setBounds(120, 0, 300, 200);
     lblImageplaceholder.setIcon(new ImageIcon(scaledBack));

     panel.add(lblImageplaceholder);

     nameLabel.setBounds(20,220,60,20);
     panel.add(nameLabel);

     passwordLabel.setBounds(20,260,80,20);
     panel.add(passwordLabel);

     userName.setBounds(120,220,160,25);
     panel.add(userName);

     password.setBounds(120,260,160,25);
     password.setEchoChar('*');
     panel.add(password);

     login.setBounds(300,220,80,40);
     panel.add(login);

     getContentPane().add(panel);
     panel.repaint();

        login.addActionListener(ae -> {
            this.setVisible(false);
            try {
                String result = this.connection.login(userName.getText(), MD5Hasher.getHash(new String(this.password.getPassword())));
                if (result.equals("wrong")) {
                    JOptionPane.showMessageDialog(this, "Wrong pass.");
                    this.setVisible(true);
                } else {
                    StatusGUI statusGUI = new StatusGUI(this.connection, result);
                    recyclingGUI.setStatusGUI(statusGUI);
                    recyclingGUI.setStatusGUIVisibility(true);
                }

            } catch (RemoteException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            }
        });
    }
}
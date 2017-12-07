package me.laszloszoboszlai.view;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    private static String PATH = "/me/laszloszoboszlai/img/";

    private JLabel lblImageplaceholder = new JLabel("");
    private Image img = new ImageIcon(this.getClass().getResource(PATH + "anon.png")).getImage();
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField userName = new JTextField(30);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField password = new JPasswordField(30);
    private JButton login = new JButton("Login");

 public LoginGUI(){
     this.pack();
     this.setSize(420,340);
     this.setLocationRelativeTo(null);

     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setTitle("Login");

     Image scaledBack = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
     lblImageplaceholder.setBounds(120, 0, 300, 200);
     lblImageplaceholder.setIcon(new ImageIcon(scaledBack));

     JPanel panel = new JPanel();
     panel.setLayout(null);

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

    }
    public static void main(String [] args ) {
        LoginGUI myGUI = new LoginGUI();
        myGUI.setVisible(true);
    }
}

package me.laszloszoboszlai.view.GUI;


import me.laszloszoboszlai.remote.RecycleRemoteConnection;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

public class AdminLoginGUI extends JFrame {
    private static String PATH = "/me/laszloszoboszlai/img/";

    private JLabel lblImageplaceholder = new JLabel("");
    private Image img = new ImageIcon(this.getClass().getResource(PATH + "anon.png")).getImage();
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField userName = new JTextField(30);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField password = new JPasswordField(30);
    private JButton login = new JButton("Login");
    private JLabel machineNoLabel = new JLabel("Machine:");
    //private JTextField machineNo = new JTextField(30);
    private RecycleRemoteConnection rmi;
    String[]machines = new String[] {"localhost", "127.0.0.1",
            "192.168.0.1", "Java for Dummies"};

    JComboBox<String> machineNo = new JComboBox<>(machines);

    public AdminLoginGUI() {
        this.pack();
        this.setSize(420, 340);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Admin Login");

        Image scaledBack = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        lblImageplaceholder.setBounds(120, 0, 300, 200);
        lblImageplaceholder.setIcon(new ImageIcon(scaledBack));

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(lblImageplaceholder);

        nameLabel.setBounds(20, 220, 60, 20);
        panel.add(nameLabel);

        passwordLabel.setBounds(20, 260, 80, 20);
        panel.add(passwordLabel);

        machineNoLabel.setBounds(20, 300, 80, 20);
        panel.add(machineNoLabel);

        userName.setBounds(120, 220, 160, 25);
        panel.add(userName);

        password.setBounds(120, 260, 160, 25);
        password.setEchoChar('*');
        panel.add(password);

        machineNo.setBounds(120, 300, 160, 25);
        panel.add(machineNo);

        login.setBounds(300, 220, 80, 40);
        panel.add(login);

        login.addActionListener(ae -> {
            this.hide();
            try {
                this.rmi = connectToRemoteHost((String) machineNo.getSelectedItem());
                System.out.println(userName.getText());
                System.out.println(new String(this.password.getPassword()));
                String result = this.rmi.login(userName.getText(), new String(this.password.getPassword()));
                if (result.equals("wrong")) {
                    JOptionPane.showMessageDialog(this, "Wrong pass.");
                    this.setVisible(false);
                }
                else{
                    this.setVisible(false);
                    ChartGUI chartGUI = new ChartGUI(this.rmi);
                    chartGUI.setVisible(true);
                    StatusGUI statusGUI = new StatusGUI(this.rmi);
                    statusGUI.setVisible(true);

                }
            } catch (RemoteException e) {
               // JOptionPane.showMessageDialog(this, "Couldn't connect to server");`
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                //JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            } catch (NotBoundException e) {
                //JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            } catch (MalformedURLException e) {
                //JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            }
        });

        getContentPane().add(panel);
        panel.repaint();

    }

    private RecycleRemoteConnection connectToRemoteHost(String host) throws RemoteException, NotBoundException, MalformedURLException {
       return (RecycleRemoteConnection) Naming.lookup("rmi://"+ host +"/RecycleService");
    }


    public static void main(String[] args) {
        AdminLoginGUI adminLoginGUI = new AdminLoginGUI();
        adminLoginGUI.setVisible(true);
    }
}
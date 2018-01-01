package me.laszloszoboszlai.view.GUI;


import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.remote.RecycleRemoteConnectionRPC;
import me.laszloszoboszlai.utils.ConnectionType;
import me.laszloszoboszlai.utils.MD5Hasher;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

/**
 * This is the login GUI for admins, the chosen machine can be selected to connect from a
 * dropdown list. If login is successful the StatusGUI and ChartGUI will open up.
 *
 * @author Laszlo Szoboszlai
 */
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
    private RecycleRemoteConnection connection;

    String[]machines = new String[] {"localhost", "127.0.0.1",
            "192.168.0.2", "192.168.0.3"};

    JComboBox<String> machineNo = new JComboBox<>(machines);

    public AdminLoginGUI(ConnectionType connectionType) {
        this.pack();
        this.setSize(420, 440);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            this.setVisible(false);
            try {
                this.connection = connectToRemoteHost(connectionType, (String) machineNo.getSelectedItem());
                String token = this.connection.login(userName.getText(), MD5Hasher.getHash(new String(this.password.getPassword())));
                if (token.equals("wrong")) {
                    JOptionPane.showMessageDialog(this, "Wrong password.");
                    this.setVisible(true);
                }
                else{
                    this.setVisible(false);
                    ChartGUI chartGUI = new ChartGUI(this.connection, token);
                    chartGUI.setVisible(true);
                    StatusGUI statusGUI = new StatusGUI(this.connection, token);
                    statusGUI.setVisible(true);
                }
            } catch (RemoteException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            } catch (NotBoundException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            } catch (MalformedURLException e) {
                JOptionPane.showMessageDialog(this, "Couldn't connect to server");
                e.printStackTrace();
            }
        });
        getContentPane().add(panel);
        panel.repaint();
    }

    /**
     * Sets the required type (XML-RPC/RMI) connection to the given remote server.
     * @param protocol the type of the protocol (XML-RPC/RMI)
     * @param host the remote host's IP address.
     * @return the right RecycleRemoteConnection implementation.
     * @throws RemoteException
     * @throws NotBoundException
     * @throws MalformedURLException
     */
    private RecycleRemoteConnection connectToRemoteHost(ConnectionType protocol, String host) throws RemoteException, NotBoundException, MalformedURLException {
        RecycleRemoteConnection connection = null;
        switch (protocol) {
            case RMI:
                connection = (RecycleRemoteConnection) Naming.lookup("rmi://" + host + "/RecycleService");
                break;
            case XML_RPC:
                connection = new RecycleRemoteConnectionRPC(host);
                break;
        }
        return connection;
    }
}
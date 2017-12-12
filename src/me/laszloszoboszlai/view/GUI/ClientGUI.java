package me.laszloszoboszlai.view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
/**
 * A Simple Graphical User Interface for the Recycling Machine.
 * @author Laszlo Szoboszlai
 *
 */

import org.apache.xmlrpc.XmlRpcClient;

public class ClientGUI extends JFrame implements ActionListener  {

    /**
     * Method to get the MD5 hash of a given String
     * @param word the word to be hashed
     * @return the hashed word
     */
    private String getHash(String word){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(word.getBytes());
	   byte[] digest = md.digest();
	   String hashed = DatatypeConverter
			   .printHexBinary(digest).toUpperCase();
	   return hashed;
    }

    private static String sessioncookie = "??";
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(items)) {
            try {
                XmlRpcClient server = new XmlRpcClient("http://localhost/RPC2"); //

                Vector args = new Vector();
                args.addElement(sessioncookie);
                Object result = server.execute("RecyclingServer.getNumberOfItems", args );

                if( result.toString().equals("-1")) {
                    System.out.println("Please login first");
                } else {
                    System.out.println("There are "+result.toString()+" items in the machine.");
                }

            } catch (Exception exception) {
                System.err.println("JavaClient: " + exception);
            }
        }
        else if(e.getSource().equals(login)){
            String message = JOptionPane.showInputDialog("Please enter your password");
            try {

                XmlRpcClient server = new XmlRpcClient("http://localhost/RPC2"); //
                Vector args  = new Vector();
                args.addElement(getHash(message)); // need to work on that!
                Object result = server.execute("RecyclingServer.login", args );

                if(result.equals("wrong")) {
                    System.out.println("Sorry, wrong password");
                } else {
                    System.out.println("Login successful. Please continue");
                    sessioncookie = result.toString();
                }


            } catch (Exception exception) {
                System.err.println("JavaClient: " + exception);
            }

        } else if( e.getSource().equals(logout)) {
            sessioncookie = "Reset"+Math.random();
            System.out.println("Logout. Have a good time!");
        } else if( e.getSource().equals(receipt))
        {
            System.out.println("This button does nothing. Thank you!");
        }

    }

    JButton items = new JButton("#items");
    JButton login = new JButton("Login");
    JButton logout = new JButton("Logout");

    JButton receipt = new JButton("Test?");

    public ClientGUI() {
        super();
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(items);
        panel.add(login);
        panel.add(logout);

        items.addActionListener(this);
        login.addActionListener(this);
        logout.addActionListener(this);

        panel.add(receipt);
        receipt.addActionListener(this);



        getContentPane().add(panel);
        panel.repaint();

    }

    public static void main(String [] args ) {
        ClientGUI myGUI = new ClientGUI();
        myGUI.setVisible(true);
    }

}
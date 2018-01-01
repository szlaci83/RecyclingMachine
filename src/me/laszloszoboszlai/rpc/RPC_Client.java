package me.laszloszoboszlai.rpc;

import me.laszloszoboszlai.utils.ConnectionType;
import me.laszloszoboszlai.view.GUI.RecyclingGUI;

import java.rmi.RemoteException;

/**
 * Main class to start the Customer/Maintenace interface for the XML-RPC connection
 * The main method creates the Recycling GUI for customers and maintenance person with XML-RPC connection type.
 *
 * @author Laszlo Szoboszlai
 */
public class RPC_Client {

    public static void main(String [] args ) throws RemoteException {
        RecyclingGUI recyclingGUI = new RecyclingGUI(ConnectionType.XML_RPC);
        recyclingGUI.setVisible(true);
    }
}

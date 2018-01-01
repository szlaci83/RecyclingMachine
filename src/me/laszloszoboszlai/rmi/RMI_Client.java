package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.utils.ConnectionType;
import me.laszloszoboszlai.view.GUI.RecyclingGUI;

import java.rmi.RemoteException;

/**
 * Main class to start the Customer/Maintenace interface for the RMI connection
 * The main method creates the Recycling GUI for customers and maintenance person with RMI connection type.
 *
 * @author Laszlo Szoboszlai
 */
public class RMI_Client {
    public static void main(String[] args) throws RemoteException {
        RecyclingGUI recyclingGUI = new RecyclingGUI(ConnectionType.RMI);
        recyclingGUI.setVisible(true);
    }
}
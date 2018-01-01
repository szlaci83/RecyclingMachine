package me.laszloszoboszlai.rpc;

import me.laszloszoboszlai.utils.ConnectionType;
import me.laszloszoboszlai.view.GUI.AdminLoginGUI;

import java.rmi.RemoteException;

/**
 * Main class to start the Admin interface for the XML-RPC connection
 * The main method creates the Login GUI for admins with XML-RPC connection type
 *
 * @author Laszlo Szoboszlai
 */
public class RPC_Admin {

    public static void main(String[] args) throws RemoteException {
        AdminLoginGUI adminGUI = new AdminLoginGUI(ConnectionType.XML_RPC);
        adminGUI.setVisible(true);
    }
}
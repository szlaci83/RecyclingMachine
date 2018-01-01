package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.utils.ConnectionType;
import me.laszloszoboszlai.view.GUI.AdminLoginGUI;

import java.rmi.RemoteException;

/**
 * Main class to start the Admin interface for the RMI connection
 * The main method creates the Login GUI for admins with RMI connection type
 *
 * @author Laszlo Szoboszlai
 */
public class RMI_Admin {
    public static void main(String [] args ) throws RemoteException {
        AdminLoginGUI adminGUI = new AdminLoginGUI(ConnectionType.RMI);
        adminGUI.setVisible(true);
    }
}

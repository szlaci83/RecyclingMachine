package me.laszloszoboszlai.rpc;

import me.laszloszoboszlai.view.GUI.AdminLoginGUI;

import java.rmi.RemoteException;

public class RPC_Admin {

    public static void main(String [] args ) throws RemoteException {
        AdminLoginGUI adminGUI = new AdminLoginGUI("XML-RPC");
        adminGUI.setVisible(true);
    }
}

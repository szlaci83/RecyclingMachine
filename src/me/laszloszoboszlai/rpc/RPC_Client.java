package me.laszloszoboszlai.rpc;

import me.laszloszoboszlai.view.GUI.RecyclingGUI;

import java.rmi.RemoteException;

public class RPC_Client {

    public static void main(String [] args ) throws RemoteException {
        RecyclingGUI recyclingGUI = new RecyclingGUI("XML-RPC");
        recyclingGUI.setVisible(true);
    }
}

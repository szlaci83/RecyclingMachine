package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.view.GUI.RecyclingGUI;

import java.rmi.RemoteException;

public class RMI_Client {
    public static void main(String [] args ) throws RemoteException {
    RecyclingGUI recyclingGUI = new RecyclingGUI("RMI");
    recyclingGUI.setVisible(true);
    }
}
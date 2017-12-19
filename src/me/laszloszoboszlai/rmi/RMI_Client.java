package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.view.GUI.RecyclingGUI;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMI_Client {

    public static void main(String [] args ) throws RemoteException {
        RecyclingGUI myGUI;
        try {
            RecycleRemoteConnection remoteConnection
                    = (RecycleRemoteConnection) Naming.lookup("rmi://localhost/RecycleService");

            myGUI = new RecyclingGUI(remoteConnection);
            myGUI.setVisible(true);

        } catch (Exception exception) {
            System.err.println("JavaClient: " + exception);
        }
    }
}

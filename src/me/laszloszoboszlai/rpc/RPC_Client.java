package me.laszloszoboszlai.rpc;

import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.remote.RecycleRemoteConnectionRPC;
import me.laszloszoboszlai.view.GUI.RecyclingGUI;

import java.rmi.RemoteException;

public class RPC_Client {

    public static void main(String [] args ) throws RemoteException {
        RecyclingGUI myGUI;
        RecycleRemoteConnection remoteConnection
                    = new RecycleRemoteConnectionRPC();
        myGUI = new RecyclingGUI(remoteConnection);
        myGUI.setVisible(true);
    }
}

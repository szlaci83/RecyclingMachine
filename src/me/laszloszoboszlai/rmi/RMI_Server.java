package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.controller.MaintanancePanel;
import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.remote.RecycleRemoteConnectionRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Server {

    public static void main(String[] args) throws RemoteException {
        MaintanancePanel maintanancePanel = new MaintanancePanel();
        try {
            RecycleRemoteConnectionRMI recycleImpl = new RecycleRemoteConnectionRMI();
            recycleImpl.setPanel(maintanancePanel);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("RecycleService", (RecycleRemoteConnection) recycleImpl);
            System.out.println("Starting Recycling Service. Welcome to RMI!");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
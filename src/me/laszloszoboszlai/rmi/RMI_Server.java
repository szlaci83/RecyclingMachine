package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.controller.MaintenancePanel;
import me.laszloszoboszlai.remote.RecycleRemoteConnection;
import me.laszloszoboszlai.remote.RecycleRemoteConnectionRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Server {

    public static void main(String[] args) throws RemoteException {
        MaintenancePanel maintenancePanel = new MaintenancePanel();
        try {
            RecycleRemoteConnectionRMI recycleImpl = new RecycleRemoteConnectionRMI();
            recycleImpl.setPanel(maintenancePanel);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("RecycleService", (RecycleRemoteConnection) recycleImpl);
            System.out.println("Starting Recycling Service. Welcome to RMI!");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.remote.RecycleRemoteConnectionRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Main class to start the server for the RMI connection
 * The main method creates the server with RMI protocol on port 1099 and starts it.
 *
 * @author Laszlo Szoboszlai
 */
public class RMI_Server {

    public static void main(String[] args) throws RemoteException {
        try {
            RecycleRemoteConnectionRMI recycleImpl = new RecycleRemoteConnectionRMI();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("RecycleService", recycleImpl);
            System.out.println("Starting Recycling Service. Welcome to RMI!");
        } catch (Exception e) {
            System.out.println("RMI server: " + e);
        }
    }
}
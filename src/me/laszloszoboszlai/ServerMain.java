package me.laszloszoboszlai;

import me.laszloszoboszlai.rmi.RecycleRMI;
import me.laszloszoboszlai.rmi.RecycleRmiImpl;
import me.laszloszoboszlai.service.CustomerPanel;
import me.laszloszoboszlai.view.Display;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    public static void main(String[] args) throws RemoteException {
        CustomerPanel customerPanel = new CustomerPanel(new Display());
        try {
            RecycleRmiImpl recycleImpl = new RecycleRmiImpl();
            recycleImpl.setPanel(customerPanel);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("RecycleService", (RecycleRMI) recycleImpl);
            System.out.println("Starting Recycling Service. Welcome to RMI!");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
package me.laszloszoboszlai;

import me.laszloszoboszlai.controller.MaintanancePanel;
import me.laszloszoboszlai.rmi.RecycleRMI;
import me.laszloszoboszlai.rmi.RecycleRmiImpl;
import me.laszloszoboszlai.controller.CustomerPanel;
import me.laszloszoboszlai.view.Display;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServerMain {

    public static void main(String[] args) throws RemoteException {
        MaintanancePanel maintanancePanel = new MaintanancePanel();
        try {
            RecycleRmiImpl recycleImpl = new RecycleRmiImpl();
            recycleImpl.setPanel(maintanancePanel);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("RecycleService", (RecycleRMI) recycleImpl);
            System.out.println("Starting Recycling Service. Welcome to RMI!");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
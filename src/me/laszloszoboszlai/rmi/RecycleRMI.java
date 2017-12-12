package me.laszloszoboszlai.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface RecycleRMI extends Remote {
    Map<String, Long> getStatus() throws IOException, RemoteException;
    void emptySlot(int slot)throws IOException, RemoteException;
    void changeItemValue(String name, int value) throws RemoteException;
    public String login(String userName, String password) throws  RemoteException;
    public boolean isLoggedIn() throws RemoteException;
    public int getItemValue(String name) throws RemoteException;
}

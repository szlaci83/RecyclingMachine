package me.laszloszoboszlai.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface RecycleRMI extends Remote {
    Map<String, Long> getStatus() throws IOException, RemoteException;
    void emptySlot(int slot)throws IOException, RemoteException;
    void changeItemValue(String name, int value) throws RemoteException;
    public String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException;
    public boolean isLoggedIn() throws RemoteException;
    public int getItemValue(String name) throws RemoteException;
    Map<String, Long> getCapacity()throws IOException;
    void setCapacity(String name, long capacity) throws IOException;
    void classifyItem(int itemNumber)throws RemoteException;
    void printReceipt() throws IOException;
}

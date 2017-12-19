package me.laszloszoboszlai.remote;

import org.bson.Document;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public interface RecycleRemoteConnection extends Remote {
    Map getStatus() throws IOException, RemoteException;
    boolean emptySlot(int slot)throws IOException, RemoteException;
    boolean changeItemValue(String name, int value) throws RemoteException;
    String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException;
    boolean isLoggedIn() throws RemoteException;
    int getItemValue(String name) throws RemoteException;
    Map getCapacity()throws IOException;
    boolean setCapacity(String name, long capacity) throws IOException;
    boolean classifyItem(int itemNumber)throws RemoteException;
    boolean printReceipt() throws IOException;
    boolean closeConnection()throws RemoteException;
    Vector<String> getUsage(String from, String to) throws IOException;
}

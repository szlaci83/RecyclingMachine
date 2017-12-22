package me.laszloszoboszlai.remote;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Vector;

/**
 * Interface to be implemented by the different implementations of
 * (XML-RPC and RMI) connection classes to handle remote calls for the Recycle Machine.
 * The different implementations implement this common interface, so they can be injected on demand.
 *
 * @author Laszlo Szoboszlai
 */
public interface RecycleRemoteConnection extends Remote {
    Map getStatus() throws IOException, RemoteException;
    boolean emptySlot(int slot)throws IOException, RemoteException;
    boolean changeItemValue(String name, int value) throws RemoteException;
    String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException;
    boolean isLoggedIn(String username) throws RemoteException;
    int getItemValue(String name) throws RemoteException;
    Map getCapacity()throws IOException;
    boolean setCapacity(String name, long capacity) throws IOException;
    boolean classifyItem(int itemNumber)throws RemoteException;
    boolean printReceipt() throws IOException;
    boolean closeConnection()throws RemoteException;
    Vector<String> getUsage(String from, String to) throws IOException;
    boolean logout()throws RemoteException;
}
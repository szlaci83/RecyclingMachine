package me.laszloszoboszlai.remote;

import me.laszloszoboszlai.exception.NotLoggedInException;

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
    Map getStatus(String token) throws IOException, NotLoggedInException;
    boolean emptySlot(String token, int slot)throws IOException, NotLoggedInException;
    boolean changeItemValue(String token, String name, int value) throws RemoteException, NotLoggedInException;
    String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException;
    int getItemValue(String token, String name) throws RemoteException, NotLoggedInException;
    Map getCapacity(String token)throws IOException, NotLoggedInException;
    boolean setCapacity(String token, String name, long capacity) throws IOException, NotLoggedInException;
    boolean classifyItem(int itemNumber)throws RemoteException;
    boolean printReceipt() throws IOException;
    Vector<String> getUsage(String token, String from, String to) throws IOException, NotLoggedInException;
    boolean logout(String username)throws RemoteException,NotLoggedInException;
}
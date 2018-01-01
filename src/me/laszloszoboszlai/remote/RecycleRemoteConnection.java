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
 * The different implementations can be injected on demand as they implement this common interface.
 *
 * @author Laszlo Szoboszlai
 */
public interface RecycleRemoteConnection extends Remote {
    /**
     * Returns the status of the machine (How full it is)
     *
     * @param token the user-token to authenticate the user beforehand
     * @return Map of the status of the machine (ItemName ->  number of items deposited)
     * @throws IOException
     */
    Map getStatus(String token) throws IOException;

    /**
     * Empties a given slot
     *
     * @param token the user-token to authenticate the user beforehand
     * @param slot  the slot number to be emptied
     * @return true if the operation was successful
     * @throws IOException
     */
    boolean emptySlot(String token, int slot) throws IOException;

    /**
     * Changes the value of a given item
     *
     * @param token the user-token to authenticate the user beforehand
     * @param name  name of th item, the value of which to be changed
     * @param value the new value of the item
     * @return true if the operation was successful
     * @throws RemoteException
     */
    boolean changeItemValue(String token, String name, int value) throws RemoteException;

    /**
     * Logs in a user with a given username and password
     *
     * @param userName the name of the user
     * @param password the password of the user
     * @return "wrong" if the login process failed, user-token otherwise
     * @throws RemoteException
     * @throws NoSuchAlgorithmException
     */
    String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException;

    /**
     * Returns the value of an item.
     *
     * @param token the user-token to authenticate the user beforehand
     * @param name  name of the item, the value of which to be returned
     * @return true if the operation was successful
     * @throws RemoteException
     */
    int getItemValue(String token, String name) throws RemoteException;

    /**
     * Returns the capacity of the items' slots as a map
     *
     * @param token the user-token to authenticate the user beforehand
     * @return Map of the items' capacity  (item -> capacity map)
     * @throws IOException
     */
    Map getCapacity(String token) throws IOException;

    /**
     * Sets the capacity of a given item
     *
     * @param token    the user-token to authenticate the user beforehand
     * @param name     the name of the item
     * @param capacity the new capcity value
     * @return true if the operation was successful
     * @throws IOException
     */
    boolean setCapacity(String token, String name, long capacity) throws IOException;

    /**
     * Classifies an item inserted to the machine's slot
     *
     * @param itemNumber the number of the item's slot
     * @return true if the operation was successful
     * @throws RemoteException
     */
    boolean classifyItem(int itemNumber) throws RemoteException;

    /**
     * Prints the receipt of the deposited items since the last receipt printed.
     *
     * @return true if the operation was successful
     * @throws IOException
     */
    boolean printReceipt() throws IOException;

    /**
     * Gets the usage information of the machine for a given date range
     *
     * @param token the user-token to authenticate the user beforehand
     * @param from  the date the usage data is required from
     * @param to    the date the usage data is required to
     * @return Vector of Strings in a json format of usage data
     * @throws IOException
     */
    Vector<String> getUsage(String token, String from, String to) throws IOException;

    /**
     * Logs a given user out
     *
     * @param username the name of the user to be logged out.
     * @return true if the operation was successful
     * @throws RemoteException
     */
    boolean logout(String username) throws RemoteException;
}
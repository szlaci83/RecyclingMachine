package me.laszloszoboszlai.remote;

import org.apache.xmlrpc.XmlRpcClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Vector;

/**
 * XML-RPC implementation of the RecycleRemoteConnection. Can be injected to the system to handle XLM-RPC connections
 * between the server and the clients. Calls the relevant methods with the received parameters.
 * @see RecycleRemoteConnection
 *
 * @author Laszlo Szoboszlai
 */
public class RecycleRemoteConnectionRPC implements RecycleRemoteConnection {

    // The ip of the Recycling machine server
    static String HOST = "http://127.0.0.1/RPC2";
    static XmlRpcClient server;

    public RecycleRemoteConnectionRPC() {

    }

    public RecycleRemoteConnectionRPC(String HostName) {
        HOST = "http://" + HostName + "/RPC2";
        try {
            server = new XmlRpcClient(HOST);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to call methods remotely using XML-RPC
     *
     * @param methodName the name of the method to call
     * @param params     the parameters for the method
     * @return the result of the method call
     */
    private static Object executeRemotely(String methodName, Vector params) {
        try {
            Object result = server.execute(methodName, params);
            return result;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


    @Override
    public Map<String, Long> getStatus(String token) throws IOException {
        Vector params = new Vector();
        params.add(token);
        return (Map<String, Long>) executeRemotely("RecyclingMaintanance.getStatus", params);
    }

    @Override
    public boolean emptySlot(String token, int slot) throws IOException {
        Vector params = new Vector();
        params.add(token);
        params.add(slot);
        executeRemotely( "RecyclingMaintanance.emptySlot", params);
        return true;
    }

    @Override
    public boolean changeItemValue(String token, String name, int value) throws RemoteException {
        Vector params = new Vector();
        params.add(token);
        params.add(name);
        params.add(value);
        executeRemotely("RecyclingMaintanance.changeItemValue", params);
        return true;
    }

    @Override
    public String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException {
        Vector params = new Vector();
        params.add(userName);
        params.add(password);
        return (String) executeRemotely("RecyclingLogin.login", params);
    }

    @Override
    public int getItemValue(String token, String name) throws RemoteException {
        Vector params = new Vector();
        params.add(token);
        params.add(name);
        return (Integer) executeRemotely("RecyclingMaintanance.getItemValue", params);
    }

    @Override
    public Map<String, Long> getCapacity(String token) throws IOException {
        Vector params = new Vector();
        params.add(token);
        return (Map<String, Long>) executeRemotely("RecyclingMaintanance.getCapacity", params);
    }

    @Override
    public boolean setCapacity(String token, String name, long capacity) throws IOException {
        Vector params = new Vector();
        params.add(token);
        params.add(name);
        params.add(String.valueOf(capacity));
        executeRemotely("RecyclingMaintanance.setCapacity", params);
        return true;
    }

    @Override
    public boolean classifyItem(int itemNumber) throws RemoteException {
        Vector params = new Vector();
        params.add(itemNumber);
        executeRemotely("RecyclingCustomer.classifyItem", params);
        return true;
    }

    @Override
    public boolean printReceipt() throws IOException {
        Vector params = new Vector();
        executeRemotely("RecyclingCustomer.printReceipt", params);
        return true;
    }

    @Override
    public Vector<String> getUsage(String token, String from, String to) throws IOException {
        Vector params = new Vector();
        params.add(token);
        params.add(from);
        params.add(to);
        return (Vector<String>) executeRemotely("RecyclingMaintanance.getUsage", params);
    }

    @Override
    public boolean logout(String username) {
        Vector params = new Vector();
        params.add(username);
        return (boolean) executeRemotely("RecyclingLogin.logout", params);
    }
}
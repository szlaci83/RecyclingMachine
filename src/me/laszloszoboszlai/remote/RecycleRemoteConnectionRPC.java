package me.laszloszoboszlai.remote;

import org.apache.xmlrpc.XmlRpcClient;
import org.bson.Document;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.Vector;

public class RecycleRemoteConnectionRPC implements RecycleRemoteConnection{

    // The ip of the Recycling machine server
    static String HOST = "http://127.0.0.1/RPC2";

    /**
     * Method to call methods remotely using RPC
     * @param url the server's URL
     * @param methodName the name of the method to call
     * @param params the parameters for the method
     * @return the result of the method call
     */
    private static Object executeRemotely(String url, String methodName, Vector params){
        try {
            XmlRpcClient server = new XmlRpcClient(url);
            Object result = server.execute(methodName, params);
            return result;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


    @Override
    public Map<String, Long> getStatus() throws IOException, RemoteException {
        return (Map<String, Long>) executeRemotely(HOST, "RecyclingMaintanance.getStatus", new Vector());
    }

    @Override
    public boolean emptySlot(int slot) throws IOException, RemoteException {
        Vector params = new Vector();
        params.add(slot);
        executeRemotely(HOST, "RecyclingMaintanance.emptySlot", params);
        return true;
    }

    @Override
    public boolean changeItemValue(String name, int value) throws RemoteException {
        Vector params = new Vector();
        params.add(name);
        params.add(value);
        executeRemotely(HOST, "RecyclingMaintanance.changeItemValue", params);
        return true;
    }

    @Override
    public String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException {
        return null;
    }

    @Override
    public boolean isLoggedIn(String username) throws RemoteException {
        return true;
    }

    @Override
    public int getItemValue(String name) throws RemoteException {
        Vector params = new Vector();
        params.add(name);
        return (Integer) executeRemotely(HOST, "RecyclingMaintanance.getItemValue", params);
    }

    @Override
    public Map<String, Long> getCapacity() throws IOException {
        return (Map<String, Long>) executeRemotely(HOST, "RecyclingMaintanance.getCapacity", new Vector());
    }

    @Override
    public boolean setCapacity(String name, long capacity) throws IOException {
        Vector params = new Vector();
        params.add(name);
        params.add(String.valueOf(capacity));
        executeRemotely(HOST, "RecyclingMaintanance.setCapacity", params);
        return true;
    }

    @Override
    public boolean classifyItem(int itemNumber) throws RemoteException {
        Vector params = new Vector();
        params.add(itemNumber);
        executeRemotely(HOST, "RecyclingCustomer.classifyItem", params);
        return true;
    }

    @Override
    public boolean printReceipt() throws IOException {
        Vector params = new Vector();
        executeRemotely(HOST, "RecyclingCustomer.printReceipt", params);
        return true;
    }

    @Override
    public boolean closeConnection() throws RemoteException {
        Vector params = new Vector();
        executeRemotely(HOST, "RecyclingMaintanance.closeConnection", params);
        return true;
    }

    @Override
    public Vector<String> getUsage(String from, String to) throws IOException {
        Vector params = new Vector();
        params.add(from);
        params.add(to);
        return (Vector<String>) executeRemotely(HOST, "RecyclingMaintanance.getUsage", params);
    }

    @Override
    public boolean logout() {
return true;
    }

    public static void main(String[] args) {
        RecycleRemoteConnectionRPC conn = new RecycleRemoteConnectionRPC();
        try {
            System.out.println(conn.getStatus());
            System.out.println(conn.getCapacity());
            conn.emptySlot(1);
            System.out.println(conn.getUsage("0","0"));
            System.out.println(conn.emptySlot(1));
            System.out.println(conn.changeItemValue("Can", 1));
            System.out.println(conn.getItemValue("Bottle"));
            System.out.println(conn.classifyItem(1));
            System.out.println(conn.printReceipt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

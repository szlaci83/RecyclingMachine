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

/**
 * XML-RPC implementation of the RecycleRemoteConnection. Can be injected to the system to handle XLM-RPC connections
 * between the server and the clients. Calls the relevant methods with the received parameters.
 *
 * @author Laszlo Szoboszlai
 */
public class RecycleRemoteConnectionRPC implements RecycleRemoteConnection{

    // The ip of the Recycling machine server
    static String HOST = "http://127.0.0.1/RPC2";

    public RecycleRemoteConnectionRPC() {

    }

    public RecycleRemoteConnectionRPC(String HostName) {
      HOST = "http://" + HostName + "/RPC2";
    }

    /**
     * Helper method to call methods remotely using RPC
     * @param url the server's URL
     * @param methodName the name of the method to call
     * @param params the parameters for the method
     * @return the result of the method call
     */
    private static Object executeRemotely(String url, String methodName, Vector params){
        try {
            System.out.println(url);
            XmlRpcClient server = new XmlRpcClient(url);
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
        return (Map<String, Long>) executeRemotely(HOST, "RecyclingMaintanance.getStatus", params);
    }

    @Override
    public boolean emptySlot(String token, int slot) throws IOException {
        Vector params = new Vector();
        params.add(token);
        params.add(slot);
        executeRemotely(HOST, "RecyclingMaintanance.emptySlot", params);
        return true;
    }

    @Override
    public boolean changeItemValue(String token, String name, int value) throws RemoteException {
        Vector params = new Vector();
        params.add(token);
        params.add(name);
        params.add(value);
        executeRemotely(HOST, "RecyclingMaintanance.changeItemValue", params);
        return true;
    }

    @Override
    public String login(String userName, String password) throws RemoteException, NoSuchAlgorithmException {
        Vector params = new Vector();
        params.add(userName);
        params.add(password);
        return(String) executeRemotely(HOST, "RecyclingLogin.login", params);
    }

    @Override
    public boolean isLoggedIn(String username) throws RemoteException {
        Vector params = new Vector();
        params.add(username);
        return(boolean) executeRemotely(HOST, "RecyclingLogin.isLoggedIn", params);
    }

    @Override
    public int getItemValue(String token,String name) throws RemoteException {
        Vector params = new Vector();
        params.add(token);
        params.add(name);
        return (Integer) executeRemotely(HOST, "RecyclingMaintanance.getItemValue", params);
    }

    @Override
    public Map<String, Long> getCapacity(String token) throws IOException {
        Vector params = new Vector();
        params.add(token);
        return (Map<String, Long>) executeRemotely(HOST, "RecyclingMaintanance.getCapacity", params);
    }

    @Override
    public boolean setCapacity(String token, String name, long capacity) throws IOException {
        Vector params = new Vector();
        params.add(token);
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
    public boolean closeConnection(String token) throws RemoteException {
        Vector params = new Vector();
        params.add(token);
        executeRemotely(HOST, "RecyclingMaintanance.closeConnection", params);
        return true;
    }

    @Override
    public Vector<String> getUsage(String token, String from, String to) throws IOException {
        Vector params = new Vector();
        params.add(token);
        params.add(from);
        params.add(to);
        return (Vector<String>) executeRemotely(HOST, "RecyclingMaintanance.getUsage", params);
    }

    @Override
    public boolean logout(String username) {
        Vector params = new Vector();
        params.add(username);
        return(boolean) executeRemotely(HOST, "RecyclingLogin.logout", params);
    }
//
//    public static void main(String[] args) {
//        RecycleRemoteConnectionRPC conn = new RecycleRemoteConnectionRPC();
//        try {
//            System.out.println(conn.getStatus());
//            System.out.println(conn.getCapacity());
//            conn.emptySlot(1);
//            System.out.println(conn.getUsage("0","0"));
//            System.out.println(conn.emptySlot(1));
//            System.out.println(conn.changeItemValue("Can", 1));
//            System.out.println(conn.getItemValue("Bottle"));
//            System.out.println(conn.classifyItem(1));
//            System.out.println(conn.printReceipt());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
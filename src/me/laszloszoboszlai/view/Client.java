package me.laszloszoboszlai.view;

import java.util.*;
import org.apache.xmlrpc.*;

/**
 * Simple client for the Recycling machine, using RPC calls to call different methods remotely.
 */
public class Client {
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
            System.err.println("JavaClient: " + exception);
        }
        return null;
    }

    /**
     * Method to call the classifyItem remote method on the server with different parameters.
     * @param i the number of the item recieved
     */
    private static void classify(int i){
        Vector p = new Vector();
        p.add(i);
        executeRemotely(HOST, "RecyclingServer.classifyItem",p );
    }

    /**
     * Method to call the printReceipt remote method on the server
     */
    private static void printReceipt(){
        executeRemotely(HOST, "RecyclingServer.printReceipt",new Vector() );
    }

    public static void main(String[] args) {
        // Some test calls to the remote server.
        classify(1);
        classify(2);
        classify(3);
        classify(4);
        printReceipt();
    }

}
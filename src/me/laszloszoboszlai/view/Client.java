package me.laszloszoboszlai.view;

import java.util.*;
import org.apache.xmlrpc.*;

public class Client {
    static String HOST = "http://127.0.0.1/RPC2";


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

    private static void classify(int i){
        Vector p = new Vector();
        p.add(i);
        executeRemotely(HOST, "RecyclingServer.classifyItem",p );
    }

    private static void printReceipt(){
        executeRemotely(HOST, "RecyclingServer.printReceipt",new Vector() );
    }

    public static void main(String[] args) {
        classify(1);
        classify(2);
        classify(3);
        classify(4);
        printReceipt();
    }

}
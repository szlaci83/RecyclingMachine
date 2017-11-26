package me.laszloszoboszlai.utils;

import java.util.*;
import org.apache.xmlrpc.*;

public class HelloClient {
    public static void main (String [] args) {
        try {

            XmlRpcClient server = new XmlRpcClient("http://127.0.0.1/RPC2"); //

            Object result = server.execute("sample.hello", new Vector() );

            System.out.println("The result is: "+result.toString());

        } catch (Exception exception) {
            System.err.println("JavaClient: " + exception);
        }
    }
}
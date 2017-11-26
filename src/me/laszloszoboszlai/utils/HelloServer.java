package me.laszloszoboszlai.utils;

import org.apache.xmlrpc.*;

public class HelloServer {
    public String hello() {
        return "Hello from the guy who does the demonstration.";
    }

    public static void main (String [] args) {
        try {
            System.out.println("Starting the Server...");
            WebServer server = new WebServer(80);
            server.addHandler("sample", new HelloServer());
            server.start();
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }
}
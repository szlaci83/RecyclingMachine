package me.laszloszoboszlai;

import org.apache.xmlrpc.WebServer;

public class Server {

    public static void main (String [] args) {
        try {
            System.out.println("Starting the Server...");
            WebServer server = new WebServer(80);
            server.addHandler("sample", new Server());
            server.start();
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }
}
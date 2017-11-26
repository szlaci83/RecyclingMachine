package me.laszloszoboszlai.view;

import me.laszloszoboszlai.controller.DepositItemReceiver;
import me.laszloszoboszlai.controller.DepositItemReceiverInterface;
import org.apache.xmlrpc.*;

public class Server {

    public static void main (String [] args) {
        DepositItemReceiverInterface depositItemReceiver = new DepositItemReceiver(new ReceiptPrinter());


        try {
            System.out.println("Starting the Recycling Server...");
            WebServer server = new WebServer(80);
            server.addHandler("RecyclingServer", depositItemReceiver);
            server.start();
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }
}
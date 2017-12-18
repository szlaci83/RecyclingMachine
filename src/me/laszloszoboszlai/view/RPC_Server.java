package me.laszloszoboszlai.view;

import me.laszloszoboszlai.service.DepositItemReceiver;
import me.laszloszoboszlai.service.DepositItemReceiverInterface;
import org.apache.xmlrpc.*;

import java.rmi.RemoteException;

public class RPC_Server {

    public static void main (String [] args) throws RemoteException {
        DepositItemReceiverInterface depositItemReceiver = new DepositItemReceiver(new ReceiptPrinter());


        try {
            System.out.println("Starting the Recycling RPC_Server...");
            WebServer server = new WebServer(80);
            server.addHandler("RecyclingServer", depositItemReceiver);
            server.start();
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }
}
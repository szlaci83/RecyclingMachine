package me.laszloszoboszlai.rpc;

import me.laszloszoboszlai.controller.CustomerPanel;
import me.laszloszoboszlai.controller.LoginPanel;
import me.laszloszoboszlai.controller.MaintenancePanel;
import me.laszloszoboszlai.view.Display;
import org.apache.xmlrpc.WebServer;

import java.rmi.RemoteException;

/**
 * Main class to start the server for the XML-RPC connection
 * The main method creates the server with XML-RPC protocol on port 80 and adds the
 * MaintenancePanel, CustomerPanel and LoginPanel as handlers and starts it.
 *
 * @author Laszlo Szoboszlai
 */
public class RPC_Server {
    public static void main(String[] args) throws RemoteException {
        MaintenancePanel maintenancePanel = new MaintenancePanel();
        CustomerPanel customerPanel = new CustomerPanel(new Display());
        LoginPanel loginPanel = new LoginPanel();

        try {
            System.out.println("Starting the Recycling RPC_Server...");
            WebServer server = new WebServer(80);
            server.addHandler("RecyclingMaintanance", maintenancePanel);
            server.addHandler("RecyclingCustomer", customerPanel);
            server.addHandler("RecyclingLogin", loginPanel);
            server.start();
        } catch (Exception exception) {
            System.err.println("XML-RPC Server: " + exception);
        }
    }
}

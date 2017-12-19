package me.laszloszoboszlai.rpc;

import me.laszloszoboszlai.controller.CustomerPanel;
import me.laszloszoboszlai.controller.LoginPanel;
import me.laszloszoboszlai.controller.MaintanancePanel;
import me.laszloszoboszlai.view.Display;
import org.apache.xmlrpc.WebServer;

import java.rmi.RemoteException;

public class RPC_Server {
    public static void main(String[] args) throws RemoteException {
        MaintanancePanel maintanancePanel = new MaintanancePanel();
        CustomerPanel customerPanel = new CustomerPanel(new Display());
        LoginPanel loginPanel = new LoginPanel();

        try {
            System.out.println("Starting the Recycling RPC_Server...");
            WebServer server = new WebServer(80);
            server.addHandler("RecyclingMaintanance", maintanancePanel);
            server.addHandler("RecyclingCustomer", customerPanel);
            server.addHandler("RecyclingLogin", loginPanel);
            server.start();
        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception);
        }
    }
}

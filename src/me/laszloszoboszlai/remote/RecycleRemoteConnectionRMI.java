package me.laszloszoboszlai.remote;

import me.laszloszoboszlai.controller.CustomerPanel;
import me.laszloszoboszlai.controller.LoginPanel;
import me.laszloszoboszlai.controller.MaintanancePanel;
import me.laszloszoboszlai.view.Display;
import org.bson.Document;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

/**
 * RMI implementation of the RecycleRemoteConnection. Can be injected to the system to handle RMI connections
 * between the server and the clients. Has instances of the Maintenance, Login and customer panels, and passes
 * the method calls to the relevant panel.
 *
 * @author Laszlo Szoboszlai
 */
public class RecycleRemoteConnectionRMI extends UnicastRemoteObject implements RecycleRemoteConnection {

    private static final long serialVersionUID = 1L;
    private MaintanancePanel maintanancePanel;
    private CustomerPanel customerPanel = new CustomerPanel(new Display());
    private LoginPanel loginPanel = new LoginPanel();


    public RecycleRemoteConnectionRMI() throws RemoteException {
        super();

    }

    public RecycleRemoteConnectionRMI(int arg0) throws RemoteException {
        super(arg0);
    }

    /**
     * Sets the maintenance panel of the
     * recycling machine, because this part is machine dependent this is set after initialisation.
     * @param thePanel the maintenance panel of the system.
     */
    public void setPanel( MaintanancePanel thePanel ) {
        maintanancePanel = thePanel;
    }

    @Override
    public Map<String, String> getStatus() throws IOException {
        return maintanancePanel.getStatus();
    }

    @Override
    public boolean emptySlot(int slot) throws IOException {
        maintanancePanel.emptySlot(slot);
        return true;
    }

    @Override
    public boolean changeItemValue(String name, int value) {
        maintanancePanel.changeItemValue(name, value);
        return true;
    }

    @Override
    public String login(String userName, String password) throws NoSuchAlgorithmException {
       return loginPanel.login(userName, password);
    }

    @Override
    public boolean isLoggedIn(String username) throws RemoteException {
        return loginPanel.isLoggedIn(username);
    }

    @Override
    public int getItemValue(String name) throws RemoteException {
        return maintanancePanel.getItemValue(name);
    }

    @Override
    public Map<String, String> getCapacity() throws RemoteException{
       return maintanancePanel.getCapacity();
    }

    @Override
    public boolean setCapacity(String name, long capaity) throws IOException {
        maintanancePanel.setCapacity(name, capaity);
        return true;
    }

    @Override
    public boolean classifyItem(int slot) {
        customerPanel.classifyItem(slot);
        return true;
    }

    @Override
    public boolean printReceipt() throws IOException {
        customerPanel.printReceipt();
        return true;
    }

    @Override
    public boolean closeConnection() {
        maintanancePanel.closeConnection();
        return true;
    }

    @Override
    public Vector<String> getUsage(String from, String to) throws IOException {
        return maintanancePanel.getUsage(from, to);
    }

    @Override
    public boolean logout(String username) {
        loginPanel.logout(username);
        return true;
    }
}
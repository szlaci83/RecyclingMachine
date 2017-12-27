package me.laszloszoboszlai.remote;

import me.laszloszoboszlai.controller.CustomerPanel;
import me.laszloszoboszlai.controller.LoginPanel;
import me.laszloszoboszlai.controller.MaintenancePanel;
import me.laszloszoboszlai.exception.NotLoggedInException;
import me.laszloszoboszlai.view.Display;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
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
    private MaintenancePanel maintenancePanel;
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
    public void setPanel( MaintenancePanel thePanel ) {
        maintenancePanel = thePanel;
    }

    @Override
    public Map<String, String> getStatus(String token) throws IOException, NotLoggedInException {
        return maintenancePanel.getStatus(token);
    }

    @Override
    public boolean emptySlot(String token, int slot) throws IOException, NotLoggedInException {
        maintenancePanel.emptySlot(token, slot);
        return true;
    }

    @Override
    public boolean changeItemValue(String token, String name, int value) throws NotLoggedInException {
        maintenancePanel.changeItemValue(token, name, value);
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
    public int getItemValue(String token, String name) throws RemoteException, NotLoggedInException {
        return maintenancePanel.getItemValue(token, name);
    }

    @Override
    public Map<String, String> getCapacity(String token) throws RemoteException, NotLoggedInException {
       return maintenancePanel.getCapacity(token);
    }

    @Override
    public boolean setCapacity(String token, String name, long capaity) throws IOException, NotLoggedInException {
        maintenancePanel.setCapacity(token, name, capaity);
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
    public boolean closeConnection(String token) throws NotLoggedInException {
        maintenancePanel.closeConnection(token);
        return true;
    }

    @Override
    public Vector<String> getUsage(String token, String from, String to) throws IOException, NotLoggedInException {
        return maintenancePanel.getUsage(token, from, to);
    }

    @Override
    public boolean logout(String username) {
        loginPanel.logout(username);
        return true;
    }
}
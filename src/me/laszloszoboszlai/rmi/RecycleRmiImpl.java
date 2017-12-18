package me.laszloszoboszlai.rmi;

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

public class RecycleRmiImpl extends UnicastRemoteObject implements RecycleRMI{

    private static final long serialVersionUID = 1L;
    private MaintanancePanel maintanancePanel;
    private CustomerPanel customerPanel = new CustomerPanel(new Display());
    private LoginPanel loginPanel = new LoginPanel();


    public RecycleRmiImpl() throws RemoteException {
        super();

    }

    public RecycleRmiImpl(int arg0) throws RemoteException {
        super(arg0);
        // TODO Auto-generated constructor stub
    }


    /**
     * initialise implementation with the customer panel of the
     * recycling machine. Here is where the service actually
     * gets to know about the recylcing machine.
     * @param thePanel
     */
    public void setPanel( MaintanancePanel thePanel ) {
        maintanancePanel = thePanel;
    }

    @Override
    public Map<String, Long> getStatus() throws IOException {
        return maintanancePanel.getStatus();
    }

    @Override
    public void emptySlot(int slot) throws IOException {
        maintanancePanel.emptySlot(slot);
    }

    @Override
    public void changeItemValue(String name, int value) {
        maintanancePanel.changeItemValue(name, value);
    }

    @Override
    public String login(String userName, String password) throws NoSuchAlgorithmException {
       return loginPanel.login(userName, password);
    }

    @Override
    public boolean isLoggedIn() throws RemoteException {
        return loginPanel.isLoggedIn();
    }

    @Override
    public int getItemValue(String name) throws RemoteException {
        return maintanancePanel.getItemValue(name);
    }

    @Override
    public Map<String, Long> getCapacity() throws RemoteException{
       return maintanancePanel.getCapacity();
    }

    @Override
    public void setCapacity(String name, long capaity) throws IOException {
        maintanancePanel.setCapacity(name, capaity);
    }

    @Override
    public void classifyItem(int slot) {
        customerPanel.classifyItem(slot);
    }

    @Override
    public void printReceipt() throws IOException {
        customerPanel.printReceipt();
    }

    @Override
    public void closeConnection() {
        maintanancePanel.closeConnection();
    }

    @Override
    public ArrayList<Document> getUsage(long from, long to) throws IOException {
        return maintanancePanel.getUsage(from, to);
    }
}

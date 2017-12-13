package me.laszloszoboszlai.rmi;

import me.laszloszoboszlai.service.CustomerPanel;
import me.laszloszoboszlai.service.LoginPanel;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class RecycleRmiImpl extends UnicastRemoteObject implements RecycleRMI{

    private static final long serialVersionUID = 1L;
    private CustomerPanel customerPanel;
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
    public void setPanel( CustomerPanel thePanel ) {
        customerPanel = thePanel;
    }

    @Override
    public Map<String, Long> getStatus() throws IOException {
        return customerPanel.getStatus();
    }

    @Override
    public void emptySlot(int slot) throws IOException {
        customerPanel.emptySlot(slot);
    }

    @Override
    public void changeItemValue(String name, int value) {
        customerPanel.changeItemValue(name, value);
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
        return customerPanel.getIemValue(name);
    }
}

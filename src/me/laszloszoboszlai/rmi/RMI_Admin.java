package me.laszloszoboszlai.rmi;
import me.laszloszoboszlai.view.GUI.AdminLoginGUI;
import java.rmi.RemoteException;

public class RMI_Admin {
    public static void main(String [] args ) throws RemoteException {
        AdminLoginGUI adminGUI = new AdminLoginGUI("RMI");
        adminGUI.setVisible(true);
    }
}

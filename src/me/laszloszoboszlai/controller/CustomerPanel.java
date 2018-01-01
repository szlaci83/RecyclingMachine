package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.service.DepositItemReceiver;
import me.laszloszoboszlai.service.DepositItemReceiverInterface;
import me.laszloszoboszlai.view.PrinterInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Customer controller of the recycling machine
 * Only functionality relevant for the users of the machine can be accessed from here.
 *
 * @author Laszlo Szoboszlai
 */
public class CustomerPanel {

    /**
     * The receiver used by the system.
     */
    DepositItemReceiverInterface receiver = null;

    /**
     * Constructor to create a deposit-item receiver with a printer implementation.
     *
     * @param printer the implementation of the printer the machine should use.
     * @throws RemoteException if there is an error through remote call.
     */
    public CustomerPanel(PrinterInterface printer) throws RemoteException {
        receiver = new DepositItemReceiver(printer);
    }

    /**
     * Classifies an item using the receiver
     *
     * @param slot slot number where the item intserted
     */
    public void itemReceived(int slot) {
        receiver.classifyItem(slot);
    }

    /**
     * Prints the receipt for the deposited items.
     *
     * @return true if it was successful.
     */
    public boolean printReceipt() throws IOException {
        receiver.printReceipt();
        return true;
    }

    /**
     * Classifies am inserted item depending on the slot number.
     *
     * @param slot the number of the slot the item is inserted into.
     * @return true if it was successful.
     */
    public boolean classifyItem(int slot) {
        receiver.classifyItem(slot);
        return true;
    }
}
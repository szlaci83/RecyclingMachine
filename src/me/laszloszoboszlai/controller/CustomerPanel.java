package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.service.DepositItemReceiver;
import me.laszloszoboszlai.service.DepositItemReceiverInterface;
import me.laszloszoboszlai.view.PrinterInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Simple view of the recycling machine
 * @author Laszlo Szoboszlai
 *
 */
public class CustomerPanel {

    /**
     * The receiver used by the system.
     */
	DepositItemReceiverInterface receiver = null;



	public CustomerPanel(PrinterInterface printer) throws RemoteException {
		receiver = new DepositItemReceiver(printer);
	}

	/**
     * Classifies an item using the receiver
	 * @param slot  slot number where the item intserted
	 */
	public void itemReceived(int slot) { 
		receiver.classifyItem(slot); 
	}
	/**
	 * Prints the receipt for the deposited items.
	 */
	public boolean printReceipt() throws IOException {
		receiver.printReceipt();
		return true;
	}

	public boolean classifyItem(int slot){
		receiver.classifyItem(slot);
		return true;
	}
}
package me.laszloszoboszlai.service;

import me.laszloszoboszlai.controller.DepositItemReceiver;
import me.laszloszoboszlai.controller.DepositItemReceiverInterface;
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
	public void printReceipt() throws IOException {
		receiver.printReceipt();
	}

	/**
	 *  Prints the status of the machine
	 */
	public Map<String, Long> getStatus(){
		return receiver.getStatus();
	}

	public void printCapacity(){
		receiver.printCapacity();
	}

	public void emptySlot(int slot) throws IOException { receiver.emptySlot(slot);}

	public void closeConnection(){
		receiver.closeConnection();
	}


	public void changeItemValue(String name, int value) {
		receiver.changeItemValue(name,value);
	}

	public int getIemValue(String name){
		return receiver.getItemValue(name);
	}
}

package me.laszloszoboszlai.view;

import me.laszloszoboszlai.controller.DepositItemReceiver;
import me.laszloszoboszlai.service.PrinterInterface;

/**
 * Simple view of the recycling machine
 * @author Laszlo Szoboszlai
 *
 */
public class CustomerPanel {

    /**
     * The receiver used by the system.
     */
	DepositItemReceiver receiver = null;

	public CustomerPanel(PrinterInterface printer) {
		receiver = new DepositItemReceiver(printer);
	}

	/**
     * Classifies an item using the receiver.
	 * @param slot  slot number where the item intserted
	 */
	public void itemReceived(int slot) { 
		receiver.classifyItem(slot); 
	}
	/**
	 * Prints the receipt for the deposited items.
	 */
	public void printReceipt() { 
		receiver.printReceipt();
	}

	/**
	 *  Prints the status of the machine (Full/ not full)
	 */
	public void printStatus(){
		receiver.printStatus();
	}
}

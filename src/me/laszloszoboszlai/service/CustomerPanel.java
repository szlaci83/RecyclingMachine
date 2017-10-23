package me.laszloszoboszlai.service;

import me.laszloszoboszlai.controller.DepositItemReceiver;

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

	/**
     * Interface to be implemented by the printer classes of the system.
     * @author Laszlo Szoboszlai
     */
    public static interface PrinterInterface {
        /**
         * Print method to be implemented by all the implementing classes.
         * @param str the String to be printed.
         */
        public void print(String str);
    }
}

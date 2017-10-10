package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.service.PrinterInterface;

/**
 * @author Marc Conrad
 *
 */
public class CustomerPanel {

	DepositItemReceiver receiver;

	public CustomerPanel(PrinterInterface printer) {
		receiver = new DepositItemReceiver(printer);
	}

	/**
	 * @param slot
	 */
	public void itemReceived(int slot) { 
		receiver.classifyItem(slot); 
	}
	/**
	 * 
	 */
	public void printReceipt() { 
		receiver.printReceipt();
	}
}

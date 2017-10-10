package me.laszloszoboszlai;

import me.laszloszoboszlai.controller.CustomerPanel;
import me.laszloszoboszlai.service.PrinterInterface;
import me.laszloszoboszlai.service.ReceiptPrinter;

/**
 * Tests the recycling machine.
 * @author Marc Conrad
 *
 */
public class SimpleTester {
	
	public static void main(String [] args) {
		PrinterInterface printer = new ReceiptPrinter();
		CustomerPanel myPanel = new CustomerPanel(printer);
		myPanel.itemReceived(1);
		myPanel.itemReceived(4);
		myPanel.itemReceived(3);
		myPanel.itemReceived(2);
		myPanel.itemReceived(2);
		myPanel.printReceipt();
	}
}

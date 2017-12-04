package me.laszloszoboszlai;

import me.laszloszoboszlai.service.CustomerPanel;
import me.laszloszoboszlai.view.Display;

import java.io.IOException;

/**
 * Tests basic functionality of the recycling machine.
 * @author Laszlo Szoboszlai
 *
 */
public class SimpleTester {
	
	public static void main(String [] args) throws IOException {
		//Display myDisplay = new Display();
		//CustomerPanel thePanel = new CustomerPanel(myDisplay);

		//PrinterInterface printer = new ReceiptPrinter();
		CustomerPanel myPanel = new CustomerPanel(new Display());
		myPanel.printStatus();
		myPanel.itemReceived(1);
		myPanel.itemReceived(4);
		myPanel.itemReceived(3);
		myPanel.itemReceived(2);
		myPanel.itemReceived(2);
		myPanel.itemReceived(1);
		myPanel.itemReceived(4);
		myPanel.itemReceived(3);
		myPanel.itemReceived(2);
		myPanel.itemReceived(2);
		myPanel.printStatus();
		myPanel.printCapacity();
		//myPanel.printReceipt();
	}
}

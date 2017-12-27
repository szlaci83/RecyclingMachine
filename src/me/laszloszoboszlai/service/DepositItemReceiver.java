package me.laszloszoboszlai.service;

import me.laszloszoboszlai.model.*;
import me.laszloszoboszlai.repository.ReceiptBasis;
import me.laszloszoboszlai.view.PrinterInterface;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Class to control the logic of receiving, classifying, and printing items.
 * @author Laszlo Szoboszlai
 *
 */
public class DepositItemReceiver implements DepositItemReceiverInterface{

	// session cookie to identify the individual users
	//String sessioncookie = "notset";

	private ItemServiceInterface itemService = new ItemService();
	private ReceiptBasis theReceiptBasis;
	private PrinterInterface printer = null;

	public DepositItemReceiver(PrinterInterface printer) throws RemoteException {
		this.printer = printer;
		createReceiptBasis();
	}


	/**
	 * Creates a single instance of the receipt basis.
	 */
	public void createReceiptBasis() { 
		theReceiptBasis = new ReceiptBasis();
	}

	/**
     * Classifies the inserted item.
	 * @param slot the number of the slot the item inserted into.
	 */
	public String classifyItem(int slot) {
		Item item = null;
		if( slot == 1 ) {
			item = Can.getFromJson();
		} else if( slot == 2 ) { 
			item = Bottle.getFromJson();
		} else if ( slot == 3 ) { 
			item = Crate.getFromJson();
		} else if ( slot == 4 ) {
			item = Carton.getFromJson();
		}

		if( theReceiptBasis == null ) { 
			createReceiptBasis(); 
		}
		if (!itemService.isFull(item.getName())) {
			theReceiptBasis.addItem(item);
			printer.print(item.getName() + " Received.");

			return item.getName() + " received";
		}
		return null;
	}


	/**
	 * Computes the sum, and uses the printer to print out the result, and clears the receiptbasis.
	 */
	public String printReceipt() throws IOException {
		String result = null;
		if (theReceiptBasis != null){
			String str = theReceiptBasis.computeSum();
			printer.print(str);
			result = str;
			itemService.recordDeposit(theReceiptBasis.getItems());
			itemService.recordUsage(theReceiptBasis.getItems());
			theReceiptBasis = null;
		}
		return result;
	}
}
package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.model.*;
import me.laszloszoboszlai.repository.*;
import me.laszloszoboszlai.view.PrinterInterface;
import org.bson.Document;

import java.io.IOException;
import java.util.Map;

/**
 * Class to control the logic of receiving, classifying, and printing items.
 * @author Laszlo Szoboszlai
 *
 */
public class DepositItemReceiver implements DepositItemReceiverInterface{

	// session cookie to identify the individual users
	//String sessioncookie = "notset";

	private ReceiptBasisInterface theReceiptBasis;
	private PrinterInterface printer = null;

	public DepositItemReceiver(PrinterInterface printer) {
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
		theReceiptBasis.addItem(item);
		printer.print(item.getName() + " Received.");

		return item.getName() + " received";
	}

	/**
	 * Prints if the machine full or not.
	 * @return true is the machine is full false otherwise.
	 */
	public String printStatus(){
		String statusString = "Status: \n";
		if (theReceiptBasis == null){
			return statusString;
		}
		Map<String, Long>  status = theReceiptBasis.getStatus();
		for (String itemName : status.keySet()){
			statusString += itemName + ":" + status.get(itemName) + "\n";
		}
		printer.print(statusString);
		return statusString;
	}

	/**
	 * Prints if the machine full or not.
	 * @return true is the machine is full false otherwise.
	 */
	public String printCapacity(){
		String capacityString = "Capacity: \n";

		if (theReceiptBasis == null){
			return capacityString;
		}
		Map<String, Long>  status = theReceiptBasis.getCapacity();
		for (String itemName : status.keySet()){
			capacityString += itemName + ":" + status.get(itemName) + "\n";
		}
		printer.print(capacityString);
		return capacityString;
	}

	public String closeConnection(){
		if (theReceiptBasis != null) {
			theReceiptBasis.closeConnection();
		}
		return "Connection closed.";
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
	    }
	    theReceiptBasis.closeConnection();
		theReceiptBasis = null;
	    return result;
	}

	public String emptySlot(int slot) throws IOException {
		Item item = null;
		if( slot == 1 ) {
			theReceiptBasis.emptySlot("Can");
		} else if( slot == 2 ) {
			theReceiptBasis.emptySlot("Bottle");
		} else if ( slot == 3 ) {
			theReceiptBasis.emptySlot("Crate");
		} else if ( slot == 4 ) {
			theReceiptBasis.emptySlot("Carton");
		}
		return "Slot" + slot + "emptied.";
	}

	public void changeItemValue(String name, int value){
		theReceiptBasis.changeItemValue(name, value);
	}

}
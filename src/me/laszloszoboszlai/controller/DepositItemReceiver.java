package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.model.*;
import me.laszloszoboszlai.repository.ReceiptBasis;
import me.laszloszoboszlai.repository.ReceiptBasisInterface;
import me.laszloszoboszlai.repository.UserRepository;
import me.laszloszoboszlai.repository.UserRepositoryInterface;
import me.laszloszoboszlai.view.PrinterInterface;

/**
 * Class to control the logic of receiving, classifying, and printing items.
 * @author Laszlo Szoboszlai
 *
 */
public class DepositItemReceiver implements DepositItemReceiverInterface{

	String sessioncookie = "fdfsdfsdfsdfsdf";

	ReceiptBasisInterface theReceiptBasis = null;
	PrinterInterface printer = null;
	UserRepositoryInterface userRepository = new UserRepository();

	public DepositItemReceiver(PrinterInterface printer) {
		this.printer = printer;
	}


	public String login(String passwd){
		if( passwd.equals(userRepository.getUserByName("admin"))){
			sessioncookie = "Random"+Math.random();
			return sessioncookie;
		} else {
			return "wrong";
		}
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
	public void classifyItem(int slot) {
		DepositItem item = null;
		if( slot == 1 ) {
			item = Can.getFromJson();
		} else if( slot == 2 ) { 
			item = Bottle.getFromJson();
		} else if ( slot == 3 ) { 
			item = Crate.getFromJson();
		} else if ( slot == 4 ) {
			item = Cartoon.getFromJson();
		}

		if( theReceiptBasis == null ) { 
			createReceiptBasis(); 
		}
		theReceiptBasis.addItem(item);
		printer.print(item.getName() + " Received.");
	}

	/**
	 * Prints if the machine full or not.
	 * @return true is the machine is full false otherwise.
	 */
	public void printStatus(){
		String neg = "";
		if ((theReceiptBasis == null) || (! theReceiptBasis.isFull())  ) {
			neg = "not ";
		}
		printer.print("The machine is " + neg + "full.");
	}

	/**
	 * Computes the sum, and uses the printer to print out the result, and clears the receiptbasis.
	 */
	public void printReceipt() {
	    if (theReceiptBasis != null){
		String str = theReceiptBasis.computeSum(); 
		printer.print(str); }
		theReceiptBasis = null; 
	}
}

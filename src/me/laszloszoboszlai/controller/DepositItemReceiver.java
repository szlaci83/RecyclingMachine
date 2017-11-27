package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.model.*;
import me.laszloszoboszlai.repository.*;
import me.laszloszoboszlai.view.PrinterInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to control the logic of receiving, classifying, and printing items.
 * @author Laszlo Szoboszlai
 *
 */
public class DepositItemReceiver implements DepositItemReceiverInterface{

	// session cookie to identify the individual users
	String sessioncookie = "notset";

	ReceiptBasisInterface theReceiptBasis = null;
	PrinterInterface printer = null;
	UserRepositoryInterface userRepository = new UserRepository();

	public DepositItemReceiver(PrinterInterface printer) {
		this.printer = printer;
		//this.intitialise();
			}

	ItemRepositoryInterface itemRepository = new ItemRepository();
	Map<String,Integer> depositedItems = new HashMap<>();


	private void intitialise(){
		depositedItems = itemRepository.getItems();
	}


	/**
	 * Method to log users in, users first need to login in order to use the system.
	 * @param passwd the password of the user
	 * @return returns the sessioncookie if the login was success "wrong" otherwise
	 */
	public String login(String passwd){
		if( passwd.equals(userRepository.getUserByName("password"))){
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



	private void addItem(){
		
	}


	/**
     * Classifies the inserted item.
	 * @param slot the number of the slot the item inserted into.
	 */
	public String classifyItem(int slot) {
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

		return item.getName() + " received";
	}

	/**
	 * Prints if the machine full or not.
	 * @return true is the machine is full false otherwise.
	 */
	public String printStatus(){
		String neg = "";
		if ((theReceiptBasis == null) || (! theReceiptBasis.isFull())  ) {
			neg = "not ";
		}
		String msg = "The machine is " + neg + "full.";
		printer.print(msg);
		return msg;
	}

	/**
	 * Computes the sum, and uses the printer to print out the result, and clears the receiptbasis.
	 */
	public String printReceipt() {
		String result = null;
	    if (theReceiptBasis != null){
		String str = theReceiptBasis.computeSum();
		printer.print(str);
	    result = str;}
		theReceiptBasis = null;
	    return result;
	}
}

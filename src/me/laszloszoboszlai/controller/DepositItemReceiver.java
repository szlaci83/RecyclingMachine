package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.domain.*;
import me.laszloszoboszlai.service.PrinterInterface;
import me.laszloszoboszlai.service.ReceiptPrinter;

/**
 * @author Marc Conrad
 *
 */
public class DepositItemReceiver {
	ReceiptBasisInterface theReceiptBasis = null;
	PrinterInterface printer = new ReceiptPrinter();

	public DepositItemReceiver(PrinterInterface printer) {
		this.printer = printer;
	}

	/**
	 * 
	 */
	public void createReceiptBasis() { 
		theReceiptBasis = new AggregatedVATReceipt();
	}
	/**
	 * @param slot
	 */
	public void classifyItem(int slot) { 
		DepositItem item = null;
		if( slot == 1 ) { 
			item = new Can();
		} else if( slot == 2 ) { 
			item = new Bottle();
		} else if ( slot == 3 ) { 
			item = new Crate();
		} else if ( slot == 4 ) {
			item = new Bottle2();
		}

		if( theReceiptBasis == null ) { 
			createReceiptBasis(); 
		}
		theReceiptBasis.addItem(item); 
	}
	/**
	 * 
	 */
	public void printReceipt() { 
		String str = theReceiptBasis.computeSum(); 
		printer.print(str); 
		theReceiptBasis = null; 
	}
}

package me.laszloszoboszlai.view;

import me.laszloszoboszlai.service.CustomerPanel;

/**
 * Simple printer class implementing the PrinterInterface, to use standard output.
 * @author Laszlo Szoboszlai
 *
 */
public class ReceiptPrinter implements PrinterInterface {
	/**
	 * Prints to the standard output.
	 * @param str the String to be printed.
	 */
	public void print(String str) { 
		System.out.println(str);
	}
}

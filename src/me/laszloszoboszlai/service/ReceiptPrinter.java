package me.laszloszoboszlai.service;

/**
 * @author Marc Conrad
 *
 */
public class ReceiptPrinter implements PrinterInterface {
	/**
	 * @param str
	 */
	public void print(String str) { 
		System.out.println(str);
	}
}

package me.laszloszoboszlai.controller;
import me.laszloszoboszlai.domain.DepositItem;

import java.util.Vector;

/**
 * @author Marc Conrad
 *
 */
public class ReceiptBasis implements ReceiptBasisInterface {
	private Vector<DepositItem> myItems = new Vector<>();
	/**
	 * @param item
	 */
	public void addItem(DepositItem item) { 
		myItems.add(item); 
		item.number = myItems.indexOf(item); 
	}
	/**
	 * @return
	 */
	public String computeSum() { 
		String receipt = ""; 
		int sum = 0;

		for (DepositItem item: myItems) {
			receipt = receipt + item.getClass().getSimpleName() +": "+ item.value;
			receipt = receipt + System.getProperty("line.separator");
			sum += item.value;
		}

		receipt = receipt + "Total: "+sum; 
		return receipt; 
	}
}

package me.laszloszoboszlai.repository;
import me.laszloszoboszlai.domain.*;

import java.util.Vector;

/**
 * This is where the data lives, i.e. cans, bottles and crates are recorded
 * in this class. We might call it our database (if we insist!).
 * It also provides a summative statement about all the items inserted into the
 * machine.
 * @author Marc Conrad
 *
 */
public class ReceiptBasis implements ReceiptBasisInterface {
	private int capacity;

	public ReceiptBasis(){
		this.capacity = 500;
	}

	public ReceiptBasis(int capacity){
		this.capacity = capacity;
	}

	private Vector<DepositItem> myItems = new Vector<DepositItem>();
	/**
	 * @param item an item that has been inserted into the machine (such as can, bottle, crate).
	 */
	public void addItem(DepositItem item) {
		int size = 0;
		if (item instanceof Bottle){
			size = Bottle.getSize();
		}
		if (item instanceof Can){
			size = Can.getSize();
		}
		if (item instanceof Crate){
			size = Crate.getSize();
		}
		if (item instanceof Cartoon){
			size = Cartoon.getSize();
		}
		if (! (capacity - size < 0)) {
			myItems.add(item);
			item.number = myItems.indexOf(item);
			capacity -= size;
		}

	}

	/**
	 * Calculates a summary based on the items inserted.
	 * @return the overall value of the items inserted by the customer.
	 */
	public String computeSum() {
		String receipt = "";
		int sum = 0;
		for(int i=0; i < myItems.size(); i++ ) {
			DepositItem item = myItems.get(i);
			receipt = receipt + item.number +": "+item.value +" ("+item.getName()+")";
			receipt = receipt + System.getProperty("line.separator");
			sum = sum + item.value;
		}
		receipt = receipt + "Total: "+sum;
		return receipt;
	}

	@Override
	public boolean isFull() {
		return capacity <= 20;
	}
}

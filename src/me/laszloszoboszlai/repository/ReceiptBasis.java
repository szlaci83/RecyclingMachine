package me.laszloszoboszlai.repository;
import me.laszloszoboszlai.model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The deposit of cans, bottles, cartons and crates are recorded
 * in this class. We might call it our database (if we insist!).
 * It also provides a summative statement about all the items inserted into the
 * machine.
 * @author Laszlo Szoboszlai
 *
 */
public class ReceiptBasis{

	public ReceiptBasis(){
	}

	private Map<String, Item> Items = new HashMap<>();

	/**
	 * @param item an item that has been inserted into the machine (such as can, bottle, crate).
	 */
	public void addItem(Item item) {
		if (Items.containsKey(item.getName())){
			Item i = Items.get(item.getName());
			long temp = i.getCount();
			i.setCount(++ temp);
			Items.put(item.getName(), i);
		}
		else {
			item.setCount(1L);
			Items.put(item.getName(), item);
		}
		item.number = Items.size();
	}

	public Map<String, Item> getItems() {
		return Items;
	}

	/**
	 * Calculates a summary based on the items inserted.
	 * @return the overall value of the items inserted by the customer.
	 */
	public String computeSum() throws IOException {
		String receipt = "";
		long sum = 0;
		for (String item : Items.keySet() ){
			Item i = Items.get(item);
			long summa = i.value * i.getCount();
			receipt += i.getCount() +": £"+ summa +" ("+i.getName()+")";
			receipt += System.getProperty("line.separator");
			sum = sum + summa;
		}
		receipt = receipt + "Total: £"+sum;
		return receipt;
	}
}
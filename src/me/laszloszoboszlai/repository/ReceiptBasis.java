package me.laszloszoboszlai.repository;
import me.laszloszoboszlai.model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This is where the data lives, i.e. cans, bottles and crates are recorded
 * in this class. We might call it our database (if we insist!).
 * It also provides a summative statement about all the items inserted into the
 * machine.
 * @author Laszlo Szoboszlai
 *
 */
public class ReceiptBasis{
//	private ItemRepository itemRepository = new ItemRepository();
//	private UsageRepository usageRepository = new UsageRepository();
//	private HashMap<String, Item> existingItems;

	public ReceiptBasis(){
//		this.existingItems = itemRepository.loadItems();
		//System.out.println(capacity);
		//System.out.println(existingItems);
	}

	private Map<String, Item> Items = new HashMap<>();


	/**
	 * @param item an item that has been inserted into the machine (such as can, bottle, crate).
	 */
	public void addItem(Item item) {
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
		if (item instanceof Carton){
			size = Carton.getSize();
		}

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
			//myItems.add(item);
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
//		recordUsage();
//		recordDeposit();
		return receipt;
	}



//	public int getItemValue(String name) {
//		System.out.println(itemRepository.loadItems().get(name));
//		return ((Item) itemRepository.loadItems().get(name)).getValue();
//	}

//
//	public boolean isFull(String itemName) {
////		if (!existingItems.keySet().contains(itemName)){
////			return false;
////		}
////			 return (existingItems.get(itemName).getCount() >= capacity.get(itemName));
//		return true;
//	}
//
//	public Map<String, Long> getStatus(){
//		Map <String, Long> status = new HashMap<>() ;
//		for (String itemName : existingItems.keySet()){
//			status.put(itemName, existingItems.get(itemName).getCount());
//		}
//		return status;
//	}



//	public void setCapacity(String name, long value) throws IOException {
//		capacity.put(name, value);
//		System.out.println(capacity);
//		recordCapacity();
//	}
}
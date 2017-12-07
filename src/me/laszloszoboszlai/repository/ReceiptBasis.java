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
public class ReceiptBasis implements ReceiptBasisInterface {
	private Map<String, Long> capacity;
	private ItemRepositoryInterface itemRepository = new ItemRepository();
	private UsageRepositoryInterface usageRepository = new UsageRepository();
	private HashMap<String, Item> existingItems;

	public ReceiptBasis(){
		this.capacity = itemRepository.loadCapacity();
		this.existingItems = itemRepository.loadItems();
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
		if (! isFull(item.getName())) {
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

	}


	public void recordDeposit() throws IOException {
		itemRepository.saveItems(depositItems(), "deposited");
	}

	public void recordCapacity()throws IOException {
		itemRepository.saveItems(existingItems, "deposited");
	}

	public void recordUsage()throws IOException {
		usageRepository.insertOne(Items);
	}


	private Map depositItems() {
		//System.out.println(existingItems);
		Map<String, Item> addedItems = new HashMap<>();

		for (String newItem : Items.keySet() ){
			Item temp = Items.get(newItem);
			if (existingItems.containsKey(newItem)){
				Long updatedCount = Items.get(newItem).getCount() + existingItems.get(newItem).getCount();
				temp.setCount(updatedCount);
				existingItems.remove(newItem);
			}
			addedItems.put(newItem, temp);
		}

		for (String oldItem : existingItems.keySet()){
			addedItems.put(oldItem, existingItems.get(oldItem));
		}

		return addedItems;
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
		recordUsage();
		recordDeposit();
		return receipt;
	}



	@Override
	public boolean isFull(String itemName) {
		if (!existingItems.keySet().contains(itemName)){
			return false;
		}
			 return (existingItems.get(itemName).getCount() >= capacity.get(itemName));
	}

	public Map<String, Long> getStatus(){
		Map <String, Long> status = new HashMap<>() ;
		for (String itemName : existingItems.keySet()){
			status.put(itemName, existingItems.get(itemName).getCount());
		}
		return status;
	}

	public Map<String, Long> getCapacity(){
		return capacity;
	}

	@Override
	public void emptySlot(String slot) throws IOException {
		Item itemToBeEmptied = existingItems.get(slot);
		itemToBeEmptied.setCount(0L);
		existingItems.put(slot, itemToBeEmptied);
		recordCapacity();
	}
}
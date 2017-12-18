package me.laszloszoboszlai.service;

import me.laszloszoboszlai.model.Item;
import me.laszloszoboszlai.repository.ItemRepository;
import me.laszloszoboszlai.repository.UsageRepository;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class ItemServiceImpl implements ItemService {
    private Map<String, Long> capacity;
    private ItemRepository itemRepository = new ItemRepository();
    private UsageRepository usageRepository = new UsageRepository();
    private HashMap<String, Item> existingItems;

    public ItemServiceImpl(){
        this.capacity = itemRepository.loadCapacity();
        this.existingItems = itemRepository.loadItems();
    }


    private Map depositItems(Map<String, Item> Items) {
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


    public void recordDeposit(Map<String, Item> items) throws IOException {
        itemRepository.saveItems(depositItems(items), "deposited");
    }

    public void recordExisting()throws IOException {
        itemRepository.saveItems(existingItems, "deposited");
    }

    public void recordCapacity()throws IOException {
        itemRepository.saveItems(capacity, "capacity");
    }

    public void recordUsage(Map<String, Item> items)throws IOException {
        usageRepository.insertOne(items);
    }


    /**
     * Prints if the machine full or not.
     * @return true is the machine is full false otherwise.
     */
    public Map<String, Long> getStatus(){
        Map <String, Long> status = new HashMap<>() ;
        for (String itemName : existingItems.keySet()){
            status.put(itemName, existingItems.get(itemName).getCount());
        }
        return status;
    }


    public void setCapacity(String name, long value) throws IOException {
        capacity.put(name, value);
        System.out.println(capacity);
        recordCapacity();
    }

    @Override
    public ArrayList<Document> getUsage(long from, long to) throws IOException {
        return usageRepository.findBetweenDates(from, to);
    }

    public String closeConnection(){
        usageRepository.closeConnection();
        return "Connection closed.";
    }


    public void emptySlot(String slot) throws IOException {
        Item itemToBeEmptied = existingItems.get(slot);
        itemToBeEmptied.setCount(0L);
        existingItems.put(slot, itemToBeEmptied);
        recordExisting();
    }

    public String emptySlot(int slot) throws IOException {
        Item item = null;
        if( slot == 1 ) {
            emptySlot("Can");
        } else if( slot == 2 ) {
            emptySlot("Bottle");
        } else if ( slot == 3 ) {
            emptySlot("Crate");
        } else if ( slot == 4 ) {
            emptySlot("Carton");
        }
        return "Slot" + slot + "emptied.";
    }

    public boolean isFull(String itemName) {
		if (!existingItems.keySet().contains(itemName)){
			return false;
		}
			 return (existingItems.get(itemName).getCount() >= capacity.get(itemName));
    }


    public String changeItemValue(String name, int value) {
        itemRepository.changeValue(name, value);
        return "Value changed";
    }

    public int getItemValue(String name) {
        //System.out.println(itemRepository.loadItems().get(name));
        return ((Item) itemRepository.loadItems().get(name)).getValue();
    }

    public Map<String, Long> getCapacity(){
        return capacity;
    }
}

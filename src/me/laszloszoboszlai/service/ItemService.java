package me.laszloszoboszlai.service;

import me.laszloszoboszlai.model.Item;
import me.laszloszoboszlai.repository.ItemRepository;
import me.laszloszoboszlai.repository.UsageRepository;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Itemservice implementation to handle the coordination of individual repositories
 * to store the items, usage and capacity information.
 *
 * @author Laszlo Szoboszlai
 */
public class ItemService implements ItemServiceInterface {
    private HashMap<String, Item> existingItems;
    private Map<String, Long> capacity;
    private ItemRepository itemRepository = new ItemRepository();
    private UsageRepository usageRepository = new UsageRepository();

    /**
     * Constructor to load the capacity and deposited values from their repositories.
     */
    public ItemService(){
        this.capacity = itemRepository.loadCapacity();
        this.existingItems = itemRepository.loadItems();
    }

    /**
     * Method to deposit items (updates the existingItems Map)
     * @param Items Map of items in ItemName -> Item format
     * @return the updated Map
     */
    private Map depositItems(Map<String, Item> Items) {
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
     * Method to deposit given items.
     * @param items Map of items in ItemName -> Item format.
     * @throws IOException if there is an I/O error.
     */
    public void recordDeposit(Map<String, Item> items) throws IOException {
        itemRepository.saveItems(depositItems(items), "deposited");
    }

    /**
     * Method to deposit the existing items Map.
     * @throws IOException if there is an I/O error.
     */
    public void recordExisting()throws IOException {
        itemRepository.saveItems(existingItems, "deposited");
    }

    /**
     * Persists the capacity Map.
     * @throws IOException if there is an I/O error.
     */
    public void recordCapacity()throws IOException {
        itemRepository.saveItems(capacity, "capacity");
    }

    /**
     * Records a single Usage (1 receipt) worth of data.
     * @param items the usage information (receipt) as a Map.
     * @throws IOException if there is an I/O error.
     */
    public void recordUsage(Map<String, Item> items)throws IOException {
        usageRepository.insertOne(items);
    }

    /**
     * Returns the number of items deposited of each item.
     * @return a Map of the number of items deposited in a ItemName -> count format.
     */
    public Map<String, Long> getStatus(){
        Map <String, Long> status = new HashMap<>() ;
        for (String itemName : existingItems.keySet()){
            status.put(itemName, existingItems.get(itemName).getCount());
        }
        return status;
    }

    /**
     * Sets a given Item's capacity to a given value.
     * @param name the name of the item.
     * @param value the value of the capacity.
     * @throws IOException if there is an I/O error.
     */
    public void setCapacity(String name, long value) throws IOException {
        capacity.put(name, value);
        recordCapacity();
    }

    /**
     * Gets the machine's usage information for a given time range.
     * @param from the time range start time in unix epoch
     * @param to the time range end time in unix epoch
     * @return an ArrayList of mongoDB Documents of the usage data.
     * @throws IOException if there is an I/O error.
     */
    @Override
    public ArrayList<Document> getUsage(long from, long to) throws IOException {
        return usageRepository.findBetweenDates(from, to);
    }

    /**
     * Closes the connection to the database.
     * @return "Connection closed."
     */
    public String closeConnection(){
        usageRepository.closeConnection();
        return "Connection closed.";
    }

    /**
     * Resets a given item's counter in the machine.
     * @param slot the name of the item to be emptied.
     * @throws IOException if there is an I/O error.
     */
    public void emptySlot(String slot) throws IOException {
        Item itemToBeEmptied = existingItems.get(slot);

        itemToBeEmptied.setCount(0L);
        existingItems.put(slot, itemToBeEmptied);
        recordExisting();
    }

    /**
     * Resets a given item's counter in the machine.
     * @param slot  the slot number of the item.
     * @return "Slot NUMBER emptied"
     * @throws IOException
     */
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

    /**
     * Checks if a given item's capacity is full or not.
     * @param itemName the name of the item to be checked.
     * @return false, if the the item's slot is not full yet.
     */
    public boolean isFull(String itemName) {
		if (!existingItems.keySet().contains(itemName)){
			return false;
		}
           return (existingItems.get(itemName).getCount() >= capacity.get(itemName));
    }

    /**
     * Changes a given item's value.
     * @param name the name of the item.
     * @param value the new value of the item.
     * @return "Value changed"
     */
    public String changeItemValue(String name, int value) {
        itemRepository.changeValue(name, value);
        return "Value changed";
    }

    /**
     * Return the value of a given item.
     * @param name the name of the item.
     * @return the value of the item.
     */
    public int getItemValue(String name) {
        return itemRepository.getValue(name);
    }

    /**
     *  Returns the capacity map.
     * @return the capacity map.
     */
    public Map<String, Long> getCapacity(){
        return capacity;
    }
}
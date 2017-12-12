package me.laszloszoboszlai.repository;

import me.laszloszoboszlai.model.Item;

import java.io.IOException;
import java.util.Map;

/**
 * Interface implemented by the repository classes in the system.
 * @author Laszlo Szoboszlai
 */
public interface ReceiptBasisInterface {
    /**
     * @param item an item that has been inserted into the machine (such as can, bottle, crate).
     */
    public void addItem(Item item);

    /**
     * Calculates a summary based on the items inserted.
     */
    public String computeSum() throws IOException;


    /**
     *  Checks if the machine is full.
     */
    public boolean isFull(String slot);

    public Map<String, Long> getStatus();

    public Map<String, Long> getCapacity();

    public void emptySlot(String slot) throws IOException;

    public void closeConnection();

    void changeItemValue(String name, int value);

    public int getItemValue(String name);
}

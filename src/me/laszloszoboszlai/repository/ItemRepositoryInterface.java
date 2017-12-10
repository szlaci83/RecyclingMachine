package me.laszloszoboszlai.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface implemented by the item repository classes in the system.
 * @author Laszlo Szoboszlai
 */
public interface ItemRepositoryInterface {
    public void saveItems(Map items, String name) throws IOException;
    public HashMap loadItems();
    public HashMap loadCapacity();
    public void saveCapacity(HashMap<String, Long> capacity);
    public void changeValue(String name, int newValue);
}

package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.service.ItemService;
import me.laszloszoboszlai.service.ItemServiceInterface;
import org.bson.Document;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Panel to access the admin and maintenance functionalities of the machine.
 *
 * @author Laszlo Szoboszlai
 */
public class MaintenancePanel {
    private ItemServiceInterface itemService = ItemService.getInstance();
    private LoginPanel loginPanel = new LoginPanel();

    /**
     * Helper method to convert a Map<String, Long> to a Hashtable<String, String>
     *
     * @param map the map to be converted
     * @return the converted Hashtable
     */
    private Hashtable<String, String> MapToTable(Map<String, Long> map) {
        Hashtable table = new Hashtable();
        for (String item : map.keySet()) {
            table.put(item, map.get(item).toString());
        }
        return table;
    }

    /**
     * Helper method to convert a List<Document> (of MongoDB documents) to a Vector of Strings
     *
     * @param list the list to be converted
     * @return the converted Vector
     */
    private Vector<String> ListToVector(List<Document> list) {
        Vector<String> vector = new Vector<>();
        for (Document doc : list) {
            vector.add(doc.toJson());
        }
        return vector;
    }

    /**
     * Returns the status of the machine
     *
     * @param token the user-token
     * @return Hashtable of the status of the machine
     */
    public Hashtable<String, String> getStatus(String token) {
        if (!loginPanel.validateToken(token)) {
            return null;
        }
        return MapToTable(itemService.getStatus());
    }

    /**
     * Returns the capacity of the machine
     *
     * @param token the user-token
     * @return Hashtable of the capacity of the machine
     */
    public Hashtable<String, String> getCapacity(String token) {
        if (!loginPanel.validateToken(token)) {
            return null;
        }
        return MapToTable(itemService.getCapacity());
    }

    /**
     * Sets the capacity of the machine
     *
     * @param token the user-token
     * @param name  the name of the item, which capacity required to be changed
     * @param value the new value in pence
     * @return true if the operation was successful, false otherwise
     * @throws IOException
     */
    public boolean setCapacity(String token, String name, String value) throws IOException {
        if (!loginPanel.validateToken(token)) {
            return false;
        }
        itemService.setCapacity(name, Long.parseLong(value));
        return true;
    }

    /**
     * Empties an item's slot
     *
     * @param token the user-token
     * @param slot  the number of the slot to be emptied
     * @return true if the operation was successful, false otherwise
     * @throws IOException
     */
    public boolean emptySlot(String token, int slot) throws IOException {
        if (!loginPanel.validateToken(token)) {
            return false;
        }
        itemService.emptySlot(slot);
        return true;
    }

    /**
     * Changes the value of an item
     *
     * @param token the user-token
     * @param name  the name of the item, which value required to be changed
     * @param value the new value in pence
     * @return true if the operation was successful, false otherwise
     */
    public boolean changeItemValue(String token, String name, int value) {
        if (!loginPanel.validateToken(token)) {
            return false;
        }
        itemService.changeItemValue(name, value);
        return true;
    }

    /**
     * Returns the value of an item
     *
     * @param token the user-token
     * @param name  the name of the item, which value required to be changed
     * @return the value of a given item
     */
    public int getItemValue(String token, String name) {
        if (!loginPanel.validateToken(token)) {
            return -1;
        }
        return itemService.getItemValue(name);
    }

    /**
     * Returns the usage of the machine between two given dates.
     *
     * @param token the user-token
     * @param from  the date from the usage data is required.
     * @param to    the date until the usage data is required.
     * @return Vector of usage data as Strings
     * @throws IOException
     */
    public Vector<String> getUsage(String token, String from, String to) throws IOException {
        if (!loginPanel.validateToken(token)) {
            return null;
        }
        return ListToVector(itemService.getUsage(Long.parseLong(from), Long.parseLong(to)));
    }
}
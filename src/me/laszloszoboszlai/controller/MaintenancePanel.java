package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.exception.NotLoggedInException;
import me.laszloszoboszlai.service.ItemServiceInterface;
import me.laszloszoboszlai.service.ItemService;
import org.bson.Document;

import java.io.IOException;
import java.util.*;

/**
 * Panel to access the admin and maintenance functionalities of the machine.
 * @author  Laszlo Szoboszlai
 */
public class MaintenancePanel
{
    private ItemServiceInterface itemService = new ItemService();
    private LoginPanel loginPanel = new LoginPanel();

	/**
	 *
	 * @param map
	 * @return
	 */
	private Hashtable<String, String> MapToTable(Map<String, Long> map){
		Hashtable table = new Hashtable();
		for (String item : map.keySet()){
			table.put(item, map.get(item).toString());
		}
		return table;
	}

	private Vector<String> ListToVector(List<Document> list){
    	Vector<String> vector = new Vector<>();
    	for (Document doc : list){
    		vector.add(doc.toJson());
		}
		return vector;
	}
    /**
     *  Prints the status of the machine
     */
	public Hashtable<String, String> getStatus(String token) throws NotLoggedInException {
		if (! loginPanel.validateToken(token) ) {
			throw new NotLoggedInException("Login required for this operation!");
		}
		return MapToTable(itemService.getStatus());
	}

    public Hashtable<String, String> getCapacity(String token) throws NotLoggedInException {	if (! loginPanel.validateToken(token) ) {
		throw new NotLoggedInException("Login required for this operation!");
	}
	 return MapToTable(itemService.getCapacity());
	}

	public boolean setCapacity(String token, String name, long value) throws IOException, NotLoggedInException {
		if (! loginPanel.validateToken(token) ) {
			throw new NotLoggedInException("Login required for this operation!");
		}
		itemService.setCapacity(name, value);
        return true;
	}
	public boolean emptySlot(String token, int slot) throws IOException, NotLoggedInException {
		if (! loginPanel.validateToken(token) ) {
			throw new NotLoggedInException("Login required for this operation!");
		}
		itemService.emptySlot(slot);
    return true;}

	public boolean closeConnection(String token) throws NotLoggedInException {
		if (! loginPanel.validateToken(token) ) {
			throw new NotLoggedInException("Login required for this operation!");
		}
        itemService.closeConnection();
        return true;
	}

	public boolean changeItemValue(String token, String name, int value) throws NotLoggedInException {
		if (! loginPanel.validateToken(token) ) {
			throw new NotLoggedInException("Login required for this operation!");
		}
        itemService.changeItemValue(name,value);
        return true;
	}

	public int getItemValue(String token, String name) throws NotLoggedInException {
		if (! loginPanel.validateToken(token) ) {
		throw new NotLoggedInException("Login required for this operation!");
	}
		return itemService.getItemValue(name);
	}

    public Vector<String> getUsage(String token, String from, String to) throws IOException, NotLoggedInException {
		if (! loginPanel.validateToken(token) ) {
			throw new NotLoggedInException("Login required for this operation!");
		}
	    return ListToVector(itemService.getUsage(Long.parseLong(from), Long.parseLong(to)));
    }
}
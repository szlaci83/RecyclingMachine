package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.repository.UsageRepository;
import me.laszloszoboszlai.service.ItemService;
import me.laszloszoboszlai.service.ItemServiceImpl;
import org.bson.Document;

import java.io.IOException;
import java.util.*;

public class MaintanancePanel {
    private ItemService itemService = new ItemServiceImpl();

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
	public Hashtable<String, String> getStatus(){
		return MapToTable(itemService.getStatus());
	}
//
//	public void printCapacity(){
//        itemService.printCapacity();
//	}


    public Hashtable<String, String> getCapacity(){
	 return MapToTable(itemService.getCapacity());
	}

	public boolean setCapacity(String name, long value) throws IOException {
        itemService.setCapacity(name, value);
        return true;
	}
	public boolean emptySlot(int slot) throws IOException { itemService.emptySlot(slot);
    return true;}

	public boolean closeConnection(){
        itemService.closeConnection();
        return true;
	}

	public boolean changeItemValue(String name, int value) {
        itemService.changeItemValue(name,value);
        return true;
	}

	public int getItemValue(String name){
		return itemService.getItemValue(name);
	}

    public Vector<String> getUsage(String from, String to) throws IOException {
	    return ListToVector(itemService.getUsage(Long.parseLong(from), Long.parseLong(to)));
    }
}
package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.repository.UsageRepository;
import me.laszloszoboszlai.service.ItemService;
import me.laszloszoboszlai.service.ItemServiceImpl;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MaintanancePanel {
    private ItemService itemService = new ItemServiceImpl();



    /**
     *  Prints the status of the machine
     */
	public Map<String, Long> getStatus(){
		return itemService.getStatus();
	}
//
//	public void printCapacity(){
//        itemService.printCapacity();
//	}


    public Map<String, Long> getCapacity(){
	 return itemService.getCapacity();
	}

	public void setCapacity(String name, long value) throws IOException {
        itemService.setCapacity(name, value);
	}
	public void emptySlot(int slot) throws IOException { itemService.emptySlot(slot);}

	public void closeConnection(){
        itemService.closeConnection();
	}

	public void changeItemValue(String name, int value) {
        itemService.changeItemValue(name,value);
	}

	public int getItemValue(String name){
		return itemService.getItemValue(name);
	}

    public ArrayList<Document> getUsage(long from, long to) throws IOException {
	    return itemService.getUsage(from, to);
    }
}
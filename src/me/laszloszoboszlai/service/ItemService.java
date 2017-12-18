package me.laszloszoboszlai.service;

import me.laszloszoboszlai.model.Item;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface ItemService {
    Map<String, Long> getStatus();
    String emptySlot(int slot)throws IOException;
    String changeItemValue(String name, int value);

    String closeConnection();

    public void recordUsage(Map<String, Item> items)throws IOException;
    public void recordDeposit(Map<String, Item> items) throws IOException;
    boolean isFull(String itemName);
    public int getItemValue(String name);
    Map<String, Long> getCapacity();
    void setCapacity(String name, long value) throws IOException;

    ArrayList<Document> getUsage(long from, long to) throws IOException;
}

package me.laszloszoboszlai.controller;

import java.io.IOException;
import java.util.Map;

public interface DepositItemReceiverInterface{
    String classifyItem(int slot);
    Map<String, Long> getStatus();
    String printReceipt() throws IOException;
    String emptySlot(int slot)throws IOException;
    String closeConnection();
    String changeItemValue(String name, int value);
    public int getItemValue(String name);
    Map<String, Long> getCapacity();
    void setCapacity(String name, long value) throws IOException;
}

//for maintanance deposit status , emptyslot, value = changeItemval
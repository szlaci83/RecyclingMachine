package me.laszloszoboszlai.service;

import java.io.IOException;
import java.util.Map;

public interface DepositItemReceiverInterface{
    String classifyItem(int slot);
    String printReceipt() throws IOException;
}

//for maintanance deposit status , emptyslot, value = changeItemval
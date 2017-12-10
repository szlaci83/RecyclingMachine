package me.laszloszoboszlai.controller;

import java.io.IOException;

public interface DepositItemReceiverInterface {
    String classifyItem(int slot);
    String printStatus();
    String printCapacity();
    String printReceipt() throws IOException;
    String emptySlot(int slot)throws IOException;
    String closeConnection();
}

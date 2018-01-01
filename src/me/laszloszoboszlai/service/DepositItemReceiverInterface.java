package me.laszloszoboszlai.service;

import java.io.IOException;

public interface DepositItemReceiverInterface {
    String classifyItem(int slot);

    String printReceipt() throws IOException;
}
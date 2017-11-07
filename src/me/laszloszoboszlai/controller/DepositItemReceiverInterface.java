package me.laszloszoboszlai.controller;

import java.io.IOException;

public interface DepositItemReceiverInterface {
    void classifyItem(int slot);
    void printStatus();
    void printReceipt();
}

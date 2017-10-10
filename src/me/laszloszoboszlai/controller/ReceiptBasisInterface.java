package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.domain.DepositItem;

public interface ReceiptBasisInterface {
    public void addItem(DepositItem item);

    public String computeSum();
}

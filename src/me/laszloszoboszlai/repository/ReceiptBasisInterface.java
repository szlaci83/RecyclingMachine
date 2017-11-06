package me.laszloszoboszlai.repository;

import me.laszloszoboszlai.model.DepositItem;

/**
 * Interface implemented by the repository classes in the system.
 * @author Laszlo Szoboszlai
 */
public interface ReceiptBasisInterface {
    /**
     * @param item an item that has been inserted into the machine (such as can, bottle, crate).
     */
    public void addItem(DepositItem item);

    /**
     * Calculates a summary based on the items inserted.
     */
    public String computeSum();


    /**
     *  Checks if the machine is full.
     */
    public boolean isFull();

}

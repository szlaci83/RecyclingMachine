package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.domain.DepositItem;

import java.util.HashMap;
import java.util.Map;

public class AggregatedVATReceipt implements ReceiptBasisInterface {

    Map<DepositItem, Integer> items = new HashMap<>();

    private float VAT;

    @Override
    public void addItem(DepositItem item) {
      if (items.containsKey(item)) {
          int value = items.get(item).intValue();
          items.put(item, Integer.valueOf(value +1));
        }
    }

    @Override
    public String computeSum() {
        String receipt = "";
        int sum = 0;

        items.entrySet().stream().forEach(item ->
                System.out.println(item.getKey() + ": " + item.getValue())
        );

//        for (MapEntry item : items.entrySet()) {
//            int itemTotal = item.value * item.getValue();
//            receipt = receipt + item.getClass().getSimpleName() + " : " +  itemTotal;
//            receipt = receipt + System.getProperty("line.separator");
//            sum += itemTotal;
//        }

        receipt = receipt + "Total: " + sum;
        return receipt;
    }

    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

    public float getVAT() {
        return VAT;
    }

}

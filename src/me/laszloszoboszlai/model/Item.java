package me.laszloszoboszlai.model;

/**
 * Common parent class of all recyclable items, extends the DepositItem abstract class,
 * but it can be instantiated.
 */
public class Item extends DepositItem{
    public Item(){
    }

    public int getValue(){
        return this.value;
    }
    @Override
    public String toString() {
        return  //"name : " + this.getName() +
                "\"count\" = "+ this.getCount() +
                ", \"value\" = "+ this.value;
    }
}
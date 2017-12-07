package me.laszloszoboszlai.model;

public class Item extends DepositItem{
    public Item(){

    }
    @Override
    public String toString() {
        return  //"name : " + this.getName() +
                "\"count\" : "+ this.getCount() +
                ", \"value\" : "+ this.value;
    }
}

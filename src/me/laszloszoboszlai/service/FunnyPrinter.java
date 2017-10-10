package me.laszloszoboszlai.service;

public class FunnyPrinter implements PrinterInterface{

    @Override
    public void print(String str) {
         System.out.println("This is funny!!!");

    }
}

package me.laszloszoboszlai.view;

/**
 * Interface to be implemented by the printer classes of the system.
 * @author Laszlo Szoboszlai
 */
public interface PrinterInterface {
    /**
     * Print method to be implemented by all the implementing classes.
     * @param str the String to be printed.
     */
    void print(String str);
}
package me.laszloszoboszlai.domain;

/**
 * Domain class representing a recyclable bottle
 * @author Laszlo Szoboszlai
 *
 */
public class Bottle extends DepositItem {
	static int weight = 10; 
	static int size = 8; 
	/**
	 * Bottle a cartoon with value 18
	 */
	public Bottle() { 
		value = 18; 
	}

    public static int getSize() {
        return size;
    }

    public static int getWeight(){
        return weight;
    }
}

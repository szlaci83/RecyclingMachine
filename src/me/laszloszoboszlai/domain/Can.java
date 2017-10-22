package me.laszloszoboszlai.domain;

/**
 * Domain class representing a recyclable can
 * @author Laszlo Szoboszlai
 */
public class Can extends DepositItem {
	static int weight = 4; 
	static int size = 5; 
	/**
	 * Creates a can with value 16.
	 */
	public Can() { 
		value = 16; 
	}
	public int getSize() {
		return size;
	}

	public  int getWeight(){
		return weight;
	}

}

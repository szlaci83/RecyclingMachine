package me.laszloszoboszlai.domain;

/**
 * Domain class representing a recyclable cartoon
 * @author Laszlo Szoboszlai
 */
public class Cartoon extends DepositItem {
	static int weight = 20;
	static int size = 16;
	/**
	 * Creates a cartoon with value 28
	 */
	public Cartoon() {
		value = 28;
	}

	public int getSize() {
		return size;
	}

	public  int getWeight(){
		return weight;
	}
}

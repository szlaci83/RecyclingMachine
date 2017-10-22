package me.laszloszoboszlai.domain;

/**
 * Domain class representing a recyclable crate
 * @author Laszlo Szoboszlai
 */
public class Crate extends DepositItem {
	static int weight = 1516;
	static int size = 90;

	/**
	 * Creates a crate with value 42
	 */
	public Crate() {
		value = 42;
	}

	public static int getSize() {
		return size;
	}

	public static int getWeight(){
		return weight;
	}

}

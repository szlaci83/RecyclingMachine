package me.laszloszoboszlai.model;

/**
 * Abstract class representing a recyclable item
 * @author Laszlo Szoboszlai
 */
public abstract class DepositItem {
	public int number;
	public int value;

	public String getName(){
		return this.getClass().getSimpleName();
	}
}

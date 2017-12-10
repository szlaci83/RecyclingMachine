package me.laszloszoboszlai.model;

/**
 * Abstract class representing a recyclable item
 * @author Laszlo Szoboszlai
 */
public abstract class DepositItem {
	public int number;
	public int value;

	public DepositItem(){
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private long count;

	public String getName(){
		return this.getClass().getSimpleName();
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

package me.laszloszoboszlai.domain;

/**
 * @author Marc Conrad
 *
 */
public abstract class DepositItem {
	public int number;
	public int value;

	public String getName(){
		return this.getClass().getSimpleName();
	}
}

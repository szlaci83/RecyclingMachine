package me.laszloszoboszlai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

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

	/**
	 * Method to instantiate the Cartoon objects from a .json file
	 * @return instance of the Cartoon object
	 */
	public static Cartoon getFromJson() {
		Cartoon cartoon = null;
		try (Reader reader = new InputStreamReader(Cartoon.class.getResourceAsStream("Cartoon.json"), "UTF-8")) {
			Gson gson = new GsonBuilder().create();
			cartoon =  gson.fromJson(reader, Cartoon.class);
		} catch (IOException e) {
			System.out.print(e);
		}
		return cartoon;
	}


	public Cartoon(int value) {
		this.value = value;
	}

	public static int getSize() {
		return size;
	}

	public static int getWeight(){
		return weight;
	}
}

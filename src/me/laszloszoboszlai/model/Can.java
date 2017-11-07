package me.laszloszoboszlai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

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

	public static Can getFromJson() {
		Can can = null;
		try (Reader reader = new InputStreamReader(Can.class.getResourceAsStream("Can.json"), "UTF-8")) {
			Gson gson = new GsonBuilder().create();
			can =  gson.fromJson(reader, Can.class);
		} catch (IOException e) {
			System.out.print(e);
		}
		return can;
	}

	public Can(int value) {
		this.value = value;
	}

	public static int getSize() {
		return size;
	}

	public static int getWeight(){
		return weight;
	}

}

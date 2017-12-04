package me.laszloszoboszlai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Domain class representing a recyclable crate
 * @author Laszlo Szoboszlai
 */
public class Crate extends Item {
	static int weight = 1516;
	static int size = 90;

	/**
	 * Method to instantiate the Crete objects from a .json file
	 * @return instance of the Crete object
	 */
	public static Crate getFromJson() {
		Crate crate = null;
		try (Reader reader = new InputStreamReader(Crate.class.getResourceAsStream("Crate.json"), "UTF-8")) {
			Gson gson = new GsonBuilder().create();
			crate =  gson.fromJson(reader, Crate.class);
		} catch (IOException e) {
			System.out.print(e);
		}
		return crate;
	}

	/**
	 * Creates a crate with value 42
	 */
	public Crate() {
		this.value = 42;
	}

	public static int getSize() {
		return size;
	}

	public static int getWeight(){
		return weight;
	}

}

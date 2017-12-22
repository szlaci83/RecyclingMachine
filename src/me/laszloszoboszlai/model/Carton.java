package me.laszloszoboszlai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Domain class representing a recyclable carton
 * @author Laszlo Szoboszlai
 */
public class Carton extends Item {
	static int weight = 20;
	static int size = 16;
	/**
	 * Creates a cartoon with value 28
	 */
	public Carton() {
		value = 28;
	}

	/**
	 * Method to instantiate the Carton objects from a .json file
	 * @return instance of the Carton object
	 */
	public static Carton getFromJson() {
		Carton carton = null;
		try (Reader reader = new InputStreamReader(Carton.class.getResourceAsStream("Carton.json"), "UTF-8")) {
			Gson gson = new GsonBuilder().create();
			carton =  gson.fromJson(reader, Carton.class);
		} catch (IOException e) {
			System.out.print(e);
		}
		return carton;
	}


	public Carton(int value) {
		this.value = value;
	}

	public static int getSize() {
		return size;
	}

	public static int getWeight(){
		return weight;
	}
}

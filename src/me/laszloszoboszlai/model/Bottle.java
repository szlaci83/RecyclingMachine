package me.laszloszoboszlai.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Domain class representing a recyclable bottle
 * @author Laszlo Szoboszlai
 *
 */
public class Bottle extends DepositItem {
	static int weight = 10; 
	static int size = 8;

	/**
	 * Method to instantiate the Bottle objects from a .json file
	 * @return instance of the Bottle object
	 */
	public static Bottle getFromJson() {
		Bottle bottle = null;
		try (Reader reader = new InputStreamReader(Bottle.class.getResourceAsStream("Bottle.json"), "UTF-8")) {
			Gson gson = new GsonBuilder().create();
			bottle =  gson.fromJson(reader, Bottle.class);
		} catch (IOException e) {
			System.out.print(e);
		}
		return new Bottle();
	}

	/**
	 * Bottle a cartoon with value 18
	 */
	public Bottle() { 
		value = 18; 
	}

	public Bottle(int value) {
		this.value = value;
	}

    public static int getSize() {
        return size;
    }

    public static int getWeight(){
        return weight;
    }
}

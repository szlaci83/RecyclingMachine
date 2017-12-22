package me.laszloszoboszlai.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.laszloszoboszlai.model.Item;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository class to store the information about each item's properties. (value, capacity of storage etc..)
 * The implementation is a simple .json format file storage.
 */
public class ItemRepository {

   // private static final String DEPOSITED_PATH = "/home/laszlo/Projects/RecyclingMachine/deposited.json";
   // private static final String CAPACITY_PATH = "/home/laszlo/Projects/RecyclingMachine/capacity.json";
   // private static final String ITEMS_PATH = "/home/laszlo/Projects/RecyclingMachine/src/me/laszloszoboszlai/model/";

    //WIN settings
    private static final String DEPOSITED_PATH = "D:\\github.com\\RecyclingMachine\\deposited.json";
    private static final String CAPACITY_PATH = "D:\\github.com\\RecyclingMachine\\capacity.json";
    private static final String ITEMS_PATH = "D:\\github.com\\RecyclingMachine\\src\\me\\laszloszoboszlai\\model\\";

    private static final String MachineID = "1";


    /**
     * Saves the items' Map representation into the relevant file as a .json object.
     * @param items the items' Map representation.
     * @param name name of the file to store it.
     * @throws IOException if there is a file I/O error.
     */
    public void saveItems(Map items, String name) throws IOException {
        Writer writer = null;
        if (name.equals("deposited")){
            writer = new FileWriter(DEPOSITED_PATH);
        } else {
            writer = new FileWriter(CAPACITY_PATH);
        }
        Gson gson = new GsonBuilder().create();

        gson.toJson(items, writer);
        writer.close();
    }

    /**
     * Loads the number of deposited items from a file into a HashMap.
     * @return the Hashmap containing the names -> items.
     */
    public HashMap<String, Item> loadItems() {
        HashMap<String, Item> items = null;
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(DEPOSITED_PATH));
            Type type = new TypeToken<HashMap<String, Item>>(){}.getType();
            items = gson.fromJson(br, type);

        }catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }
        return items;
    }

    /**
     * Loads the capacity for each item from a file to a HashMap.
     * @return the HashMap containing the names -> Capacity (piece).
     */
    public HashMap<String, Long> loadCapacity() {
        HashMap<String, Long> items = null;
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(CAPACITY_PATH));
            Type type = new TypeToken<HashMap<String, Long>>(){}.getType();
            items = gson.fromJson(br, type);

            //System.out.println(items);

        }catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }
        return items;
    }

    /**
     * Saves the capacity for each item to a file from a HashMap.
     * @param capacity Hashmap containing itemName -> capacity (piece).
     */
    public void saveCapacity(HashMap<String, Long> capacity) {
        Gson gson = new GsonBuilder().create();
        try {
            Writer writer = new FileWriter(CAPACITY_PATH);
            gson.toJson(capacity, writer);
            writer.close();
        }catch (IOException exc) {
            System.out.println(exc);
        }
    }

    /**
     * Changes the value of a given item.
     * @param name the item's value to be changed.
     * @param newValue the new value of the item.
     */
    public void changeValue(String name, int newValue){
        String path = ITEMS_PATH + name + ".json";
        HashMap<String, Integer> item = null;
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            item = gson.fromJson(bufferedReader, type);
        }catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }
        item.replace("value", newValue);
        try {
            Writer writer = new FileWriter(path);
            gson.toJson(item, writer);
            writer.close();
        }catch (IOException exc) {
            System.out.println(exc);
        }
    }

    /**
     * Gets the value of a given item.
     * @param name the name of the item.
     * @return the value of the item.
     */
    public int getValue(String name) {
        System.out.println(name);
        String path = ITEMS_PATH + name + ".json";
        HashMap<String, Integer> item = null;
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            item = gson.fromJson(bufferedReader, type);
        }catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }
        return item.get("value");
    }
}
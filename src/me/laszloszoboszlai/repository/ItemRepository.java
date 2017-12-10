package me.laszloszoboszlai.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.laszloszoboszlai.model.Item;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ItemRepository implements ItemRepositoryInterface {

    private static final String DEPOSITED_PATH = "/home/laszlo/Projects/RecyclingMachine/deposited.json";
    private static final String CAPACITY_PATH = "/home/laszlo/Projects/RecyclingMachine/capacity.json";
    private static final String ITEMS_PATH = "/home/laszlo/Projects/RecyclingMachine/src/me/laszloszoboszlai/model/";

    //WIN settings
   // private static final String DEPOSITED_PATH = "";
   // private static final String CAPACITY_PATH = "";

    private static final String MachineID = "1";


    @Override
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

    @Override
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

    @Override
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

    @Override
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
}
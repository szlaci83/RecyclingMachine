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

    @Override
    public void saveItems(Map items) throws IOException {
        Gson gson = new GsonBuilder().create();
        Writer writer = new FileWriter(DEPOSITED_PATH);
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


}

package me.laszloszoboszlai.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;

/* Helper method to write the Model classes to JSON */
public class GsonWriter {
    private static final String ITEMS_PATH = "/home/laszlo/Projects/RecyclingMachine/src/me/laszloszoboszlai/model/";
    private static final String CAPACITY_PATH = "/home/laszlo/Projects/RecyclingMachine/capacity.json";

    public static void changeValue(String name, int newValue){
        String path = ITEMS_PATH + name + ".json";
        HashMap<String, Integer> item = null;
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            item = gson.fromJson(bufferedReader, type);
            //System.out.println(item);
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












    public static void main(String[] args) throws IOException {

    changeValue("Bottle", 12);
//        HashMap<String, Long> items = new HashMap<>();
//        items.put("Bottle", 500L);
//        items.put("Crate", 500L);
//        items.put("Can", 500L);
//        items.put("Carton", 500L);
//
//            Gson gson = new GsonBuilder().create();
//            Writer writer = new FileWriter(CAPACITY_PATH);
//            gson.toJson(items, writer);
//            writer.close();

    }
}
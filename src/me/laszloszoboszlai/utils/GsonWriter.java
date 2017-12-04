package me.laszloszoboszlai.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.laszloszoboszlai.model.Crate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/* Helper method to write the Model classes to JSON */
public class GsonWriter {
    private static final String CAPACITY_PATH = "/home/laszlo/Projects/RecyclingMachine/capacity.json";
    public static void main(String[] args) throws IOException {


        HashMap<String, Long> items = new HashMap<>();
        items.put("Bottle", 500L);
        items.put("Crete", 500L);
        items.put("Can", 500L);
        items.put("Cartoon", 500L);

            Gson gson = new GsonBuilder().create();
            Writer writer = new FileWriter(CAPACITY_PATH);
            gson.toJson(items, writer);
            writer.close();

    }
}
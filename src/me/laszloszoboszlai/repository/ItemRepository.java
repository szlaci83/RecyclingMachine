package me.laszloszoboszlai.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.laszloszoboszlai.model.Bottle;

import java.io.*;
import java.util.Map;

public class ItemRepository implements ItemRepositoryInterface {
    @Override
    public void setItems(Map items) throws IOException {
        Gson gson = new GsonBuilder().create();
        Writer writer = new FileWriter("deposited.json");
        gson.toJson(items, writer);
        writer.close();
    }

    @Override
    public Map getItems() {
        Map items = null;
        try (Reader reader = new InputStreamReader(Bottle.class.getResourceAsStream("deposited.json"), "UTF-8")) {
            Gson gson = new GsonBuilder().create();
            items =  gson.fromJson(reader, Map.class);
        } catch (IOException e) {
            System.out.print(e);
        }
        return items;
    }
}

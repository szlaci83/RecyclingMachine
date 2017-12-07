package me.laszloszoboszlai.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.laszloszoboszlai.model.Item;
import org.bson.Document;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ItemRepository implements ItemRepositoryInterface {

    private static final String DEPOSITED_PATH = "/home/laszlo/Projects/RecyclingMachine/deposited.json";
    private static final String CAPACITY_PATH = "/home/laszlo/Projects/RecyclingMachine/capacity.json";
    private static final String MachineID = "1";

    private MongoClient connectToMongo(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient;
    }

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
    public void recordUsage(Map items) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MongoClient client = connectToMongo();
        MongoDatabase database = client.getDatabase("recyclingMachine");
        MongoCollection<Document> usageCollection = database.getCollection("usage" + MachineID);
        usageCollection.insertOne(new Document().append(date.toString(), items.toString()));
        client.close();

//        Writer writer = null;
//        File f = new File(USAGE_PATH);
//
//        if (!f.exists()) {
//            writer = new FileWriter(USAGE_PATH);
//            writer.append("[");
//            writer.close();
//        }
//        writer = new FileWriter(USAGE_PATH, true);
//        Gson gson = new GsonBuilder().create();
//        Map<Date, Map> usage =  new HashMap<Date, Map>();
//        usage.put(date, items);
//
//        gson.toJson(usage, writer);
//        writer.append(",\n");
//        writer.close();
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

package me.laszloszoboszlai.repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.laszloszoboszlai.model.Item;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class UsageRepository{

    private static final String MachineID = "2";
    MongoClient client;
    MongoDatabase database;

    public UsageRepository(){
        this.client = new MongoClient("localhost", 27017);
        this.database = client.getDatabase("recyclingMachine");
        //Omit logs
        java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
    }


    public ArrayList<Document> findMany(BasicDBObject query) throws IOException {
        MongoCollection<Document> usageCollection = database.getCollection("usage" + MachineID);
        ArrayList<Document> result = new ArrayList<>();
        usageCollection.find(query).
                into(result);
        return result;
    }

    public ArrayList<Document> findBetweenDates(Long from, Long to) throws IOException {
        BasicDBObject getQuery = new BasicDBObject();
        BasicDBObject gte = new BasicDBObject("$gte", from);
        if (to != null){
            gte.append("$lte", to);
        }
        getQuery.put("Timestamp", gte);
        return findMany(getQuery);
    }

    public void insertOne(Map<String, Item> items) throws IOException {
        MongoDatabase database = client.getDatabase("recyclingMachine");
        MongoCollection<Document> usageCollection = database.getCollection("usage" + MachineID);

        Document record = new Document();
        record.append("Timestamp", new Date().getTime());
        Gson gson = new Gson();
        String json = new String();
        for (String itemName : items.keySet()){
            Map<String, String> entry = new HashMap<>();
            entry.put("name", itemName);
            entry.put("count" , Long.toString(items.get(itemName).getCount()));
            entry.put("value" , Integer.toString(items.get(itemName).getValue()));
            json += gson.toJson(entry);
        }
        record.append("items", json);
        usageCollection.insertOne(record);
        client.close();
    }

    public void closeConnection(){
        client.close();
    }

    public static void main(String[] args) throws IOException {
        UsageRepository usageRepository = new UsageRepository();
        ArrayList<Document> documents = usageRepository.findBetweenDates(Long.parseLong("1513036391402"), Long.parseLong("1513037372940"));
        for (Document document : documents){
            System.out.println(document.toJson().toString());
        }
    }
}
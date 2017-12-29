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

/**
 * Repository handling the usage recording and retrieving of the recycling machine.
 * The usage information is stored in MongoDB (a NoSQL document storage database)
 *
 * @author Laszlo Szoboszlai
 */
public class UsageRepository{

    private static final String MachineID = "1";
    MongoClient client;
    MongoDatabase database;

    public UsageRepository(){
        this.client = new MongoClient("localhost", 27017);
        this.database = client.getDatabase("recyclingMachine");
        //Display logs from MongoDB
        //java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
    }

    /**
     * Method to retreive many matching records to the query.
     * @param query a MongoDB query.
     * @return an ArrayList of MongoDb Documents matching the given query.
     * @throws IOException if there is an exception during retrieval from the database.
     */
    public ArrayList<Document> findMany(BasicDBObject query) throws IOException {
        MongoCollection<Document> usageCollection = database.getCollection("usage" + MachineID);
        ArrayList<Document> result = new ArrayList<>();
        usageCollection.find(query).
                into(result);
        return result;
    }

    /**
     * Method to retrieve records from the database between two dates.
     * @param from the from data in unix epoch time.
     * @param to the to date in unix epoch time.
     * @return an ArrayList of MongoDb Documents between the given dates.
     * @throws IOException if there is an exception during retrieval from the database.
     */
    public ArrayList<Document> findBetweenDates(Long from, Long to) throws IOException {
        BasicDBObject getQuery = new BasicDBObject();
        BasicDBObject gte = new BasicDBObject("$gte", from);
        if (to != null){
            gte.append("$lte", to);
        }
        getQuery.put("Timestamp", gte);
        return findMany(getQuery);
    }

    /**
     * Method to insert a single record (Document) into the database.
     * @param items Map of items to be inserted. (ItemName -> Item)
     * @throws IOException if there is an exception during retrieval from the database.
     */
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
    }

    /**
     * Method to close the connection to the database.
     */
    public void closeConnection(){
        client.close();
    }
}
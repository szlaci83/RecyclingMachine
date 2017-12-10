package me.laszloszoboszlai.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;

public class UsageRepository  implements UsageRepositoryInterface{

    private static final String MachineID = "1";
    MongoClient client;
    MongoDatabase database;

    public UsageRepository(){
        this.client = new MongoClient("localhost", 27017);
        this.database = client.getDatabase("recyclingMachine");
        //Ommit logs
        java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
    }


    public Document findAll() throws IOException {
        MongoCollection<Document> usageCollection = database.getCollection("usage" + MachineID);
        //TODO: modify it to correlate
        Document result = usageCollection.find().first();
        client.close();
        return result;
    }

    @Override
    public void insertOne(Map items) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MongoDatabase database = client.getDatabase("recyclingMachine");
        MongoCollection<Document> usageCollection = database.getCollection("usage" + MachineID);
        usageCollection.insertOne(new Document().append(date.toString(), items.toString()));
        client.close();
    }

    public void closeConnection(){
        client.close();
    }
}
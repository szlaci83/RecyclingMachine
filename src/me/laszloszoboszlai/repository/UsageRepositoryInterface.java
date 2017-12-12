package me.laszloszoboszlai.repository;

import com.mongodb.BasicDBObject;
import me.laszloszoboszlai.model.Item;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface UsageRepositoryInterface  {

    public ArrayList<Document> findMany(BasicDBObject query) throws IOException;

    public void insertOne(Map<String, Item> map) throws IOException;

    public ArrayList<Document> findBetweenDates(Long from, Long to) throws IOException;

    public void closeConnection();
}

package me.laszloszoboszlai.repository;

import org.bson.Document;

import java.io.IOException;
import java.util.Map;

public interface UsageRepositoryInterface  {

    public Document findAll() throws IOException;

    public void insertOne(Map map) throws IOException;
}

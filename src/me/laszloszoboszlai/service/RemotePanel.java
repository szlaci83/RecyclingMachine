package me.laszloszoboszlai.service;

import me.laszloszoboszlai.repository.UsageRepository;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;

public class RemotePanel {
    private UsageRepository usageRepository = new UsageRepository();

    public ArrayList<Document> getUsage(long from, long to) throws IOException {
        return usageRepository.findBetweenDates(from, to);
    }
}
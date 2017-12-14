package me.laszloszoboszlai.service;

import me.laszloszoboszlai.repository.UsageRepository;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class RemotePanel {
    private UsageRepository usageRepository = new UsageRepository();

    public ArrayList<Document> getUsage(Date from, Date to) throws IOException {
        return usageRepository.findBetweenDates(from.getTime(), to.getTime());
    }

}

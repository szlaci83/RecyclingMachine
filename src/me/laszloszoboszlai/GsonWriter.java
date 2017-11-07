package me.laszloszoboszlai;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.laszloszoboszlai.model.Cartoon;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* Helper to write the Model classes to JSON */
public class GsonWriter {
    public static void main(String[] args) throws IOException {
        Writer writer = new FileWriter("Cartoon.json");
        Gson gson = new GsonBuilder().create();
        gson.toJson(new Cartoon(), writer);
        writer.close();
    }
}
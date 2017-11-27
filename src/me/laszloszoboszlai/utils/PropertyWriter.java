package me.laszloszoboszlai.utils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Helper class to write properties in a property file.
 */
public class PropertyWriter {
    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("usage.properties");

            // set the properties value
            prop.setProperty("Can", "0");
            prop.setProperty("Cartoon", "0");
            prop.setProperty("Crate", "0");
            prop.setProperty("Bottle", "0");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
package me.laszloszoboszlai.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserRepository implements UserRepositoryInterface {

    @Override
    public String getUserByName(String userName) {
        String pass = null;
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = new FileInputStream("config.properties");
            // load a properties file
            prop.load(input);
            pass =  prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    return pass;
    }
}

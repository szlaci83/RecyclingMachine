package me.laszloszoboszlai.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Simple User repository implementation. Stores a single password in a property file.
 * In a fully working system this should be replaced by a database connection.
 */
public class UserRepository implements UserRepositoryInterface {

    @Override
    /**
     * Returns the password for the given user
     * @param userName the name of the user we want the password for
     * @return the password of the user
     */
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

package me.laszloszoboszlai.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Simple User repository implementation. Stores a single password in a property file.
 * In a fully working system this should be replaced by a database connection.
 */
public class UserRepository {

    private static final String PASSWORD_PATH = "D:\\github.com\\RecyclingMachine\\password.properties";


    /**
     * Returns the password for the given user
     * @param userName the name of the user we want the password for
     * @return the password of the user
     */
    public String getUserByName(String userName) {
        String password = null;
        Properties users = new Properties();
        InputStream inputFile = null;
        try {
            inputFile = new FileInputStream(PASSWORD_PATH);
            users.load(inputFile);
            password = users.getProperty(userName);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return password;
    }
}

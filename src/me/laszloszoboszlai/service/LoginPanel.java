package me.laszloszoboszlai.service;

import me.laszloszoboszlai.repository.UserRepository;
import me.laszloszoboszlai.utils.MD5Hasher;

import java.security.NoSuchAlgorithmException;

public class LoginPanel {


    // session cookie to identify the individual users
    private String sessioncookie = "notset";
    UserRepository userRepository = new UserRepository();

    /**
     * Method to log users in, users first need to login in order to use the system.
     * @param passwd the password of the user
     * @return returns the sessioncookie if the login was success "wrong" otherwise
     */
    public String login(String username, String passwd) throws NoSuchAlgorithmException {
         if( MD5Hasher.getHash(passwd).equals(userRepository.getUserByName(username).toUpperCase())){
            sessioncookie = "Random"+Math.random();
            return sessioncookie;
        } else {
            return "wrong";
        }
    }

    public boolean isLoggedIn(){
        return !sessioncookie.equals("notset");
    }
}
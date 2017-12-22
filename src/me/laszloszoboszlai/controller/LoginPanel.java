package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.repository.UserRepository;
import me.laszloszoboszlai.utils.MD5Hasher;

import java.security.NoSuchAlgorithmException;

/**
 * Login controller of the recycling machine.
 * Functionality to logging in to the system can be reached from here.
 * @author Laszlo Szoboszlai
 */
public class LoginPanel {

    // session cookie to identify the individual users
    private String sessioncookie = "notset";
    UserRepository userRepository = new UserRepository();

    /**
     * Method to log users in, admin and maintanace person should first need to login in order to use the restricted
     * part of the system.
     * @param password the password of the user
     * @return returns the sessioncookie if the login was success "wrong" otherwise
     */
    public String login(String username, String password) {
        String pass = userRepository.getUserByName(username);
        if (pass == null){
            return "wrong";
        }
         if( password.equals(pass.toUpperCase())){
            sessioncookie = username + Math.random();
            return sessioncookie;
        } else {
            return "wrong";
        }
    }

    /**
     * Checks if the user is loggged in.
     * @param username the name of the user to check.
     * @return true if the user is logged in, false otherwise.
     */
    public boolean isLoggedIn(String username){
        return sessioncookie.contains(username);
    }

    /**
     * Logs the current user out.
     * @return
     */
    public boolean logout() {
        this.sessioncookie = "notset";
        return true;
    }
}
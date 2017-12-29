package me.laszloszoboszlai.controller;

import me.laszloszoboszlai.repository.UserRepository;
import me.laszloszoboszlai.utils.MD5Hasher;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Login controller of the recycling machine.
 * Functionality to logging in to the system can be reached from here.
 * @author Laszlo Szoboszlai
 */
public class LoginPanel {

    private static Map<String, String> tokens = new HashMap();
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
         if( password.equals(pass)){
            String sessioncookie = username + Math.random();
            tokens.put(username, username + Math.random());
            return sessioncookie;
        } else {
            return "wrong";
        }
    }

    /**
     * Validates a given user-token
     * @param token the token to be validated
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token){
        String result = tokens.entrySet().stream()
                .filter(map -> token.equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());
        return result.isEmpty();
    }

    /**
     * Logs the given user out.
     * @return true
     */
    public boolean logout(String token) {
        tokens = tokens.entrySet().stream()
                .filter(map -> !token.equals(map.getValue()))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        return true;
    }
}
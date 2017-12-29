package me.laszloszoboszlai.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher{

    /**
     * Helper class to hash texts using MD5 (basic) hashing algorithm
     * @param text the String to be hashed
     * @return the hashed String
     * @throws NoSuchAlgorithmException
     */
    public static String getHash(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(text.getBytes());
        byte[] digested = messageDigest.digest();
        String hashedText = DatatypeConverter
                .printHexBinary(digested);
        return hashedText;
    }
}
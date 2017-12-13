package me.laszloszoboszlai.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher{

    public static String getHash(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(text.getBytes());
        byte[] digest = messageDigest.digest();
        String hashedText = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hashedText;
    }
}
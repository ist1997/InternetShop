package utils;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashUtil {

    private static final Logger logger = Logger.getLogger(HashUtil.class);

    public static String getSHA512SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }
        return generatedPassword;
    }

    public static String generateSalt() {
        char[] arr = new char[6];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (new Random().nextInt(91) + 32);
        }
        return String.valueOf(arr);
    }
}

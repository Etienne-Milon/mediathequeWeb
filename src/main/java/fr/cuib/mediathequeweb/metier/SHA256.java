package fr.cuib.mediathequeweb.metier;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256 {

    public static void main(String[] args) throws NoSuchAlgorithmException{
        String passwortToHash = "password";
        String salt = getSalt();

        String securePassword = get_SHA_256_SecurePassword(passwordToHash, salt);
    }

    private static String get_SHA_256_SecurePassword(String passwordToHash, String Salt){

        String generatedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i< bytes.length; i++){
                sb.append(Integer.to)
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CUSTOM;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author HP
 */
public class PasswordHash {
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] inputHash = md.digest(inputPassword.getBytes());
	        String inputHashString = bytesToHex(inputHash);
	        return inputHashString.equals(storedHashedPassword);
	    } catch (NoSuchAlgorithmException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}
    
}

package org.example.service;

import java.security.MessageDigest;

public class VerifyIntegrityService {

    public String checkIntegrity(String input1, String input2) {
        String hash1 = getHashWord(input1);
        String hash2 = getHashWord(input2);
        if (hash1.equals(hash2)) {
            return "Integrity: Valid";
        } else {
            return "Integrity: Invalid / Corrupted";
        }
    }

    private String getHashWord(String word) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(word.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

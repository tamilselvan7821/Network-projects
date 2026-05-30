package org.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class PasswordBreachCheckerService {

    public Map<String, Object> checkPasswordBreach(String password) {
        Map<String, Object> result = new HashMap<>();
        try {
            String sha1 = getHash(password);
            result.put("sha1Hash", sha1);
            
            String prefix = sha1.substring(0, 5);
            String suffix = sha1.substring(5);
            
            result.put("prefix", prefix);
            result.put("suffix", suffix);
            
            URL url = new URL("https://api.pwnedpasswords.com/range/" + prefix);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            
            if (urlConnection.getResponseCode() == 200) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(":");
                        if (parts[0].equalsIgnoreCase(suffix)) {
                            int breachCount = Integer.parseInt(parts[1]);
                            result.put("isBreached", true);
                            result.put("breachCount", breachCount);
                            result.put("message", "⚠️ WARNING: This password has been found in " + breachCount + " data breaches! Please use a different password.");
                            result.put("recommendation", "Change this password immediately on all accounts where it is used.");
                            return result;
                        }
                    }
                }
                // Password not found in breach dataset
                result.put("isBreached", false);
                result.put("breachCount", 0);
                result.put("message", "✅ Good news! This password has not been found in known data breaches.");
                result.put("recommendation", "This password is safe to use.");
            } else {
                result.put("error", "Failed to connect to breach database. HTTP Status: " + urlConnection.getResponseCode());
                result.put("isBreached", false);
            }
        } catch (Exception e) {
            result.put("error", "Error checking password breach: " + e.getMessage());
            result.put("isBreached", false);
        }
        return result;
    }

    public String getHash(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) sb.append("0");
                sb.append(hex);
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("Error generating SHA-1 hash", e);
        }
    }
}


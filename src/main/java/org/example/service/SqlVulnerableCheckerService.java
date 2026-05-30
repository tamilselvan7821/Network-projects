package org.example.service;

import java.util.HashMap;
import java.util.Map;

public class SqlVulnerableCheckerService {
    private boolean patternMatch(String input){
        input = input.toLowerCase();
        String[] patterns = {"'", "--", ";","/*", "*/", " or ", " and ", "drop ", "select ","insert ", "delete ", "update ",
        "xp_", "union "};
        for(String p : patterns){
            if(input.contains(p)) return true;
        }
        return false;
    }
    public Map<String, Object> checkVulnerability(String username, String password) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean chk1 = patternMatch(username);
            boolean chk2 = patternMatch(password);

            // Analyze results
            result.put("username", username);
            result.put("password", password);

            if (!chk2 && !chk1) {
                result.put("vulnerableQuery", false);
                result.put("secureQuery", true);
                result.put("status", "Login successful and not vulnerable to SQL injection!");
                result.put("isVulnerable", false);
            } else {
                result.put("vulnerableQuery", false);
                result.put("secureQuery", false);
                result.put("status", "Warning: Vulnerable: Login Bypassed; Secure: Login Failed");
                result.put("isVulnerable", true);
                result.put("vulnerability", "SQL Injection vulnerability detected! The vulnerable query was bypassed but the secure query was not.");
            }
        } catch (Exception e) {
            result.put("error", "Error checking SQL vulnerability: " + e.getMessage());
            result.put("isVulnerable", false);
        }
        return result;
    }
}


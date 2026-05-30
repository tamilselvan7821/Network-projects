package org.example.service;

public class PasswordCheckerService {

    public String checkPasswordStrength(String password) {
        int points = 0;
        password = password.replaceAll(" ", "");
        if (password.length() >= 8) points++;
        if (password.matches(".*[A-Z].*")) points++;
        if (password.matches(".*[a-z].*")) points++;
        if (password.matches(".*\\d.*")) points++;
        if (password.matches(".*[!@#$%^&*()].*")) points++;
        if (points >= 5) return "Strong";
        if (points >= 3) return "Medium";
        return "Weak";
    }
}

package org.example.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResumeKeywordScannerService {

    public Map<String, Object> analyzeResume(String skills, String jobRole) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Normalize input
            skills = skills.toLowerCase().trim();
            jobRole = jobRole.toLowerCase().trim();

            // Replace commas with spaces for splitting
            skills = skills.replaceAll(",", " ");
            jobRole = jobRole.replaceAll(",", " ");

            // Convert to sets for comparison
            Set<String> desiredSkills = new HashSet<>(Arrays.asList(jobRole.split("\\s+")));
            Set<String> mySkills = new HashSet<>(Arrays.asList(skills.split("\\s+")));

            // Remove empty strings
            desiredSkills.remove("");
            mySkills.remove("");

            // Count matched skills
            int matchedCount = 0;
            Set<String> matchedSkills = new HashSet<>();
            Set<String> missingSkills = new HashSet<>();

            for (String skill : mySkills) {
                if (desiredSkills.contains(skill)) {
                    matchedCount++;
                    matchedSkills.add(skill);
                }
            }

            // Find missing skills
            for (String skill : desiredSkills) {
                if (!mySkills.contains(skill)) {
                    missingSkills.add(skill);
                }
            }

            // Calculate percentage
            int matchPercentage = desiredSkills.size() > 0 ? (matchedCount * 100) / desiredSkills.size() : 0;

            // Build result
            result.put("userSkills", mySkills);
            result.put("desiredSkills", desiredSkills);
            result.put("matchedSkills", matchedSkills);
            result.put("missingSkills", missingSkills);
            result.put("matchedCount", matchedCount);
            result.put("totalDesiredSkills", desiredSkills.size());
            result.put("matchPercentage", matchPercentage);
            result.put("status", "success");

            // Add recommendation
            if (matchPercentage >= 80) {
                result.put("recommendation", "🟢 Excellent match! You have most of the required skills for this role.");
            } else if (matchPercentage >= 60) {
                result.put("recommendation", "🟡 Good match! You have a solid foundation. Consider learning: " + String.join(", ", missingSkills));
            } else if (matchPercentage >= 40) {
                result.put("recommendation", "🟠 Fair match. You need to develop more skills in: " + String.join(", ", missingSkills));
            } else {
                result.put("recommendation", "🔴 Limited match. Significant skill development needed. Focus on: " + String.join(", ", missingSkills));
            }

        } catch (Exception e) {
            result.put("error", "Error analyzing resume: " + e.getMessage());
            result.put("status", "error");
        }
        return result;
    }
}


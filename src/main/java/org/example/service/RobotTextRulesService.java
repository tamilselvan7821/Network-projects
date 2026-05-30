package org.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotTextRulesService {

    public Map<String, List<String>> getRules(String link) {
        Map<String, List<String>> result = new HashMap<>();
        List<String> allowedRules = new ArrayList<>();
        List<String> disAllowedRules = new ArrayList<>();

        try {
            URL url = new URL(link + "/robots.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Allow:")) {
                    allowedRules.add(line.substring(line.indexOf(":") + 1).trim());
                } else if (line.startsWith("Disallow:")) {
                    disAllowedRules.add(line.substring(line.indexOf(":") + 1).trim());
                }
            }
            br.close();
        } catch (Exception e) {
            // If URL fails, try with HttpClient
            try {
                link = link + "/robots.txt";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(new URI(link)).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String content = response.body();
                String[] lines = content.split("\n");
                for (String line : lines) {
                    line = line.trim();
                    if (line.startsWith("Allow:")) {
                        allowedRules.add(line.substring(line.indexOf(":") + 1).trim());
                    } else if (line.startsWith("Disallow:")) {
                        disAllowedRules.add(line.substring(line.indexOf(":") + 1).trim());
                    }
                }
            } catch (Exception ex) {
                result.put("error", List.of("Error in fetching robots.txt file: " + ex.getMessage()));
                return result;
            }
        }

        result.put("allowed", allowedRules);
        result.put("disallowed", disAllowedRules);
        return result;
    }
}

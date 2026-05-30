package org.example.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class QuotesService {

    public Map<String, String> getQuote(String query) {
        Map<String, String> result = new HashMap<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://zenquotes.io/api/random"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            // Simple parsing, assuming first quote
            String quote = json.split("\"q\":\"")[1].split("\"")[0];
            String author = json.split("\"a\":\"")[1].split("\"")[0];
            result.put("quote", quote);
            result.put("author", author);
        } catch (Exception e) {
            result.put("error", "Failed to fetch quote: " + e.getMessage());
        }
        return result;
    }
}

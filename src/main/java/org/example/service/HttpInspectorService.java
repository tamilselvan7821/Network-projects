package org.example.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpInspectorService {

    public Map<String, Object> inspectUrl(String url) {
        Map<String, Object> result = new HashMap<>();
        try {
            long start = System.currentTimeMillis();
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            long end = System.currentTimeMillis();
            result.put("statusCode", statusCode);
            result.put("responseTime", (end - start) + " ms");
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            Map<String, Object> cleanHeaders = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    // status line
                    cleanHeaders.put("status", entry.getValue().get(0));
                } else {
                    cleanHeaders.put(key, entry.getValue());
                }
            }
            result.put("headers", cleanHeaders);
        } catch (Exception e) {
            result.put("error", "Failed to inspect URL: " + e.getMessage());
        }
        return result;
    }
}

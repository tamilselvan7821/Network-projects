package org.example.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WebSiteUptimeCheckerService {

    public UptimeResult checkUptime(String url) {
        try {
            HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).header("User-Agent", "Mozilla/5.0").GET().build();
            long start = System.currentTimeMillis();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            long end = System.currentTimeMillis();
            int code = response.statusCode();
            boolean isUp = code >= 200 && code < 400;
            long timeTaken = end - start;
            return new UptimeResult(isUp, timeTaken, code);
        } catch (Exception e) {
            return new UptimeResult(false, -1, -1);
        }
    }

    public static class UptimeResult {
        public boolean isUp;
        public long responseTime;
        public int statusCode;

        public UptimeResult(boolean isUp, long responseTime, int statusCode) {
            this.isUp = isUp;
            this.responseTime = responseTime;
            this.statusCode = statusCode;
        }
    }
}

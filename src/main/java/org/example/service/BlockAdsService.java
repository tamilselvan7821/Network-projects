package org.example.service;

public class BlockAdsService {

    public String checkUrl(String url) {
        if (url.contains("ads") || url.contains("doubleclick")) {
            return "Blocked: URL contains ad-related content";
        } else {
            return "Allowed: URL is safe";
        }
    }
}

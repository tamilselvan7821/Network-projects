package org.example.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class UrlMetaExtractorService {

    public Map<String, Object> extractMetaData(String url) {
        Map<String, Object> meta = new HashMap<>();
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.title();
            meta.put("title", title);
            String description = document.select("meta[name=description]").attr("content");
            meta.put("description", description);
            Elements elements = document.select("a");
            int linkCount = elements.size();
            meta.put("linkCount", linkCount);
        } catch (Exception e) {
            meta.put("error", "Failed to extract metadata: " + e.getMessage());
        }
        return meta;
    }
}

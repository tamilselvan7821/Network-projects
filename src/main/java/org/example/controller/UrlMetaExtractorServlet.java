package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.UrlMetaExtractorService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UrlMetaExtractorServlet extends HttpServlet {

    private UrlMetaExtractorService service = new UrlMetaExtractorService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String url = input.get("urlMeta");

            if (url == null) {
                response.put("error", "URL is required");
            } else {
                Map<String, Object> meta = service.extractMetaData(url);
                response.putAll(meta);
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

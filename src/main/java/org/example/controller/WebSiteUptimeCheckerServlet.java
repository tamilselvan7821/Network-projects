package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.WebSiteUptimeCheckerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebSiteUptimeCheckerServlet extends HttpServlet {

    private WebSiteUptimeCheckerService service = new WebSiteUptimeCheckerService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String url = input.get("urlInput");

            if (url == null || url.trim().isEmpty()) {
                response.put("error", "URL is required");
            } else {
                WebSiteUptimeCheckerService.UptimeResult result = service.checkUptime(url);
                response.put("isUp", result.isUp);
                response.put("message", result.isUp ? "Website is up and running" : "Website is down");
                response.put("responseTime", result.responseTime + " ms");
                response.put("statusCode", result.statusCode);
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.RobotTextRulesService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotTextRulesServlet extends HttpServlet {

    private RobotTextRulesService service = new RobotTextRulesService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String url = input.get("robotUrl");

            if (url == null || url.isEmpty()) {
                response.put("error", "URL is required");
            } else {
                Map<String, List<String>> rules = service.getRules(url);
                if (rules.containsKey("error")) {
                    response.put("error", rules.get("error").get(0));
                } else {
                    response.put("allowed", rules.get("allowed"));
                    response.put("disallowed", rules.get("disallowed"));
                }
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.JsonValidateService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonValidateServlet extends HttpServlet {

    private JsonValidateService service = new JsonValidateService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String json = input.get("jsonInput");

            if (json == null || json.trim().isEmpty()) {
                response.put("error", "JSON input is required");
            } else {
                String prettyJson = service.validateAndPrettyPrint(json);
                response.put("valid", true);
                response.put("output", prettyJson);
            }
        } catch (Exception e) {
            response.put("valid", false);
            response.put("error", "Invalid JSON input: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

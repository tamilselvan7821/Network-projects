package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.VerifyIntegrityService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VerifyIntegrityServlet extends HttpServlet {

    private VerifyIntegrityService service = new VerifyIntegrityService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String input1 = input.get("input1");
            String input2 = input.get("input2");

            if (input1 == null || input2 == null) {
                response.put("error", "Both inputs are required");
            } else {
                String result = service.checkIntegrity(input1, input2);
                response.put("result", result);
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import org.example.service.PasswordCheckerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordCheckerServlet extends HttpServlet {

    private PasswordCheckerService service = new PasswordCheckerService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String password = input.get("passStrength");

            if (password == null) {
                response.put("error", "Password is required");
            } else {
                String strength = service.checkPasswordStrength(password);
                response.put("strength", strength);
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

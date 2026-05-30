package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.PasswordBreachCheckerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PasswordBreachCheckerServlet extends HttpServlet {

    private PasswordBreachCheckerService service = new PasswordBreachCheckerService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String password = input.get("breachPassword");

            if (password == null || password.isEmpty()) {
                Map<String, Object> error = Map.of("error", "Password cannot be empty");
                resp.getWriter().write(mapper.writeValueAsString(error));
                return;
            }

            Map<String, Object> result = service.checkPasswordBreach(password);
            resp.getWriter().write(mapper.writeValueAsString(result));
        } catch (Exception e) {
            Map<String, Object> error = Map.of("error", "An error occurred: " + e.getMessage());
            resp.getWriter().write(mapper.writeValueAsString(error));
        }
    }
}


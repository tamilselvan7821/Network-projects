package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.HashPasswordService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashPasswordServlet extends HttpServlet {

    private HashPasswordService service = new HashPasswordService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String action = input.get("action");
            if ("hash".equals(action)) {
                String username = input.get("username");
                String password = input.get("password");
                if (password == null || username == null || password.isEmpty() || username.isEmpty()) {
                    response.put("error", "Username and password are required");
                } else {
                    boolean add = service.addUser(username, password);
                    if(add) response.put("resp", "Successfully Account created");
                    else response.put("error", "Username already registered");
                }
            } else {
                String username = input.get("username");
                String password = input.get("password");
                if (username == null || password == null || password.isEmpty() || username.isEmpty()) {
                    response.put("error", "Username and password are required");
                } else {
                    response = service.login(username, password);
                }
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

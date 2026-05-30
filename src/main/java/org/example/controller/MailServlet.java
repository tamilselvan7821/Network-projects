package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.MailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MailServlet extends HttpServlet {

    private MailService service = new MailService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String to = input.get("to");
            String subject = input.get("subject");
            String text = input.get("text");

            if (to == null || subject == null || text == null) {
                response.put("error", "To, subject, and text are required");
            } else {
                String result = service.sendMail(to, subject, text);
                response.put("result", result);
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import org.example.service.LogReaderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class LogReaderServlet extends HttpServlet {

    private LogReaderService service = new LogReaderService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Collection<Part> parts = req.getParts();

            if (parts == null || parts.isEmpty()) {
                response.put("error", "Files are required");
            } else {
                Map<String, Integer> counts = service.readLog(parts);
                if (counts.containsKey("error") && counts.get("error") == 1) {
                    response.put("error", "Failed to read file");
                } else {
                    response.put("counts", counts);
                }
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

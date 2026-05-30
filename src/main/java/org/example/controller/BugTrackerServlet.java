package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Bug;
import org.example.service.BugTrackerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BugTrackerServlet extends HttpServlet {

    private BugTrackerService service = new BugTrackerService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String action = input.get("action");

            if ("add".equals(action)) {
                String title = input.get("title");
                String priority = input.get("priority");
                if (title != null && priority != null) {
                    Bug bug = service.addBug(title, priority);
                    response.put("bug", bug);
                    response.put("message", "Bug added successfully");
                } else {
                    response.put("error", "Title and priority are required");
                }
            } else if ("view".equals(action)) {
                List<Bug> bugs = service.getAllBugs();
                response.put("bugs", bugs);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(input.get("id"));
                String status = input.get("status");
                Bug bug = service.updateStatus(id, status);
                if (bug != null) {
                    response.put("bug", bug);
                    response.put("message", "Status updated");
                } else {
                    response.put("error", "Bug not found");
                }
            } else {
                response.put("error", "Invalid action");
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

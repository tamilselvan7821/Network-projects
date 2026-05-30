package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.ResumeKeywordScannerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ResumeKeywordScannerServlet extends HttpServlet {

    private ResumeKeywordScannerService service = new ResumeKeywordScannerService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String skills = input.get("resumeSkills");
            String jobRole = input.get("jobRoleSkills");

            if (skills == null || skills.isEmpty() || jobRole == null || jobRole.isEmpty()) {
                Map<String, Object> error = Map.of("error", "Skills and job role are required");
                resp.getWriter().write(mapper.writeValueAsString(error));
                return;
            }

            Map<String, Object> result = service.analyzeResume(skills, jobRole);
            resp.getWriter().write(mapper.writeValueAsString(result));
        } catch (Exception e) {
            Map<String, Object> error = Map.of("error", "An error occurred: " + e.getMessage());
            resp.getWriter().write(mapper.writeValueAsString(error));
        }
    }
}

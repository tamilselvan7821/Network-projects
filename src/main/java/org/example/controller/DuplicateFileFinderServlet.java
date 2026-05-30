package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import org.example.service.DuplicateFileFinderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class DuplicateFileFinderServlet extends HttpServlet {

    private DuplicateFileFinderService service = new DuplicateFileFinderService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Collection<Part> parts = req.getParts();
            Map<String, Object> result = new HashMap<>();
            if (parts == null || parts.isEmpty()) {
               result.put("error", "Directory path is required");
            }
            else{
                result = service.findDuplicates(parts);
            }
            resp.getWriter().write(mapper.writeValueAsString(result));
        } catch (Exception e) {
            Map<String, Object> error = Map.of("error", "An error occurred: " + e.getMessage());
            resp.getWriter().write(mapper.writeValueAsString(error));
        }
    }
}

package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import org.example.service.SearchEngineService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
public class SearchEngineServlet extends HttpServlet {

    private SearchEngineService service = new SearchEngineService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            String keyWord = req.getParameter("keyword").toLowerCase();
            Collection<Part> parts = req.getParts();
            if (parts == null || keyWord.isEmpty()) {
                response.put("error", "Directory path and keyword are required");
            } else {
                List<String> results = service.getMultipleFiles(keyWord, parts);
                response.put("results", results);
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

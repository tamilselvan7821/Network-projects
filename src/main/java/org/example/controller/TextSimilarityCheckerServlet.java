package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.TextSimilarityCheckerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextSimilarityCheckerServlet extends HttpServlet {

    private TextSimilarityCheckerService service = new TextSimilarityCheckerService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String text1 = input.get("text1");
            String text2 = input.get("text2");

            if (text1 == null || text2 == null) {
                response.put("error", "Both texts are required");
            } else {
                TextSimilarityCheckerService.SimilarityResult result = service.checkSimilarity(text1, text2);
                response.put("similarity", result.similarityPercentage + "%");
                response.put("commonWords", result.commonWords);
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}

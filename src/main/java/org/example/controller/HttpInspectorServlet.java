package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.HttpInspectorService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class HttpInspectorServlet extends HttpServlet {

    private HttpInspectorService service = new HttpInspectorService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String url = input.get("inspectUrl");
            Map<String, Object> result = service.inspectUrl(url);
            resp.getWriter().write(mapper.writeValueAsString(result));
        } catch (Exception e) {
            Map<String, Object> error = Map.of("error", "An error occurred: " + e.getMessage());
            resp.getWriter().write(mapper.writeValueAsString(error));
        }
    }
}

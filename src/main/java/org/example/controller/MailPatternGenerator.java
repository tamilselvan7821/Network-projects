package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.MailPatternService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MailPatternGenerator extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    MailPatternService service = new MailPatternService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        Map<String, Object> response = new HashMap<>();
        try{
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String name = input.get("Name");
            String domain =input.get("Domain");
            if(name == null || domain == null || name.isEmpty() || domain.isEmpty()){
                response.put("error", "name and domain are required");
            }
            else{
                response = service.generatePattern(name, domain);
            }
        }
        catch(Exception e){
            response.put("error", "An error occurred "+e.getMessage());
        }
        res.getWriter().write(mapper.writeValueAsString(response));
    }
}

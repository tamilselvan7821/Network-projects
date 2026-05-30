package org.example.service;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class LogReaderService {

    public Map<String, Integer> readLog(Collection<Part> parts) {
        Map<String, Integer> counts = new HashMap<>();
        int info = 0, error = 0, warning = 0;
        try {
            for(Part part : parts) {
                if("files".equals(part.getName()) && part.getSize() > 0) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            line = line.toLowerCase();
                            if (line.contains("error")) error++;
                            if (line.contains("info")) info++;
                            if (line.contains("warning")) warning++;
                        }
                    }
                }
            }
        }catch (Exception e) {
            counts.put("error", 1); // flag error
            return counts;
        }
        counts.put("info", info);
        counts.put("error", error);
        counts.put("warning", warning);
        return counts;
    }
}

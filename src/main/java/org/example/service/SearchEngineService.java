package org.example.service;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

@MultipartConfig
public class SearchEngineService {
    public List<String> getMultipleFiles(String keyword, Collection<Part> parts){
        List<String> matchedFiles = new ArrayList<>();
        try {
            for (Part part : parts) {
                if (part.getName().equals("files") && part.getSize() > 0) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()));
                    String line;
                    int l = 1;
                    while ((line = br.readLine()) != null) {
                        if (line.toLowerCase().contains(keyword)) {
                            matchedFiles.add("file name : "+part.getSubmittedFileName()+" line no : "+l);
                            break;
                        }
                        l++;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(matchedFiles.isEmpty()){
            matchedFiles.add("Keyword doesn't match");
        }
        return matchedFiles;
    }
}

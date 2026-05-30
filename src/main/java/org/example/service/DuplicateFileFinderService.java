package org.example.service;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.io.*;
import java.security.MessageDigest;
import java.util.*;

@MultipartConfig
public class DuplicateFileFinderService {

    public File convertPartToFile(Part part, String uploadDir) throws IOException {
        String fileName = part.getSubmittedFileName();
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        File file = new File(uploadFolder, fileName);
        try (InputStream input = part.getInputStream();
             FileOutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        return file;
    }
    public Map<String, Object> findDuplicates(Collection<Part> parts) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean flag = false;
            HashMap<String, List<String>> map = new HashMap<>();
            for (Part f : parts) {
                if (f.getSize() > 0 && "files".equals(f.getName())) {
                    File file = convertPartToFile(f, "/tmp/uploads/");
                    String hash = getHash(file);
                    map.putIfAbsent(hash, new ArrayList<>());
                    map.get(hash).add(file.getName());
                }
            }
            List<List<String>> duplicates = new ArrayList<>();
            for (String key : map.keySet()) {
                if (map.get(key).size() > 1) {
                    duplicates.add(map.get(key));
                    flag = true;
                }
            }
            result.put("duplicatesFound", flag);
            result.put("duplicates", duplicates);
        } catch (Exception e) {
            result.put("error", "Error occurred: " + e.getMessage());
        }
        return result;
    }

    private String getHash(File file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
            fis.close();
            byte[] hash = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}

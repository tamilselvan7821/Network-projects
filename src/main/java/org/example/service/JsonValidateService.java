package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonValidateService {

    public String validateAndPrettyPrint(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Object js = mapper.readValue(json, Object.class);
        return mapper.enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(js);
    }
}

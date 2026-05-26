package com.daraz.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonReader {

    public static <T> List<T> readJsonData(String filePath, TypeReference<List<T>> typeReference) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), typeReference);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + e.getMessage());
        }
    }
}
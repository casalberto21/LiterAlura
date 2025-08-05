package com.alura.literalura.service;

import com.alura.literalura.model.IJsonConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class DataConverter implements IJsonConvert {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> simpleClass) {
        try {
            return mapper.readValue(json, simpleClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to class: " + simpleClass.getSimpleName(), e);
        }
    }

    @Override
    public <T> List<T> getArrayData(String json, Class<T> arrayClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, arrayClass));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to List<" + arrayClass.getSimpleName() + ">", e);
        }
    }
}
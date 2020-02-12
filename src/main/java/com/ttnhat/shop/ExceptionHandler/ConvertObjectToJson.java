package com.ttnhat.shop.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertObjectToJson {
    private Object object;

    public ConvertObjectToJson(Object object) {
        this.object = object;
    }

    public String convert() throws JsonProcessingException
    {
        if (this.object == null){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this.object);
    }
}

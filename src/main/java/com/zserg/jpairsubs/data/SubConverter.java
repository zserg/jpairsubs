package com.zserg.jpairsubs.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zserg.jpairsubs.model.Sub;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
public class SubConverter implements AttributeConverter<Sub, String> {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Sub sub) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(sub);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public Sub convertToEntityAttribute(String subJson) {

        Sub sub = null;
        try {
            sub = objectMapper.readValue(subJson, Sub.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return sub;
    }

}

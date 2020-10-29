package com.zserg.jpairsubs.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.model.Subtitle;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
public class SubConverter implements AttributeConverter<List<Subtitle>, String> {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Subtitle> sub) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(sub);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public List<Subtitle> convertToEntityAttribute(String subJson) {

        try {
            return objectMapper.readValue(subJson, new TypeReference<List<Subtitle>>() {
            });
        } catch (final IOException e) {
            log.error("JSON reading error", e);
            return new ArrayList<>();
        }
    }

}

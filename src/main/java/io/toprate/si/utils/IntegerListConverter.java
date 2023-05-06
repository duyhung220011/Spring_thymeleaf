package io.toprate.si.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Integer> integerList) {
        try {
            return new ObjectMapper().writeValueAsString(integerList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
    }

    @SneakyThrows
    @Override
    public List<Integer> convertToEntityAttribute(String string) {
        return StringUtils.isBlank(string) ? null : new ObjectMapper().readValue(string, new TypeReference<>() {
        });
    }
}
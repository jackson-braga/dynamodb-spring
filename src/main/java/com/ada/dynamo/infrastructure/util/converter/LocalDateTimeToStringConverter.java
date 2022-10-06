package com.ada.dynamo.infrastructure.util.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;

public class LocalDateTimeToStringConverter implements DynamoDBTypeConverter<String, LocalDateTime> {
    @Override
    public String convert(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    @Override
    public LocalDateTime unconvert(String s) {
        return LocalDateTime.parse(s);
    }
}

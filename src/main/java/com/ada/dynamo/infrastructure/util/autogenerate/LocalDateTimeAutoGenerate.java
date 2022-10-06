package com.ada.dynamo.infrastructure.util.autogenerate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerator;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeAutoGenerate implements DynamoDBAutoGenerator<LocalDateTime> {
    @Override
    public DynamoDBAutoGenerateStrategy getGenerateStrategy() {
        return DynamoDBAutoGenerateStrategy.CREATE;
    }

    @Override
    public LocalDateTime generate(LocalDateTime o) {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}

package com.ada.dynamo.model;

import com.ada.dynamo.util.converter.LocalDateTimeToStringConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "tarefas")
public class Tarefa {

    @DynamoDBHashKey
    private String id;

    private String titulo;
    private String descricao;

    @DynamoDBTypeConvertedEnum
    private Prioridade prioridade;

    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    private LocalDateTime criacao;

    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    private LocalDateTime previsao;

    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    private LocalDateTime conclusao;
}

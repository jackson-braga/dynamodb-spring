package com.ada.dynamo.domain.model;
import com.ada.dynamo.infrastructure.util.autogenerate.LocalDateTimeAutoGenerate;
import com.ada.dynamo.infrastructure.util.converter.LocalDateTimeToStringConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "quadros")
public class CartaoTarefa {
    @DynamoDBHashKey
    private String id;
    @DynamoDBRangeKey
    private String tipo;
    private String titulo;
    private String descricao;
    @DynamoDBTypeConvertedEnum
    private Prioridade prioridade;
    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    @DynamoDBAutoGenerated(generator = LocalDateTimeAutoGenerate.class)
    private LocalDateTime criacao;
    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    private LocalDateTime previsao;
    @DynamoDBTypeConverted(converter = LocalDateTimeToStringConverter.class)
    private LocalDateTime conclusao;
    // TODO: vincular com a tarefa
}

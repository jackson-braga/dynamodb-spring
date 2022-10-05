package com.ada.dynamo.model;


import com.ada.dynamo.config.DynamoDBGenerateAtInsert;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "tarefas")
public class Tarefa implements DynamoDBEntity {

    @DynamoDBHashKey
    private String id;

    @DynamoDBRangeKey
    private String titulo;
    private String descricao;

    @DynamoDBTypeConvertedEnum
    private Prioridade prioridade;

    @DynamoDBGenerateAtInsert(type = LocalDateTime.class)
    private LocalDateTime criacao;

    @DynamoDBGenerateAtInsert(type = LocalDateTime.class)
    private LocalDateTime previsao;

    @DynamoDBGenerateAtInsert(type = LocalDateTime.class)
    private LocalDateTime conclusao;
}

package com.ada.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "quadros")
public class Coluna {
    @DynamoDBHashKey
    private String id;
    @DynamoDBRangeKey
    private String tipo;
    private String nome;
    private String cor; //#FFFFFF
    private Integer ordem;
    private Integer limite;
}

package com.ada.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@DynamoDBTable(tableName = "quadros")
@NoArgsConstructor
public class Quadro {

    @DynamoDBHashKey
    private String id;

    @DynamoDBRangeKey
    private String tipo;
    private String nome;
}

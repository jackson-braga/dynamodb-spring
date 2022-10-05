package com.ada.dynamo.model;

import com.ada.dynamo.enums.Tipo;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Data;

import java.util.UUID;

@Data
@DynamoDBTable(tableName = "quadros")
public class Quadro {
    @DynamoDBHashKey
    private String id;
    @DynamoDBRangeKey
    @DynamoDBTypeConvertedEnum
    private Tipo tipo = Tipo.QUADRO;
    private String nome;
}

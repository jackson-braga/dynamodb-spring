package com.ada.dynamo.model;

import com.ada.dynamo.enums.Tipo;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "quadros")
public class Coluna {
    @DynamoDBHashKey
    private String id;
    @DynamoDBRangeKey
    @DynamoDBTypeConvertedEnum
    private Tipo tipo = Tipo.COLUNA;
    private String nome;
    private String cor; //#FFFFFF
    private Integer ordem;
    private Integer limite;
}

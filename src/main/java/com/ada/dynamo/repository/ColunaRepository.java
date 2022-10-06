package com.ada.dynamo.repository;
import java.util.HashMap;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import com.ada.dynamo.model.Coluna;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ColunaRepository extends AbstractRepository<Coluna,String> {

    public ColunaRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected DynamoDBScanExpression getDynamoDBScanExpression() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS("COLUNA"));
        var scanDB = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return scanDB;
    }

    @Override
    protected Class<Coluna> getClassType() {
        return Coluna.class;
    }

    /*@Override
    protected Coluna doEntity(String id) {
        Coluna coluna = new Coluna();
        coluna.setId(id);
        return coluna;
    }*/
}

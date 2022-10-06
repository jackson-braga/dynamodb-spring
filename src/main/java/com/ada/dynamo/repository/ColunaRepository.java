package com.ada.dynamo.repository;

import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.model.Quadro;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ColunaRepository {

    private final DynamoDBMapper mapper;

    public Coluna save(String quadroId, Coluna coluna) {
        coluna.setId(String.format("%s#%s", quadroId, UUID.randomUUID()));
        mapper.save(coluna);
        return coluna;
    }

    public Iterable<Coluna> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS("COLUNA"));


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Coluna.class, scanExpression);
    }

    public Optional<Coluna> findById(String id) {
        return Optional.ofNullable(mapper.load(Coluna.class, id, "COLUNA"));
    }

    public void delete(Coluna id) {
        mapper.delete(id);
    }
}

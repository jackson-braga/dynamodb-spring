package com.ada.dynamo.repository;

import com.ada.dynamo.model.Quadro;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class QuadroRepository {

    private final DynamoDBMapper mapper;

    public Quadro save(Quadro quadro) {
        quadro.setId(UUID.randomUUID().toString());
        mapper.save(quadro);
        return quadro;
    }

    public Iterable<Quadro> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS("a5ca8f74-121b-4f3a-bb85-961118da5bb3"));
//        eav.put(":tipo", new AttributeValue().withS("QUA"));
//
//        DynamoDBQueryExpression<Quadro> queryExpression = new DynamoDBQueryExpression<Quadro>()
//                .withKeyConditionExpression("id = :id and begins_with(tipo, :tipo)")
//                .withExpressionAttributeValues(eav);
//
//        List<Quadro> quadros = mapper.query(Quadro.class, queryExpression);

//        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
//                .withFilterExpression("begins_with(id, :id)")
//                .withExpressionAttributeValues(eav);

        Condition contition = new Condition()
                .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                .withAttributeValueList(new AttributeValue().withS("a5ca8f74-121b-4f3a-bb85-961118da5bb3"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withScanFilter(Map.of("id", contition));


        List<Quadro> quadros = mapper.scan(Quadro.class, scanExpression);

        return quadros.stream().collect(Collectors.toList());
    }
}

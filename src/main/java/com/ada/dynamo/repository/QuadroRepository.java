package com.ada.dynamo.repository;

import com.ada.dynamo.model.Quadro;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class QuadroRepository {
    private final DynamoDBMapper mapper;

    public Quadro save(Quadro quadro) {
        mapper.save(quadro);
        return quadro;
    }
    public Quadro put(Quadro quadro) {
        mapper.save(quadro);
        return quadro;
    }

    public Iterable<Quadro> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS("QUADRO"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Quadro.class, scanExpression);
    }

    public Optional<Quadro> findById(String id) {
        return Optional.ofNullable(mapper.load(Quadro.class, id, "QUADRO"));
    }

    public List<Quadro> findByPartialId(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Quadro.class, scanExpression);
    }

    public void delete(Quadro id) {
        mapper.delete(id);
    }
}

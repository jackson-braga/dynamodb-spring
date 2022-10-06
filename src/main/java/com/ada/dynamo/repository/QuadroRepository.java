package com.ada.dynamo.repository;

import com.ada.dynamo.model.Quadro;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class QuadroRepository {

    private final DynamoDBMapper mapper;
    private static final String TIPO = "quadro";

    public Quadro save(Quadro quadro) {
        quadro.setId(UUID.randomUUID().toString());
        quadro.setTipo(TIPO);
        mapper.save(quadro);
        return quadro;
    }

    public Quadro findById(String id) {
        var entity = mapper.load(Quadro.class, id, TIPO);
        if (Objects.nonNull(entity))
            return entity;
        throw new RuntimeException("Item not found!");
    }

    public Iterable<Quadro> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue(TIPO));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Quadro.class, scanExpression);
    }

    public Quadro update(Quadro quadro) {

        findById(quadro.getId());
        mapper.save(quadro);
        return quadro;
    }

    public void delete(String id) {
        mapper.delete(findById(id));
    }

}

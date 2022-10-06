package com.ada.dynamo.repository;

import com.ada.dynamo.model.Coluna;
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
public class ColunaRepository {

    private final DynamoDBMapper mapper;

    private static final String TIPO = "coluna";

    public Coluna save(String quadroId, Coluna coluna) {
        coluna.setId(quadroId + "#" + UUID.randomUUID());
        coluna.setTipo(TIPO);
        mapper.save(coluna);
        return coluna;
    }

    public Coluna findById(String id) {
        var entity = mapper.load(Coluna.class, id, TIPO);
        if (Objects.nonNull(entity))
            return entity;
        throw new RuntimeException("Item not found!");
    }

    public Iterable<Coluna> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue(TIPO));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Coluna.class, scanExpression);
    }

    public Coluna update(Coluna coluna) {

        findById(coluna.getId());
        mapper.save(coluna);
        return coluna;
    }

    public void delete(String id) {
        mapper.delete(findById(id));
    }
}

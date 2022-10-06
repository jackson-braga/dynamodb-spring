package com.ada.dynamo.repository;

import com.ada.dynamo.model.Coluna;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ColunaRepository {

    private final DynamoDBMapper mapper;

    public Coluna save(String quadroId, Coluna coluna) {
        coluna.setId(quadroId + "#" + UUID.randomUUID());
        mapper.save(coluna);
        return coluna;
    }

    public Coluna findById(String id) {
        return mapper.load(Coluna.class, id);
    }

    public Iterable<Coluna> findAll() {
        return mapper.scan(Coluna.class, new DynamoDBScanExpression());
    }
}

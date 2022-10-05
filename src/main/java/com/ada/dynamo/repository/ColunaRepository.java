package com.ada.dynamo.repository;

import com.ada.dynamo.model.Coluna;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ColunaRepository {

    private final DynamoDBMapper mapper;

    public Coluna save(Coluna coluna) {
        mapper.save(coluna);
        return coluna;
    }
}

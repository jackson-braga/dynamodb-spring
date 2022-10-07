package com.ada.dynamo.repository;

import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.provider.TipoProvider;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ColunaRepository {

    private final DynamoDBMapper mapper;

    public Coluna save(Coluna coluna) {
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

    public List<Coluna> findByPartialId(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Coluna.class, scanExpression);
    }

    public void delete(Coluna entity) {
        mapper.delete(entity);
    }

    public List<Coluna> findByQuadro(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));
        eav.put(":tipo", new AttributeValue().withS(TipoProvider.COLUNA.stringValue()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id and tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Coluna.class, scanExpression);
    }
}

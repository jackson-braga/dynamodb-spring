package com.ada.dynamo.repository;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class QuadroRepository extends AbstractRepository<Quadro,String> {

    public QuadroRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected DynamoDBScanExpression getDynamoDBScanExpression() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS("QUADRO"));
        var scanDB = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return scanDB;
    }

    @Override
    protected Class<Quadro> getClassType() {
        return Quadro.class;
    }
}

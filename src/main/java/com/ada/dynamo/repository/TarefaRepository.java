package com.ada.dynamo.repository;

import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository

public class TarefaRepository extends AbstractRepository<Tarefa,String> {

    public TarefaRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected DynamoDBScanExpression getDynamoDBScanExpression() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS("TAREFA"));
        var scanDB = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return scanDB;
    }

    @Override
    protected Class<Tarefa> getClassType() {
        return Tarefa.class;
    }
}

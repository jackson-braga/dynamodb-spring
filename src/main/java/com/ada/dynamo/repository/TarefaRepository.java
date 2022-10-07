package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class TarefaRepository {
    private final DynamoDBMapper mapper;

    public Tarefa save(Tarefa tarefa) {
        mapper.save(tarefa);
        return tarefa;
    }

    public Tarefa put(Tarefa tarefa) {
        mapper.save(tarefa);
        return tarefa;
    }

    public Optional<Tarefa> findById(String id) {
        return Optional.ofNullable(mapper.load(Tarefa.class, id));
    }

    public List<CartaoTarefa> findByPartialId(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id")
                .withExpressionAttributeValues(eav);

        return mapper.scan(CartaoTarefa.class, scanExpression);
    }

    public Iterable<Tarefa> findAll() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Tarefa.class, scanExpression);
    }

    public void delete(Tarefa entity) {
        mapper.delete(entity);
    }

}

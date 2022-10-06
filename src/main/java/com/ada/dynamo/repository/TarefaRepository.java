package com.ada.dynamo.repository;

import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TarefaRepository {
    private final DynamoDBMapper mapper;

    public Tarefa save(Tarefa tarefa) {
        tarefa.setId(UUID.randomUUID().toString());
        mapper.save(tarefa);
        return tarefa;
    }

    public Optional<Tarefa> findById(String id) {
        return Optional.ofNullable(mapper.load(Tarefa.class, id));
    }

    public Iterable<Tarefa> findAll() {
        return new ArrayList<>(mapper.scan(Tarefa.class, new DynamoDBScanExpression()));
    }

    public void delete(Tarefa entity) {
        mapper.delete(entity);
    }
}

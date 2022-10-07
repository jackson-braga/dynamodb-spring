package com.ada.dynamo.repository;

import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TarefaRepository {
    private final DynamoDBMapper mapper;

    public Tarefa save(Tarefa tarefa) {
        tarefa.setId(UUID.randomUUID().toString());
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

    public Iterable<Tarefa> findAll() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Tarefa.class, scanExpression);
    }

    public void delete(Tarefa entity) {
        mapper.delete(entity);
    }

}

package com.ada.dynamo.repository;

import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TarefaRepository {

    private final DynamoDBMapper mapper;

    public Tarefa save(Tarefa tarefa) {
        tarefa.setId(UUID.randomUUID());
        mapper.save(tarefa);
        return tarefa;
    }

    public Tarefa update(Tarefa tarefa) {
        findById(tarefa.getId());
        mapper.save(tarefa);
        return tarefa;
    }

    public void delete(UUID id) {
        mapper.delete(findById(id));
    }

    public Tarefa findById(UUID id) {
        var entity = mapper.load(Tarefa.class, id);
        if (Objects.nonNull(entity))
            return entity;
        throw new RuntimeException("Item not found!");
    }


    public Iterable<Tarefa> findAll() {
        return mapper.scan(Tarefa.class, new DynamoDBScanExpression());
    }
}

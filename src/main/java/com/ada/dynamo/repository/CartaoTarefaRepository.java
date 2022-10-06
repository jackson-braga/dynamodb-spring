package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CartaoTarefaRepository {

    private final DynamoDBMapper mapper;

    public CartaoTarefa save(String tarefaId, String colunaId, CartaoTarefa tarefa) {
        tarefa.setId(tarefaId + "#" + colunaId + "#" + UUID.randomUUID());
        mapper.save(tarefa);
        return tarefa;
    }

    public CartaoTarefa findById(String id) {
        return mapper.load(CartaoTarefa.class, id);
    }

    public Iterable<CartaoTarefa> findAll() {
        return mapper.scan(CartaoTarefa.class, new DynamoDBScanExpression());
    }

    public CartaoTarefa update(CartaoTarefa tarefa) {

        findById(tarefa.getId());
        mapper.save(tarefa);
        return tarefa;
    }

    public void delete(String id) {
        mapper.delete(findById(id));
    }
}

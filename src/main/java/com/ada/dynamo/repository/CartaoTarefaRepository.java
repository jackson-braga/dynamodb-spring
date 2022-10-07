package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CartaoTarefaRepository {

    private final DynamoDBMapper mapper;
    private final ColunaRepository colunaRepository;
    private final TarefaRepository tarefaRepository;
    private static final String TIPO = "tarefa";

    public CartaoTarefa save(String quadroId, String colunaId, CartaoTarefa tarefa) {
        tarefa.setId(generateId(quadroId, colunaId));
        tarefa.setTipo(TIPO);
        mapper.save(tarefa);
        tarefaRepository.save(new Tarefa(tarefa));
        return tarefa;
    }

    private String generateId(String quadroId, String colunaId) {
        return quadroId +"#"+ colunaId +"#"+ UUID.randomUUID();
    }

    public CartaoTarefa findById(String id) {
        var entity = mapper.load(CartaoTarefa.class, id, TIPO);
        if (Objects.nonNull(entity))
            return entity;
        throw new RuntimeException("Item not found!");
    }

    public Iterable<CartaoTarefa> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue(TIPO));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(CartaoTarefa.class, scanExpression);
    }

    public CartaoTarefa update(CartaoTarefa tarefa) {
        findById(tarefa.getId());
        mapper.save(tarefa);
        return tarefa;
    }

    public CartaoTarefa changeCollumn(String quadroId, String colunaId, CartaoTarefa tarefa){
        colunaRepository.findById(quadroId+"#"+colunaId);
        delete(tarefa.getId());
        return save(quadroId,colunaId,tarefa);
    }

    public void delete(String id) {
        CartaoTarefa cartaoTarefa = findById(id);
        mapper.delete(cartaoTarefa);
        tarefaRepository.delete(tarefaRepository.findById(cartaoTarefa.convertIdToTarefaId()).getId());
    }

}

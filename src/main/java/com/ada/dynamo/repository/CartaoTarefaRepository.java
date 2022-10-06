package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartaoTarefaRepository {

    private final DynamoDBMapper mapper;

    public CartaoTarefa save(String colunaId, String tarefaId, CartaoTarefa cartaoTarefa) {
        cartaoTarefa.setId(String.format("%s#%s", colunaId, tarefaId));
        mapper.save(cartaoTarefa);
        return cartaoTarefa;
    }

    public void delete(CartaoTarefa entity) {
        mapper.delete(entity);
    }

    public Optional<CartaoTarefa> findById(String id) {
        return Optional.ofNullable(mapper.load(CartaoTarefa.class, id, "CARTAO_TAREFA"));
    }

    public Iterable<CartaoTarefa> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS("CARTAO_TAREFA"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(CartaoTarefa.class, scanExpression);
    }
}

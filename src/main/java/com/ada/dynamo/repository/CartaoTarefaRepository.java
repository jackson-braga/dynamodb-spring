package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CartaoTarefaRepository {

    private final DynamoDBMapper mapper;

    public CartaoTarefa save(String quadroId, String colunaId, CartaoTarefa cartaoTarefa) {
        cartaoTarefa.setId(quadroId+"#" + colunaId + "#"+ UUID.randomUUID().toString());
        mapper.save(cartaoTarefa);
        return cartaoTarefa;
    }

    public void delete(CartaoTarefa entity) {
        mapper.delete(entity);
    }

    public Optional<CartaoTarefa> findById(String id) {
        return Optional.ofNullable(mapper.load(CartaoTarefa.class, id, "CARTAO_TAREFA"));
    }

    public Iterable<CartaoTarefa> findAll(){
        return new ArrayList<>(mapper.scan(CartaoTarefa.class, new DynamoDBScanExpression()));
    }
}

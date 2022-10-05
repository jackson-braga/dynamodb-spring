package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}

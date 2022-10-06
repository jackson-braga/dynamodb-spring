package com.ada.dynamo.repository;

import com.ada.dynamo.dto.CartaoTarefaAddRequest;
import com.ada.dynamo.model.CartaoTarefa;
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

    private static final String TIPO = "tarefa";

    public CartaoTarefa save(CartaoTarefaAddRequest dto) {
        CartaoTarefa tarefa = convertDtoToModel(dto);
        mapper.save(tarefa);
        return tarefa;
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

    public void delete(String id) {
        mapper.delete(findById(id));
    }

    private  CartaoTarefa convertDtoToModel(CartaoTarefaAddRequest dto) {
        CartaoTarefa cartaoTarefa = new CartaoTarefa();
        cartaoTarefa.setId(dto.getQuadroId() + "#" + dto.getColunaId() + "#" + UUID.randomUUID());
        cartaoTarefa.setConclusao(dto.getConclusao());
        cartaoTarefa.setTipo(TIPO);
        cartaoTarefa.setPrevisao(dto.getPrevisao());
        cartaoTarefa.setDescricao(dto.getDescricao());
        cartaoTarefa.setTitulo(dto.getTitulo());

        return cartaoTarefa;
    }
}

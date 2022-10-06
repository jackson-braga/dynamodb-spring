package com.ada.dynamo.domain.repository;

import com.ada.dynamo.domain.model.CartaoTarefa;

import java.util.List;
import java.util.Optional;

public interface CartaoTarefaRepository {
    CartaoTarefa save(CartaoTarefa cartaoTarefa);

    Optional<CartaoTarefa> getByHashKeyAndRangeKey(String hashKey);

    List<CartaoTarefa> findAll();

    CartaoTarefa update(CartaoTarefa cartaoTarefa);

    void delete(String hashKey);

    CartaoTarefa moveCartaoTarefa(CartaoTarefa cartaoTarefa, String colunaDestino);
}

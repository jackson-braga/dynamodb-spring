package com.ada.dynamo.domain.repository;

import com.ada.dynamo.domain.model.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository {

    Tarefa save(Tarefa tarefa);

    Optional<Tarefa> getByHashKeyAndRangeKey(String hashKey, String rangeKey);

    List<Tarefa> findAll();

    Tarefa update(Tarefa tarefa);

    void delete(String hashKey, String rangeKey);
}

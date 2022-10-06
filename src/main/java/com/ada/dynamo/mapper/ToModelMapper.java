package com.ada.dynamo.mapper;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.model.Tarefa;

public interface ToModelMapper<TEntity, TRequest> {
    TEntity toModel(TRequest request);
}

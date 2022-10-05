package com.ada.dynamo.mapper;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.model.Tarefa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    Tarefa toModel(TarefaRequest tarefaRequest);
}

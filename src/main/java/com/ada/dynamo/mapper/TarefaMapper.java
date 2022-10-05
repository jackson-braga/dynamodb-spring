package com.ada.dynamo.mapper;

import com.ada.dynamo.dto.request.TarefaRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    Tarefa toModel(TarefaRequest tarefaRequest);
}

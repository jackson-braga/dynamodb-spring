package com.ada.dynamo.application.mapper;

import com.ada.dynamo.application.request.TarefaRequest;
import com.ada.dynamo.application.response.TarefaResponse;
import com.ada.dynamo.domain.model.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TarefaMapper {

    TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);

    Tarefa tarefaRequestToTarefa(TarefaRequest tarefaRequest);

    TarefaResponse tarefaToTarefaResponse(Tarefa tarefa);

    List<TarefaResponse> tarefaListToTarefaResponseList(List<Tarefa> tarefa);

}

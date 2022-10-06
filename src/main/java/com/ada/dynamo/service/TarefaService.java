package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.dto.response.TarefaResponse;
import com.ada.dynamo.model.Prioridade;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

//    public TarefaResponse add(TarefaRequest tarefaRequest) {
//        Tarefa tarefa = repository.save(tarefaRequest);
//        return tarefa;
//    }

}

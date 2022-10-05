package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.TarefaResponse;
import com.ada.dynamo.dto.response.TarefaRequest;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
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

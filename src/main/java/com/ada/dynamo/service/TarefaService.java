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

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaResponse findById(UUID id){
        var tarefa = repository.findById(id);
        return mapToResponse(tarefa);
    }

//    public TarefaResponse save(TarefaRequest tarefaRequest) {
//        Tarefa tarefa = repository.save(tarefaRequest);
//        return tarefa;
//    }

    private TarefaResponse mapToResponse(Tarefa tarefa){
        var tarefaResponse = new TarefaResponse();

        tarefaResponse.setId(tarefa.getId());
        tarefaResponse.setTitulo(tarefa.getTitulo());
        tarefaResponse.setDescricao(tarefa.getDescricao());
        tarefaResponse.setPrioridade(tarefa.getPrioridade());
        tarefaResponse.setCriacao(tarefa.getCriacao());
        tarefaResponse.setPrevisao(tarefa.getPrevisao());
        tarefaResponse.setConclusao(tarefa.getConclusao());

        return tarefaResponse;
    }

    private Tarefa mapToModel(TarefaRequest request){
        var tarefa = new Tarefa();
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setPrioridade(request.getPrioridade());
        tarefa.setCriacao(request.getCriacao());
        tarefa.setPrevisao(request.getPrevisao());
        tarefa.setConclusao(request.getConclusao());
        return tarefa;
    }

}

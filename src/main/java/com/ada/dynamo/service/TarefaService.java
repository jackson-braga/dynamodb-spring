package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.dto.response.TarefaResponse;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService implements ServiceContract<TarefaRequest, TarefaResponse> {

    private final TarefaRepository repository;

    @Override
    public TarefaResponse findById(String id) {
        Tarefa tarefa = findModelById(id);
        return mapToResponse(tarefa);
    }

    public Tarefa findModelById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Tarefa com id %s não encontrada", id)));
    }

    @Override
    public TarefaResponse create(TarefaRequest tarefaRequest) {
        Tarefa tarefa = repository.save(mapToModel(tarefaRequest));
        return mapToResponse(tarefa);
    }

    @Override
    public List<TarefaResponse> findAll() {
        var tarefas = repository.findAll();
        var iterator = tarefas.iterator();

        var tarefaResponses = new ArrayList<TarefaResponse>();
        while (iterator.hasNext()) {
            tarefaResponses.add(mapToResponse(iterator.next()));
        }
        return tarefaResponses;
    }

    @Override
    public void delete(String id) {
        var tarefa = findModelById(id);
        repository.delete(tarefa);
    }

    private TarefaResponse mapToResponse(Tarefa tarefa) {
        var tarefaResponse = new TarefaResponse();
        BeanUtils.copyProperties(tarefa, tarefaResponse);
        return tarefaResponse;
    }

    private Tarefa mapToModel(TarefaRequest tarefaRequest) {
        var tarefa = new Tarefa();
        BeanUtils.copyProperties(tarefaRequest, tarefa);
        return tarefa;
    }
}

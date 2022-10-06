package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.mapper.TarefaMapper;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository repository;
    private final TarefaMapper mapper;

    public Tarefa save(TarefaRequest tarefaRequest){
        Tarefa tarefa = mapper.toModel(tarefaRequest);
        tarefa.setId(UUID.randomUUID().toString());
        return repository.save(tarefa);
    }

    public List<Tarefa> getAll() {
        return repository.getAll();
    }

    public Tarefa findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

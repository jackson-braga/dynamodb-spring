package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.dto.response.TarefaResponse;
import com.ada.dynamo.exception.ItemComAssociassaoException;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Tarefa tarefaModel = mapToModel(tarefaRequest);
        tarefaModel.setId(UUID.randomUUID().toString());
        tarefaModel.setCriacao(LocalDateTime.now());
        Tarefa tarefa = repository.save(tarefaModel);
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
        List<CartaoTarefa> partial = repository.findByPartialId(id);
        if (!partial.isEmpty()) {
            throw new ItemComAssociassaoException(String.format("O id %s está associado a outros itens e não pode ser deletado", id));
        }

        Tarefa tarefa = findModelById(id);
        repository.delete(tarefa);
    }

    @Override
    public TarefaResponse update(TarefaRequest tarefaRequest, String id) {
        Tarefa tarefa = findModelById(id);
        Tarefa tarefaModel = mapToModel(tarefaRequest);
        tarefaModel.setCriacao(tarefa.getCriacao());
        tarefaModel.setId(id);

        return mapToResponse(repository.save(tarefaModel));
    }

    private TarefaResponse mapToResponse(Tarefa tarefa) {
        TarefaResponse tarefaResponse = new TarefaResponse();
        BeanUtils.copyProperties(tarefa, tarefaResponse);
        return tarefaResponse;
    }

    private Tarefa mapToModel(TarefaRequest tarefaRequest) {
        Tarefa tarefa = new Tarefa();
        BeanUtils.copyProperties(tarefaRequest, tarefa);
        return tarefa;
    }
}

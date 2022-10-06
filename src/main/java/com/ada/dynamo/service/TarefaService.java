package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.dto.response.TarefaResponse;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarefaService implements ServiceContract<TarefaRequest, TarefaResponse> {

    private final TarefaRepository repository;

    @Override
    public TarefaResponse findById(String id) {
        var tarefa = repository.findById(id.toString()).orElseThrow(
            () -> new ItemNaoEncontradoException(String.format("Sem tarefa encontrada para o id %s", id))
        );

        return mapToResponse(tarefa);
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
        var tarefa = repository.findById(id).orElseThrow(
            () -> new ItemNaoEncontradoException(String.format("Tarefa com id %s não econtrado para deleção", id))
        );
        repository.delete(tarefa);
    }

    private TarefaResponse mapToResponse(Tarefa tarefa) {
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

    private Tarefa mapToModel(TarefaRequest request) {
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

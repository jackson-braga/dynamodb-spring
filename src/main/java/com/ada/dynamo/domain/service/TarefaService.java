package com.ada.dynamo.domain.service;

import com.ada.dynamo.application.mapper.TarefaMapper;
import com.ada.dynamo.application.request.TarefaRequest;
import com.ada.dynamo.domain.exception.ItemNaoExistenteException;
import com.ada.dynamo.domain.model.Tarefa;
import com.ada.dynamo.domain.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    private final CartaoTarefaService cartaoTarefaService;

    public Tarefa save(TarefaRequest TarefaRequest) {
        Tarefa tarefa = TarefaMapper.INSTANCE.tarefaRequestToTarefa(TarefaRequest);
        tarefa.setId(generateHashKey());
        tarefa.setCriacao(LocalDateTime.now());

        return tarefaRepository.save(tarefa);
    }

    public Tarefa getByHashKeyAndRangeKey(String hashKey, String rangeKey) {
        return tarefaRepository.getByHashKeyAndRangeKey(hashKey, rangeKey)
                .orElseThrow(() -> new ItemNaoExistenteException(Tarefa.class));
    }

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Tarefa update(String hashKey, TarefaRequest tarefaRequest) {
        Tarefa tarefa = TarefaMapper.INSTANCE.tarefaRequestToTarefa(tarefaRequest);
        tarefa.setId(hashKey);

        return tarefaRepository.update(tarefa);
    }

    public void concluirTarefa(String haskKey, String rangeKey) {
        Tarefa tarefa = tarefaRepository.getByHashKeyAndRangeKey(haskKey, rangeKey)
                .orElseThrow(() -> new ItemNaoExistenteException(Tarefa.class));
        LocalDateTime now = LocalDateTime.now();
        tarefa.setConclusao(now);
        cartaoTarefaService.concluirCartaoTarefa(tarefa.getIdCartaoTarefa(), now);
    }

    public void concluirTarefa(String haskKey, String rangeKey, LocalDateTime localDateTime) {
        Tarefa tarefa = tarefaRepository.getByHashKeyAndRangeKey(haskKey, rangeKey)
                .orElseThrow(() -> new ItemNaoExistenteException(Tarefa.class));
        tarefa.setConclusao(localDateTime);
    }

    public void delete(String hashKey, String rangeKey) {
        tarefaRepository.delete(hashKey, rangeKey);
        Tarefa tarefa = tarefaRepository.getByHashKeyAndRangeKey(hashKey, rangeKey)
                        .orElseThrow(() -> new ItemNaoExistenteException(Tarefa.class));
        cartaoTarefaService.delete(tarefa.getIdCartaoTarefa());
    }

    private String generateHashKey() {
        return UUID.randomUUID().toString();
    }
}
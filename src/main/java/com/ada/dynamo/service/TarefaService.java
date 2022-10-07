package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.mapper.CartaoTarefaMapper;
import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.CartaoTarefaRepository;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TarefaService {
    private final TarefaRepository Tarefarepository;
    private final CartaoTarefaRepository cartaoTarefaRepository;
    private final CartaoTarefaMapper mapper;

    public Tarefa save(CartaoTarefaRequest cartaoTarefaRequest) {
        Tarefa cartaoTarefa = mapper.toTarefa(cartaoTarefaRequest);
        cartaoTarefa.setId(UUID.randomUUID().toString());
        return Tarefarepository.save(cartaoTarefa);
    }

    public Tarefa findById(String id) {
        return Tarefarepository.findById(id);
    }

    public List<Tarefa> getAll() {
        return Tarefarepository.getAll();
    }

    public void delete(String id) {
        List<CartaoTarefa> cartaoTarefa = cartaoTarefaRepository.findIdContains(id);
        if (cartaoTarefa.size() != 0) {
            cartaoTarefaRepository.delete(cartaoTarefa.get(0).getId());
        }
        Tarefarepository.delete(id);
    }

    public Tarefa put(String id, CartaoTarefaRequest cartaoTarefaRequest) {
        Tarefa tarefaLocalizada = findById(id);

        if (tarefaLocalizada == null) {
            throw new RuntimeException("ID não existente!");
        }

        Tarefa tarefa = mapper.toTarefa(cartaoTarefaRequest);
        tarefa.setId(id);

        return Tarefarepository.save(tarefa);
    }
}

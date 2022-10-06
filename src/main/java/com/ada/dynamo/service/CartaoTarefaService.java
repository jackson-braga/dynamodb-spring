package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.mapper.CartaoTarefaMapper;
import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.repository.CartaoTarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartaoTarefaService {

    private final CartaoTarefaRepository repository;
    private final CartaoTarefaMapper mapper;


    public CartaoTarefa save(String quadroColunaId, CartaoTarefaRequest cartaoTarefaRequest){
        CartaoTarefa cartaoTarefa = mapper.toModel(cartaoTarefaRequest);
        cartaoTarefa.setId(quadroColunaId + "_" + UUID.randomUUID());
        return repository.save(cartaoTarefa);
    }

    public CartaoTarefa update(String cartaoTarefaId, CartaoTarefaRequest cartaoTarefaRequest){
        CartaoTarefa cartaoTarefa = mapper.toModel(cartaoTarefaRequest);
        cartaoTarefa.setId(cartaoTarefaId);
        return repository.save(cartaoTarefa);
    }

    public CartaoTarefa changeColumn(String cartaoTarefaId, String colunaId) {
        CartaoTarefa cartaoTarefa = findById(cartaoTarefaId);
        delete(cartaoTarefaId);
        String idCartao = cartaoTarefaId.split("_")[2];
        cartaoTarefa.setId(colunaId.concat("_" + idCartao));
        return repository.save(cartaoTarefa);
    }

    public List<CartaoTarefa> getAll() {
        return repository.getAll();
    }

    public CartaoTarefa findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }
}

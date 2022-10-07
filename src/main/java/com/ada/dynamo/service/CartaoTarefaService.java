package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.dto.response.CartaoTarefaResponse;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.provider.TipoProvider;
import com.ada.dynamo.repository.CartaoTarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoTarefaService implements  ServiceContract<CartaoTarefaRequest, CartaoTarefaResponse> {

    private final CartaoTarefaRepository repository;
    private final ColunaService colunaService;
    private final TarefaService tarefaService;

    @Override
    public CartaoTarefaResponse findById(String id) {
        CartaoTarefa cartaoTarefa = findModelById(id);
        return mapToResponse(cartaoTarefa);
    }

    public CartaoTarefa findModelById(String id) {
        return repository.findById(id)
                .orElseThrow(
                    () -> new ItemNaoEncontradoException(String.format("Cartao de tarefa com id %s não encontrado", id)
                ));
    }

    @Override
    public List<CartaoTarefaResponse> findAll() {
        var cartoesTarefa = repository.findAll();
        var iterator = cartoesTarefa.iterator();

        var cartoesTarefaResponses = new ArrayList<CartaoTarefaResponse>();
        while (iterator.hasNext()) {
            cartoesTarefaResponses.add(mapToResponse(iterator.next()));
        }
        return cartoesTarefaResponses;
    }

    @Override
    public CartaoTarefaResponse create(CartaoTarefaRequest cartaoTarefaRequest) {
        colunaService.findModelById(cartaoTarefaRequest.getColunaId());
        Tarefa tarefa = tarefaService.findModelById(cartaoTarefaRequest.getTarefaId());

        CartaoTarefa cartaoTarefaModel = mapToModel(tarefa);
        CartaoTarefa cartaoTarefa = repository.save(
                cartaoTarefaRequest.getColunaId(), cartaoTarefaRequest.getTarefaId(), cartaoTarefaModel
        );
        return mapToResponse(cartaoTarefa);
    }

    @Override
    public void delete(String id) {
        CartaoTarefa cartaoTarefa = findModelById(id);
        repository.delete(cartaoTarefa);
    }

    @Override
    public CartaoTarefaResponse update(CartaoTarefaRequest cartaoTarefaRequest, String id) {
        return null;
    }

    private CartaoTarefaResponse mapToResponse(CartaoTarefa model) {
        var cartaoTarefaResponse = new CartaoTarefaResponse();
        BeanUtils.copyProperties(model, cartaoTarefaResponse);
        cartaoTarefaResponse.setTipo(TipoProvider.CARTAO_TAREFA.stringValue());
        return cartaoTarefaResponse;
    }

    private CartaoTarefa mapToModel(Tarefa tarefa) {
        var cartaoTarefa = new CartaoTarefa();
        BeanUtils.copyProperties(tarefa, cartaoTarefa);
        cartaoTarefa.setTipo(TipoProvider.CARTAO_TAREFA.stringValue());
        return cartaoTarefa;
    }
}

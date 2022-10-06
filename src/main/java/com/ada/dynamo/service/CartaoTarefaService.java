package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.dto.response.CartaoTarefaResponse;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.provider.TipoProvider;
import com.ada.dynamo.repository.CartaoTarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartaoTarefaService implements  ServiceContract<CartaoTarefaRequest, CartaoTarefaResponse> {

    private final CartaoTarefaRepository repository;

    @Override
    public CartaoTarefaResponse findById(String id) {

        var cartaoTarefa = repository.findById(id)
                .orElseThrow(
                    () -> new ItemNaoEncontradoException(String.format("Cartao de tarefa com id %s não encontrado", id)
                ));
        return mapToResponse(cartaoTarefa);
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
    public CartaoTarefaResponse create(CartaoTarefaRequest request) {
        var cartaoTarefa = mapToModel(request);

        var cartaoTarefaSalvo = repository.save(request.getQuadroId().toString(), request.getColunaId().toString(), cartaoTarefa);

        return mapToResponse(cartaoTarefaSalvo);
    }

    @Override
    public void delete(String id) {
        var cartaoTarefa = repository.findById(id).orElseThrow(
                () -> new ItemNaoEncontradoException(String.format("Coluna com id %s não encontrada para deleção", id))
        );
        repository.delete(cartaoTarefa);
    }

    private CartaoTarefaResponse mapToResponse(CartaoTarefa model) {
        var cartaoTarefaResponse = new CartaoTarefaResponse();

        cartaoTarefaResponse.setTipo(TipoProvider.CARTAO_TAREFA.stringValue());
        cartaoTarefaResponse.setTitulo(model.getTitulo());
        cartaoTarefaResponse.setDescricao(model.getDescricao());
        cartaoTarefaResponse.setPrioridade(model.getPrioridade());
        cartaoTarefaResponse.setCriacao(LocalDateTime.now());
        cartaoTarefaResponse.setPrevisao(model.getPrevisao());
        cartaoTarefaResponse.setConclusao(null);

        return cartaoTarefaResponse;
    }

    private CartaoTarefa mapToModel(CartaoTarefaRequest request) {
        var cartaoTarefa = new CartaoTarefa();

        cartaoTarefa.setTipo(TipoProvider.CARTAO_TAREFA.stringValue());
        cartaoTarefa.setTitulo(request.getTitulo());
        cartaoTarefa.setDescricao(request.getDescricao());
        cartaoTarefa.setPrioridade(request.getPrioridade());
        cartaoTarefa.setCriacao(LocalDateTime.now());
        cartaoTarefa.setPrevisao(request.getPrevisao());
        cartaoTarefa.setConclusao(null);

        return cartaoTarefa;
    }
}

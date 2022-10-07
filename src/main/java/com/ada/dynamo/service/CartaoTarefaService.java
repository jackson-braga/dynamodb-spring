package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.dto.response.CartaoTarefaResponse;
import com.ada.dynamo.dto.response.TarefaResponse;
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
import java.util.Optional;

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
        return repository.findById(id, TipoProvider.CARTAO_TAREFA.stringValue())
                .orElseThrow(
                    () -> new ItemNaoEncontradoException(String.format("Cartao de tarefa com id %s não encontrado", id)
                ));
    }

    public List<CartaoTarefaResponse> findByQuadro(String id){
        var cartoesTarefa = repository.findByQuadro(id);
        var iterator = cartoesTarefa.iterator();

        var cartoesTarefaResponses = new ArrayList<CartaoTarefaResponse>();
        while (iterator.hasNext()) {
            cartoesTarefaResponses.add(mapToResponse(iterator.next()));
        }
        return cartoesTarefaResponses;
    }

    @Override
    public List<CartaoTarefaResponse> findAll() {
        var cartoesTarefa = repository.findBySortKey(
                "tipo", TipoProvider.CARTAO_TAREFA.stringValue()
        );
        var iterator = cartoesTarefa.iterator();

        var cartoesTarefaResponses = new ArrayList<CartaoTarefaResponse>();
        while (iterator.hasNext()) {
            cartoesTarefaResponses.add(mapToResponse(iterator.next()));
        }
        return cartoesTarefaResponses;
    }

    public Optional<CartaoTarefa> findByTarefa(String id) {
        return repository.findByTarefa(id);
    }

    @Override
    public CartaoTarefaResponse create(CartaoTarefaRequest cartaoTarefaRequest) {
        colunaService.findModelById(cartaoTarefaRequest.getColunaId());
        Tarefa tarefa = tarefaService.findModelById(cartaoTarefaRequest.getTarefaId());

        CartaoTarefa cartaoTarefaModel = mapToModel(tarefa);
        cartaoTarefaModel.setId(
                String.format("%s#%s", cartaoTarefaRequest.getColunaId(), cartaoTarefaRequest.getTarefaId())
        );
        CartaoTarefa cartaoTarefa = repository.save(cartaoTarefaModel);
        return mapToResponse(cartaoTarefa);
    }

    @Override
    public void delete(String id) {
        CartaoTarefa cartaoTarefa = findModelById(id);
        repository.delete(cartaoTarefa);
    }

    @Override
    public CartaoTarefaResponse update(CartaoTarefaRequest cartaoTarefaRequest, String id) {
        var novoCartaoTarefa = create(cartaoTarefaRequest);
        delete(id);
        return novoCartaoTarefa;
    }
    public void updateTarefa(TarefaResponse tarefaResponse) {
        var cartaoTarefa = findByTarefa(tarefaResponse.getId());

        if(cartaoTarefa.isPresent()) {
            var cartaoTarefaId = cartaoTarefa.get().getId();

            var ids = cartaoTarefaId.split("#");
            var colunaId = ids[0].concat("#" + ids[1]);

            var cartaoTarefaRequest = new CartaoTarefaRequest();
            cartaoTarefaRequest.setTarefaId(tarefaResponse.getId());
            cartaoTarefaRequest.setColunaId(colunaId);
            create(cartaoTarefaRequest);
        }
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

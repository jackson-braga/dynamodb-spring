package com.ada.dynamo.domain.service;

import com.ada.dynamo.application.mapper.CartaoTarefaMapper;
import com.ada.dynamo.application.request.CreateCartaoTarefaRequest;
import com.ada.dynamo.application.request.UpdateCartaoTarefaRequest;
import com.ada.dynamo.domain.exception.ItemNaoExistenteException;
import com.ada.dynamo.domain.model.CartaoTarefa;
import com.ada.dynamo.domain.repository.CartaoTarefaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartaoTarefaService {

    private static final String TIPO = "CARTAO_TAREFA";

    private CartaoTarefaRepository cartaoTarefaRepository;

    public CartaoTarefa save(CreateCartaoTarefaRequest createCartaoTarefaRequest) {
        CartaoTarefa cartaoTarefa = CartaoTarefaMapper.INSTANCE.createCartaoTarefaRequestToCartaoTarefa(createCartaoTarefaRequest);
        System.out.println(cartaoTarefa.getId());
        cartaoTarefa.setId(generateHashKey(createCartaoTarefaRequest.getColunaId()));
         //

        return cartaoTarefaRepository.save(cartaoTarefa);
    }

    public CartaoTarefa getByHashKeyAndRangeKey(String hashKey) {
        return cartaoTarefaRepository.getByHashKeyAndRangeKey(hashKey)
                .orElseThrow(ItemNaoExistenteException::new);
    }

    public List<CartaoTarefa> findAll() {
        return cartaoTarefaRepository.findAll();
    }

    public CartaoTarefa moveCartaoTarefa(String hashKey, String colunaDestino) {
        Optional<CartaoTarefa> cartaoTarefa = cartaoTarefaRepository.getByHashKeyAndRangeKey(hashKey);
        return cartaoTarefaRepository.moveCartaoTarefa(cartaoTarefa.get(), colunaDestino);
    }

    public CartaoTarefa update(String hashKey, UpdateCartaoTarefaRequest updateCartaoTarefaRequest) {
        CartaoTarefa cartaoTarefa = CartaoTarefaMapper.INSTANCE.updateCartaoTarefaRequestToCartaoTarefa(updateCartaoTarefaRequest);
        cartaoTarefa.setId(hashKey);
        cartaoTarefa.setTipo(TIPO);

        return cartaoTarefaRepository.update(cartaoTarefa);
    }

    public void delete(String hashKey) {
        cartaoTarefaRepository.delete(hashKey);
    }

    private String generateHashKey(String hashKeyColuna) {
        return hashKeyColuna.concat("#").concat(UUID.randomUUID().toString());
    }
}

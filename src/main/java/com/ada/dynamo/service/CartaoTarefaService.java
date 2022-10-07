package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.mapper.CartaoTarefaMapper;
import com.ada.dynamo.model.CartaoTarefa;

import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.CartaoTarefaRepository;
import com.ada.dynamo.repository.ColunaRepository;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartaoTarefaService {

    private final CartaoTarefaRepository cartaoTarefaRepository;
    private final CartaoTarefaMapper cartaoTarefaMapper;
    private final ColunaRepository colunaRepository;
    private final TarefaRepository tarefaRepository;


    public CartaoTarefa save(String cartaoTarefaId, String quadroColunaId){
        if (isColunaFull(quadroColunaId)) {
            throw new RuntimeException("NÃO HÁ ESPAÇO NA COLUNA!");
        }
        Tarefa tarefa = tarefaRepository.findById(cartaoTarefaId);
        CartaoTarefa cartaoTarefa = cartaoTarefaMapper.toCartaoTarefa(tarefa);
        cartaoTarefa.setId(quadroColunaId + "_" + cartaoTarefaId);
        return cartaoTarefaRepository.save(cartaoTarefa);
    }


    public CartaoTarefa update(String cartaoTarefaId, CartaoTarefaRequest cartaoTarefaRequest){
        CartaoTarefa cartaoTarefaLocalizado = findById(cartaoTarefaId);

        if(cartaoTarefaLocalizado == null) {
            throw new RuntimeException("ID não existente!");
        }

        CartaoTarefa cartaoTarefa = cartaoTarefaMapper.toModel(cartaoTarefaRequest);
        String idTarefa = cartaoTarefaId.split("_")[2];
        Tarefa tarefa = cartaoTarefaMapper.toTarefa(cartaoTarefaRequest);
        tarefa.setId(idTarefa);
        cartaoTarefa.setId(cartaoTarefaId);
        tarefaRepository.save(tarefa);

        return cartaoTarefaRepository.save(cartaoTarefa);
    }

    public CartaoTarefa changeColuna(String cartaoTarefaId, String quadroColunaId) {
        if (isColunaFull(quadroColunaId)) {
            throw new RuntimeException("NÃO HÁ ESPAÇO NA COLUNA!");
        }
        CartaoTarefa cartaoTarefa = findById(cartaoTarefaId);
        delete(cartaoTarefaId);
        String idTarefa = cartaoTarefaId.split("_")[2];
        cartaoTarefa.setId(quadroColunaId.concat("_" + idTarefa));
        return cartaoTarefaRepository.save(cartaoTarefa);
    }

    public List<CartaoTarefa> getAll() {
        return cartaoTarefaRepository.getAll();
    }

    public CartaoTarefa findById(String id) {
        return cartaoTarefaRepository.findById(id);
    }

    public void delete(String id) {
        cartaoTarefaRepository.delete(id);
    }

    private Long countCartaoTarefafromColuna(String idColuna) {
        List<CartaoTarefa> listCartaoTarefa = getAll();
        return listCartaoTarefa.stream()
                .filter(cartaoTarefa -> cartaoTarefa.getId().contains(idColuna))
                .count();
    }

    private boolean isColunaFull(String idColuna) {
        Integer limiteTarefaColuna = colunaRepository.findById(idColuna).getLimite();
        return limiteTarefaColuna <= countCartaoTarefafromColuna(idColuna);
    }

}

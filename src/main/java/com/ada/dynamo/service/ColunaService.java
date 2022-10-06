package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.dto.response.ColunaResponse;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.repository.ColunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColunaService implements ServiceContract<ColunaRequest, ColunaResponse> {

    private final ColunaRepository repository;

    @Override
    public ColunaResponse findById(String id) {
        var coluna = repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Coluna com id %s não encontrada", id)));

        return mapToResponse(coluna);
    }

    @Override
    public List<ColunaResponse> findAll() {
        var colunas = repository.findAll();
        var iterator = colunas.iterator();

        var colunaResponses = new ArrayList<ColunaResponse>();
        while (iterator.hasNext()) {
            colunaResponses.add(mapToResponse(iterator.next()));
        }
        return colunaResponses;
    }

    @Override
    public ColunaResponse create(ColunaRequest request) {
        Coluna colunaModel = mapToModel(request);
        var coluna = repository.save(request.getQuadroId().toString(), colunaModel);

        return mapToResponse(coluna);
    }

    @Override
    public void delete(String id) {
        var coluna = repository.findById(id).orElseThrow(
                () -> new ItemNaoEncontradoException(String.format("Coluna com id %s não encontrada para deleção", id))
        );
        repository.delete(coluna);
    }

    private ColunaResponse mapToResponse(Coluna coluna) {
        var colunaResponse = new ColunaResponse();
        BeanUtils.copyProperties(coluna, colunaResponse);
        return colunaResponse;
    }

    private Coluna mapToModel(ColunaRequest request) {
        var coluna = new Coluna();
        BeanUtils.copyProperties(request, coluna);
        return coluna;
    }
}

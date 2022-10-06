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

@Service
@RequiredArgsConstructor
public class ColunaService implements ServiceContract<ColunaRequest, ColunaResponse> {

    private final ColunaRepository repository;
    private final QuadroService quadroService;

    @Override
    public ColunaResponse findById(String id) {
        Coluna coluna = findModelById(id);
        return mapToResponse(coluna);
    }

    public Coluna findModelById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Coluna com id %s não encontrada", id)));
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
        quadroService.findModelById(request.getQuadroId());

        Coluna colunaModel = mapToModel(request);
        Coluna coluna = repository.save(request.getQuadroId(), colunaModel);

        return mapToResponse(coluna);
    }

    @Override
    public void delete(String id) {
        Coluna coluna = findModelById(id);
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

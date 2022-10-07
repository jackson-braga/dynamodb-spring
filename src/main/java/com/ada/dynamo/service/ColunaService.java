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
        colunaModel.setId(String.format("%s#%s", request.getQuadroId(), UUID.randomUUID()));
        Coluna coluna = repository.save(colunaModel);

        return mapToResponse(coluna);
    }

    @Override
    public void delete(String id) {
        Coluna coluna = findModelById(id);
        repository.delete(coluna);
    }

    @Override
    public ColunaResponse update(ColunaRequest colunaRequest, String id) {
        findModelById(id);
        Coluna colunaModel = mapToModel(colunaRequest);
        colunaModel.setId(id);
        return mapToResponse(repository.save(colunaModel));
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

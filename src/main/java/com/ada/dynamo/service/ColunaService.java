package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.dto.response.ColunaResponse;
import com.ada.dynamo.exception.ItemComAssociassaoException;
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
        return repository.findById(id, "COLUNA")
                .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Coluna com id %s não encontrada", id)));
    }

    @Override
    public List<ColunaResponse> findAll() {
        var colunas = repository.findBySortKey("tipo", "COLUNA");
        var iterator = colunas.iterator();

        var colunaResponses = new ArrayList<ColunaResponse>();
        while (iterator.hasNext()) {
            colunaResponses.add(mapToResponse(iterator.next()));
        }
        return colunaResponses;
    }

    public List<ColunaResponse> findByQuadro(String quadroId) {
        var colunas = repository.findByQuadro(quadroId);
        var iterator = colunas.iterator();

        var colunaResponses = new ArrayList<ColunaResponse>();
        while (iterator.hasNext()) {
            colunaResponses.add(mapToResponse(iterator.next()));
        }
        return colunaResponses;
    }

    @Override
    public ColunaResponse create(ColunaRequest colunaRequest) {
        quadroService.findModelById(colunaRequest.getQuadroId());

        Coluna colunaModel = mapToModel(colunaRequest);
        colunaModel.setId(String.format("%s#%s", colunaRequest.getQuadroId(), UUID.randomUUID()));
        Coluna coluna = repository.save(colunaModel);

        return mapToResponse(coluna);
    }

    @Override
    public void delete(String id) {
        List<Coluna> partial = repository.findByPartialId(id);
        if (!partial.isEmpty()) {
            throw new ItemComAssociassaoException(String.format("O id %s está associado a outros itens e não pode ser deletado", id));
        }

        Coluna coluna = findModelById(id);
        repository.delete(coluna);
    }

    @Override
    public ColunaResponse update(ColunaRequest colunaRequest, String id) {
        findModelById(id);
        quadroService.findModelById(colunaRequest.getQuadroId());
        Coluna colunaModel = mapToModel(colunaRequest);
        colunaModel.setId(id);
        return mapToResponse(repository.save(colunaModel));
    }

    private ColunaResponse mapToResponse(Coluna coluna) {
        ColunaResponse colunaResponse = new ColunaResponse();
        BeanUtils.copyProperties(coluna, colunaResponse);
        return colunaResponse;
    }

    private Coluna mapToModel(ColunaRequest request) {
        Coluna coluna = new Coluna();
        BeanUtils.copyProperties(request, coluna);
        coluna.setTipo("COLUNA");
        return coluna;
    }
}

package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.dto.response.QuadroResponse;
import com.ada.dynamo.exception.ItemComAssociassaoException;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuadroService implements ServiceContract<QuadroRequest, QuadroResponse> {

    private final QuadroRepository repository;

    public QuadroResponse findById(String id) {
        Quadro quadro = findModelById(id);
        return mapToResponse(quadro);
    }

    public Quadro findModelById(String id) {
        return repository.findById(id, "QUADRO")
                .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Quadro com id %s não encontrado", id)));
    }

    public List<QuadroResponse> findAll() {
        var quadros = repository.findBySortKey("tipo", "QUADRO");
        var iterator = quadros.iterator();

        var quadroResponses = new ArrayList<QuadroResponse>();
        while (iterator.hasNext()) {
            quadroResponses.add(mapToResponse(iterator.next()));
        }

        return quadroResponses;
     }

    public QuadroResponse create(QuadroRequest quadroRequest) {
        Quadro quadroModel = mapToModel(quadroRequest);
        quadroModel.setId(UUID.randomUUID().toString());
        Quadro quadro = repository.save(quadroModel);
        return mapToResponse(quadro);
    }

    public void delete(String id) {
        List<Quadro> partial = repository.findByPartialId(id);
        if (!partial.isEmpty()) {
            throw new ItemComAssociassaoException(String.format("O id %s está associado a outros itens e não pode ser deletado", id));
        }

        Quadro quadro = findModelById(id);
        repository.delete(quadro);
    }

    @Override
    public QuadroResponse update(QuadroRequest quadroRequest, String id) {
        findModelById(id);
        Quadro quadroModel = mapToModel(quadroRequest);
        quadroModel.setId(id);
        return mapToResponse(repository.save(quadroModel));
    }

    private QuadroResponse mapToResponse(Quadro quadro) {
        QuadroResponse quadroResponse = new QuadroResponse();
        BeanUtils.copyProperties(quadro, quadroResponse);
        return quadroResponse;
    }
    
    private Quadro mapToModel(QuadroRequest request) {
        Quadro quadro = new Quadro();
        BeanUtils.copyProperties(request, quadro);
        quadro.setTipo("QUADRO");
        return quadro;
    }
}

package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.dto.response.QuadroResponse;
import com.ada.dynamo.exception.ItemNaoEncontradoException;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuadroService implements ServiceContract<QuadroRequest, QuadroResponse> {

    private final QuadroRepository repository;

    public QuadroResponse findById(String id) {
        var quadro = repository.findById(id.toString())
            .orElseThrow(() -> new ItemNaoEncontradoException(String.format("Quadro com id %s não encontrado", id)));

        return mapToResponse(quadro);
    }

    public List<QuadroResponse> findAll() {
        var quadros = repository.findAll();
        var iterator = quadros.iterator();

        var quadroResponses = new ArrayList<QuadroResponse>();
        while (iterator.hasNext()) {
            quadroResponses.add(mapToResponse(iterator.next()));
        }

        return quadroResponses;
     }

    public QuadroResponse create(QuadroRequest request) {
        var quadro = repository.save(mapToModel(request));

        return mapToResponse(quadro);
    }

    public void delete(String id) {
        var quadro = repository.findById(id).orElseThrow(
            () -> new ItemNaoEncontradoException(String.format("Quadro com id %s não encontrado para deleção", id))
        );
        repository.delete(quadro);
    }

    private QuadroResponse mapToResponse(Quadro quadro) {
        var quadroResponse = new QuadroResponse();

        quadroResponse.setId(quadro.getId());
        quadroResponse.setNome(quadro.getNome());
        quadroResponse.setTipo(quadro.getTipo());

        return quadroResponse;
    }
    
    private Quadro mapToModel(QuadroRequest request) {
        var quadro = new Quadro();

        quadro.setTipo(request.getTipo());
        quadro.setNome(request.getNome());

        return quadro;
    }
}

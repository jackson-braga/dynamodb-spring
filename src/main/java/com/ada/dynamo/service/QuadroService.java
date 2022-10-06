package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.mapper.QuadroMapper;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuadroService {
    private final QuadroRepository repository;
    private final QuadroMapper mapper;

    public Quadro save(QuadroRequest quadroRequest){
        Quadro quadro = mapper.toModel(quadroRequest);
        quadro.setId(UUID.randomUUID().toString());
        return repository.save(quadro);
    }

    public List<Quadro> getAll() {
        return repository.getAll();
    }

    public Quadro findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}

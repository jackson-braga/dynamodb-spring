package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.mapper.ColunaMapper;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.repository.ColunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColunaService {

    private final ColunaRepository repository;
    private final ColunaMapper mapper;


    public Coluna save(String quadroId, ColunaRequest colunaRequest){
        Coluna coluna = mapper.toModel(colunaRequest);
        coluna.setId(quadroId + "_" + UUID.randomUUID());
        return repository.save(coluna);
    }

    public Coluna update(String colunaId, ColunaRequest colunaRequest){
        Coluna coluna = mapper.toModel(colunaRequest);
        coluna.setId(colunaId);
        return repository.save(coluna);
    }

    public List<Coluna> getAll() {
        return repository.getAll();
    }

    public Coluna findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}

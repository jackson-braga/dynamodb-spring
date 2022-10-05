package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.mapper.ColunaMapper;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.repository.ColunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColunaService {

    private final ColunaRepository repository;
    private final ColunaMapper mapper;

    public Coluna adicionar(String quadroId, ColunaRequest colunaRequest){
        Coluna coluna = mapper.toModel(colunaRequest);
        coluna.setId(quadroId + "#" + UUID.randomUUID());
        return repository.save(coluna);
    }

}

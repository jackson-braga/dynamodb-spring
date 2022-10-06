/*
package com.ada.dynamo.service;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.mapper.ColunaMapper;
import com.ada.dynamo.mapper.ToModelMapper;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.repository.AbstractRepository;
import com.ada.dynamo.repository.ColunaRepository;
import com.ada.dynamo.repository.CrudRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class CrudService<TEntity, TRepository, TMapper, TRequest> {
    private final CrudRepository repository;
    private final ToModelMapper<TEntity, TRequest> mapper;


    public TEntity save(String quadroId, TRequest colunaRequest) {
        TEntity coluna = mapper.toModel(colunaRequest);

        return repository.save(coluna);
    }

    public List<TEntity> getAll() {
        return repository.getAll();
    }

    public TEntity findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    protected TEntity abstract setId(TEntity coluna);
    coluna.setId(quadroId + "#" + UUID.randomUUID());
}
*/

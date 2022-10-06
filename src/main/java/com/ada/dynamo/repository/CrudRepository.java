package com.ada.dynamo.repository;

import java.util.List;

public interface CrudRepository<TEntity, TKey> {
    TEntity save(TEntity TEntity);
    TEntity findById(TKey id);
    List<TEntity> getAll();
    void delete(TKey id);
}

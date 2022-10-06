package com.ada.dynamo.mapper;

public interface ToModelMapper<TEntity, TRequest> {
    TEntity toModel(TRequest request);
}

package com.ada.dynamo.service;

import java.util.List;
import java.util.UUID;

public interface ServiceContract<TRequest, TResponse> {
    TResponse findById(String id);
    List<TResponse> findAll();
    TResponse create(TRequest request);
    void delete(String id);

    TResponse update(TRequest request, String id);
}

package com.ada.dynamo.domain.repository;

import com.ada.dynamo.domain.model.Coluna;

import java.util.List;
import java.util.Optional;

public interface ColunaRepository {

    Coluna save(Coluna coluna);

    Optional<Coluna> getByHashKeyAndRangeKey(String hashKey);

    List<Coluna> findAll();

    Coluna update(Coluna coluna);

    void delete(String hashKey);
}

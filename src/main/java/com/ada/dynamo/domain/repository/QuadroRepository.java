package com.ada.dynamo.domain.repository;


import com.ada.dynamo.domain.model.Quadro;

import java.util.List;
import java.util.Optional;

public interface QuadroRepository {

    Quadro save(Quadro quadro);

    Optional<Quadro> getByHashKeyAndRangeKey(String hashKey);

    List<Quadro> findAll();

    Quadro update(Quadro quadro);

    void delete(String hashKey);

}

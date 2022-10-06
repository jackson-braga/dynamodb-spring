package com.ada.dynamo.infrastructure.repository.dynamo;

import com.ada.dynamo.domain.model.Coluna;
import com.ada.dynamo.domain.repository.ColunaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbColunaRepository extends DynamoDbCrud<Coluna> implements ColunaRepository {

    public DynamoDbColunaRepository() {
        super(Coluna.class, "COLUNA", "tipo");
    }

}

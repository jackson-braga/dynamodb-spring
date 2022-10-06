package com.ada.dynamo.infrastructure.repository.dynamo;
import com.ada.dynamo.domain.model.Quadro;
import com.ada.dynamo.domain.repository.QuadroRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbQuadroRepository extends DynamoDbCrud<Quadro> implements QuadroRepository {

    public DynamoDbQuadroRepository() {
        super(Quadro.class, "QUADRO", "tipo");
    }

}

package com.ada.dynamo.repository;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class QuadroRepository extends AbstractRepository<Quadro,String> {

    public QuadroRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected DynamoDBScanExpression getDynamoDBScanExpression() {
        //TODO: implementar método
        return null;
    }

    @Override
    protected Class<Quadro> getClassType() {
        return Quadro.class;
    }
}

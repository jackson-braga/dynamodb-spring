package com.ada.dynamo.repository;

import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository

public class TarefaRepository extends AbstractRepository<Tarefa,String> {

    public TarefaRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected DynamoDBScanExpression getDynamoDBScanExpression() {
        //TODO: implementar método
        return null;
    }

    @Override
    protected Class<Tarefa> getClassType() {
        return Tarefa.class;
    }
}

package com.ada.dynamo.repository;

import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TarefaRepository {

    private final DynamoDBMapper mapper;

    public Tarefa save(Tarefa tarefa) {
        tarefa.setId(UUID.randomUUID());
        mapper.save(tarefa);
        return tarefa;
    }

    @Override
    protected String onGenerateKey(String... params) {
        return UUID.randomUUID().toString();
    }
}

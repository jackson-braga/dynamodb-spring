package com.ada.dynamo.repository;

import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class TarefaRepository extends RepositoryBase<Tarefa> {

    public TarefaRepository(AmazonDynamoDB mapper) {
        super(mapper, Tarefa.class);
    }

    @Override
    protected String onGenerateKey(String... params) {
        return UUID.randomUUID().toString();
    }
}

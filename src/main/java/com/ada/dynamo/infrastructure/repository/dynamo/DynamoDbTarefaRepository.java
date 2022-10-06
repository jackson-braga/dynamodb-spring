package com.ada.dynamo.infrastructure.repository.dynamo;

import com.ada.dynamo.domain.model.Tarefa;
import com.ada.dynamo.domain.repository.TarefaRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DynamoDbTarefaRepository implements TarefaRepository {

    @Autowired
    DynamoDBMapper dynamoDBMapper;

    @Override
    public Tarefa save(Tarefa tarefa) {
        dynamoDBMapper.save(tarefa);
        return tarefa;
    }

    @Override
    public Optional<Tarefa> getByHashKeyAndRangeKey(String hashKey, String rangeKey) {
        return Optional.ofNullable(dynamoDBMapper.load(Tarefa.class, hashKey, rangeKey));
    }

    @Override
    public List<Tarefa> findAll() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Tarefa.class, scanExpression);
    }

    @Override
    public Tarefa update(Tarefa tarefa) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributes = new HashMap<>();
        expectedAttributes.put("hashKey", new ExpectedAttributeValue(true));
        expectedAttributes.put("rangeKey", new ExpectedAttributeValue(true));

        saveExpression.setExpected(expectedAttributes);
        saveExpression.setConditionalOperator(ConditionalOperator.AND);

        try {
            dynamoDBMapper.save(tarefa, saveExpression);
        } catch (ConditionalCheckFailedException e) {
            //Handle conditional check
        }

        return tarefa;
    }

    @Override
    public void delete(String hashKey, String rangeKey) {
        Optional<Tarefa> item = getByHashKeyAndRangeKey(hashKey, rangeKey);

        if(item.isEmpty()) {
            throw new RuntimeException("Item nao existente");
        }

        dynamoDBMapper.delete(item.get(), new DynamoDBDeleteExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue(
                        new AttributeValue().withS(hashKey)))
                .withExpectedEntry(":id", new ExpectedAttributeValue(
                        new AttributeValue().withS(rangeKey)
                )));
    }
}

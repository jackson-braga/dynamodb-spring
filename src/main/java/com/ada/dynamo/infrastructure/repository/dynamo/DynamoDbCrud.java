package com.ada.dynamo.infrastructure.repository.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class DynamoDbCrud<T> {

    @Autowired
    DynamoDBMapper dynamoDBMapper;

    final Class<T> typeParameterClass;

    private final String rangeKey;

    private final String rangeKeyName;

    protected DynamoDbCrud(Class<T> typeParameterClass, String rangeKey, String rangeKeyName) {
        this.typeParameterClass = typeParameterClass;
        this.rangeKey = rangeKey;
        this.rangeKeyName = rangeKeyName;
    }

    public T save(T t) {
        dynamoDBMapper.save(t);
        return t;
    }

    public Optional<T> getByHashKeyAndRangeKey(String hashKey) {
        return Optional.ofNullable(dynamoDBMapper.load(typeParameterClass, hashKey, rangeKey));
    }

    public List<T> findAll() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(String.format(":%s", rangeKeyName), new AttributeValue().withS(rangeKey));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression(String.format("%s = :%s", rangeKeyName, rangeKeyName))
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.scan(typeParameterClass, scanExpression);
    }

    public T update(T t) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributes = new HashMap<>();
        expectedAttributes.put("hashKey", new ExpectedAttributeValue(false));
        expectedAttributes.put("rangeKey", new ExpectedAttributeValue(false));

        saveExpression.setExpected(expectedAttributes);
        saveExpression.setConditionalOperator(ConditionalOperator.AND);

        try {
            dynamoDBMapper.save(t, saveExpression);
        } catch (ConditionalCheckFailedException e) {
            //Handle conditional check
        }

        return t;
    }

    public void delete(String hashKey) {
        Optional<T> item = getByHashKeyAndRangeKey(hashKey);
        if(item.isEmpty()) {
            throw new RuntimeException("Item nao existente");
        }
        dynamoDBMapper.delete(item.get());
    }

}

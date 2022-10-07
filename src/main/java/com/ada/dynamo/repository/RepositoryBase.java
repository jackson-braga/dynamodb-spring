package com.ada.dynamo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<T> {

    protected final DynamoDBMapper mapper;
    protected abstract Class<T> getClassType();
    public T save(T entity) {
        mapper.save(entity);
        return entity;
    }

    public Iterable<T> findAll() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(getClassType(), scanExpression);
    }

    public Iterable<T> findBySortKey(String coluna, String rangeKey) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(String.format(":%s", coluna), new AttributeValue().withS(rangeKey));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression(String.format("%s = :%s", coluna, coluna))
                .withExpressionAttributeValues(eav);

        return mapper.scan(getClassType(), scanExpression);
    }

    public Optional<T> findById(String partitionKey, String rangeKey) {
        return Optional.ofNullable(mapper.load(getClassType(), partitionKey, rangeKey));
    }

    public Optional<T> findById(String partitionKey) {
        return Optional.ofNullable(mapper.load(getClassType(), partitionKey));
    }

    public List<T> findByPartialId(String partitionKey) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(partitionKey));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id")
                .withExpressionAttributeValues(eav);

        return mapper.scan(getClassType(), scanExpression);
    }

    public void delete(T entity) {
        mapper.delete(entity);
    }
}

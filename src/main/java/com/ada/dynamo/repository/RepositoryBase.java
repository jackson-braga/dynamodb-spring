package com.ada.dynamo.repository;

import com.ada.dynamo.model.DynamoDBEntity;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class RepositoryBase<TEntity extends DynamoDBEntity> {

    private final AmazonDynamoDB dynamoDBClient;
    private final Class<TEntity> entityType;

    public RepositoryBase(AmazonDynamoDB mapper, Class<TEntity> entityType) {
        this.dynamoDBClient = mapper;
        this.entityType = entityType;
    }

    @SneakyThrows
    public TEntity save(TEntity entity, Class<TEntity> entityType, String... keyParameters) {
        var request = new PutItemRequest();
        request.setTableName("tarefas");

        var hashMap = new HashMap<String, AttributeValue>();
        var fields = entityType.getDeclaredFields();

        for (var field : fields) {
            var a = entity.getClass();
            var b = a.getField(field.getName()).get(entity);
            hashMap.put(field.getName(), new AttributeValue(String.valueOf(b)));
        }



        dynamoDBClient.putItem(request);
        entity.setId(onGenerateKey(keyParameters));
        return entity;
    }

    public void deleteById(String id, String sortId) {
        // dynamoDBClient.delete(findById(id, sortId));
    }

    public TEntity findById(String id, String sortId) {
        // return dynamoDBClient.load(entityType, id, sortId);
        return null;
    }

    public TEntity findById(String id) {
//        return dynamoDBClient.load(entityType, id);
        return null;
    }

    public List<TEntity> findAll() {
        return null;
    }

    protected abstract String onGenerateKey(String... params);
}

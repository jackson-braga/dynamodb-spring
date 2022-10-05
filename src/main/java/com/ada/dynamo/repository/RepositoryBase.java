package com.ada.dynamo.repository;

import com.ada.dynamo.model.DynamoDBEntity;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class RepositoryBase<TEntity extends DynamoDBEntity> {

    private final AmazonDynamoDB dynamoDBClient;
    private final Class<TEntity> entityType;
    private final String tableName;

    public RepositoryBase(AmazonDynamoDB mapper, Class<TEntity> entityType, String tableName) {
        this.dynamoDBClient = mapper;
        this.entityType = entityType;
        this.tableName = tableName;
    }

    @SneakyThrows
    public TEntity save(TEntity entity, Class<TEntity> entityType, String... keyParameters) {

        var fields = entityType.getDeclaredFields();
        var hashMap = new HashMap<String, AttributeValue>();

        setItemValues(entity, entityType, fields, hashMap);
        entity.setId(onGenerateKey(keyParameters));

        dynamoDBClient.putItem(tableName, hashMap);

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

    private void setItemValues(TEntity entity, Class<TEntity> entityType, Field[] fields, HashMap<String, AttributeValue> hashMap)
            throws NoSuchFieldException, IllegalAccessException {
        for (var declaredField : fields) {
            var field = entityType.getDeclaredField(declaredField.getName());
            field.setAccessible(true);
            var value = field.get(entity);
            hashMap.put(declaredField.getName(), new AttributeValue(String.valueOf(value)));
        }
    }
}

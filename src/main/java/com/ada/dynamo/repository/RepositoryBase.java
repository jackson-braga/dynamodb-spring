package com.ada.dynamo.repository;

import com.ada.dynamo.config.DynamoDBGenerateAtInsert;
import com.ada.dynamo.model.DynamoDBEntity;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

public abstract class RepositoryBase<TEntity extends DynamoDBEntity> {

    private final AmazonDynamoDB dynamoDBClient;
    private final Class<TEntity> entityType;
    private final String tableName;

    public RepositoryBase(AmazonDynamoDB dynamoDbClient, Class<TEntity> entityType, String tableName) {
        this.dynamoDBClient = dynamoDbClient;
        this.entityType = entityType;
        this.tableName = tableName;
    }

    @SneakyThrows
    public TEntity save(TEntity entity, String... keyParameters) {
        var hashMap = new HashMap<String, AttributeValue>();

        entity.setId(onGenerateKey(keyParameters));
        setItemValues(entity, entityType, hashMap);

        dynamoDBClient.putItem(tableName, hashMap);

        return entity;
    }

    public void deleteById(String id) {

        var idField = Arrays.stream(entityType.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(DynamoDBHashKey.class))
                .findFirst().orElseThrow(NoSuchElementException::new);

        var keyMap = Map.of(idField.getName(), new AttributeValue(id));

        var deleteItemRequest = new DeleteItemRequest()
                .withTableName(tableName)
                .withKey(keyMap);

        dynamoDBClient.deleteItem(deleteItemRequest);
    }

    public void deleteById(String id, String sortId) {

        var idField = Arrays.stream(entityType.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(DynamoDBHashKey.class))
                .findFirst().orElseThrow(NoSuchElementException::new);

        var sortIdField = Arrays.stream(entityType.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(DynamoDBRangeKey.class))
                .findFirst().orElseThrow(NoSuchElementException::new);


        var keyMap = Map.of(
                idField.getName(), new AttributeValue(id),
                sortIdField.getName(), new AttributeValue(sortId)
        );

        var deleteItemRequest = new DeleteItemRequest()
                .withTableName(tableName)
                .withKey(keyMap);

        dynamoDBClient.deleteItem(deleteItemRequest);
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

    private void setItemValues(TEntity entity, Class<TEntity> entityType, HashMap<String, AttributeValue> hashMap)
            throws NoSuchFieldException, IllegalAccessException {

        var fields = entityType.getDeclaredFields();

        for (var declaredField : fields) {
            if (declaredField.isAnnotationPresent(DynamoDBGenerateAtInsert.class)) {
                handleValueGeneratedAtInsert(entity, entityType, declaredField);
            }

            setAttributteValue(entity, entityType, hashMap, declaredField);
        }
    }

    private void handleValueGeneratedAtInsert(TEntity entity, Class<TEntity> entityType, Field declaredField)
            throws NoSuchFieldException, IllegalAccessException {
        var type = declaredField.getAnnotation(DynamoDBGenerateAtInsert.class);

        if (type.type().getTypeName().equals(LocalDateTime.class.getTypeName())) {
            var field = entityType.getDeclaredField(declaredField.getName());
            field.setAccessible(true);
            field.set(entity, LocalDateTime.now());
        } else {
            throw new IllegalArgumentException(String.format(
                    "Classe %s declarada na anotação %s nao é conhecida e não pôde ser mapeada",
                    type.type().getTypeName(),
                    DynamoDBGenerateAtInsert.class.getTypeName()
                )
            );
        }
    }

    private void setAttributteValue(TEntity entity, Class<TEntity> entityType, HashMap<String, AttributeValue> hashMap, Field declaredField) throws NoSuchFieldException, IllegalAccessException {
        var field = entityType.getDeclaredField(declaredField.getName());
        field.setAccessible(true);
        var value = field.get(entity);
        hashMap.put(declaredField.getName(), new AttributeValue(String.valueOf(value)));
    }
}

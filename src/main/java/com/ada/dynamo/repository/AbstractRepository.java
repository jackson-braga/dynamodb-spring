package com.ada.dynamo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractRepository<TEntity, TKey> {

    private final DynamoDBMapper mapper;

    public TEntity save(TEntity TEntity) {
        mapper.save(TEntity);
        return TEntity;
    }

    public TEntity findById(TKey id) {
        return mapper.load(getClassType(), id);
    }

    public List<TEntity> getAll() {
        return mapper.scan(getClassType(), getDynamoDBScanExpression())
                .stream()
                .collect(Collectors.toList());
    }

    public void delete(TKey id) {
        TEntity TEntity = mapper.load(getClassType(), id);
        mapper.delete(TEntity);
    }

    protected abstract DynamoDBScanExpression getDynamoDBScanExpression();

    protected abstract Class<TEntity> getClassType();
    /*protected abstract TEntity doEntity(TKey id);*/
}

package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.model.Tarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TarefaRepository extends RepositoryBase<Tarefa>{
    public TarefaRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected Class<Tarefa> getClassType() {
        return Tarefa.class;
    }

    public List<CartaoTarefa> findCartaoTarefaByPartialId(String partitionKey) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(partitionKey));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id")
                .withExpressionAttributeValues(eav);

        return mapper.scan(CartaoTarefa.class, scanExpression);
    }
}

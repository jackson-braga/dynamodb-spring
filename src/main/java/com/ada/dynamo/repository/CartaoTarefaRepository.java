package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CartaoTarefaRepository extends AbstractRepository<CartaoTarefa, String>{

    public CartaoTarefaRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected DynamoDBScanExpression getDynamoDBScanExpression() {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":tipo", new AttributeValue().withS("CARTAO_TAREFA"));

        return new DynamoDBScanExpression()
                .withFilterExpression("tipo = :tipo")
                .withExpressionAttributeValues(eav);
    }

    public List<CartaoTarefa> findIdContains(String idTarefa) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(idTarefa));

        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id)")
                .withExpressionAttributeValues(eav);

        return new ArrayList<>(mapper.scan(CartaoTarefa.class, dynamoDBScanExpression));
    }

    @Override
    protected Class<CartaoTarefa> getClassType() {
        return CartaoTarefa.class;
    }


}

package com.ada.dynamo.repository;

import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.provider.TipoProvider;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartaoTarefaRepository extends RepositoryBase<CartaoTarefa> {

    public CartaoTarefaRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected Class<CartaoTarefa> getClassType() {
        return CartaoTarefa.class;
    }

    public List<CartaoTarefa> findByQuadro(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));
        eav.put(":tipo", new AttributeValue().withS(TipoProvider.CARTAO_TAREFA.stringValue()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id and tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(CartaoTarefa.class, scanExpression);
    }

    public Optional<CartaoTarefa> findByTarefa(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id")
                .withExpressionAttributeValues(eav);

        return mapper.scan(CartaoTarefa.class, scanExpression)
                .stream()
                .findFirst();
    }
}

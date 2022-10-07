package com.ada.dynamo.repository;

import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.provider.TipoProvider;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ColunaRepository extends RepositoryBase<Coluna> {

    public ColunaRepository(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    protected Class<Coluna> getClassType() {
        return Coluna.class;
    }

    public List<Coluna> findByQuadro(String id) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));
        eav.put(":tipo", new AttributeValue().withS(TipoProvider.COLUNA.stringValue()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(id, :id) and id <> :id and tipo = :tipo")
                .withExpressionAttributeValues(eav);

        return mapper.scan(Coluna.class, scanExpression);
    }
}

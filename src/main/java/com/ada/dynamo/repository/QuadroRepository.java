package com.ada.dynamo.repository;

import com.ada.dynamo.model.Quadro;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class QuadroRepository {

    private final DynamoDBMapper mapper;

    public Quadro save(Quadro quadro) {
        quadro.setId(UUID.randomUUID().toString());
        mapper.save(quadro);
        return quadro;
    }

    public Quadro findById(UUID id) {
        return mapper.load(Quadro.class, id);
    }

    public Iterable<Quadro> findAll(){
        return new ArrayList<>(mapper.scan(Quadro.class, new DynamoDBScanExpression()));
    }

    public void deleteById(UUID id){
        mapper.delete(findById(id));
    }
}

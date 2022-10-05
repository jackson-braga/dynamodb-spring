package com.ada.dynamo.repository;

import com.ada.dynamo.model.Coluna;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ColunaRepository extends RepositoryBase<Coluna> {

    public ColunaRepository(AmazonDynamoDB mapper) {
        super(mapper, Coluna.class);
    }

    @Override
    protected String onGenerateKey(String... params) {
        var str = Arrays.stream(params)
                .map(s -> String.format("%s#", s))
                .reduce(String::concat)
                .orElse("");

        return str.substring(0, Math.max(str.length() - 1, 0));
    }
}

package com.ada.dynamo.repository;

import com.ada.dynamo.model.Quadro;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.stereotype.Repository;

@Repository
public class QuadroRepository extends RepositoryBase<Quadro> {

    public QuadroRepository(AmazonDynamoDB mapper) {
        super(mapper, Quadro.class, "quadros");
    }

    @Override
    protected String onGenerateKey(String... params) {
        return "pprt";
    }
}

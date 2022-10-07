package com.ada.dynamo.config;

import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.model.Tarefa;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class DynamoDBConfig {
    @Value("${aws.access.key.id:fakeid}")
    private String awsAccessKeyId;
    @Value("${aws.access.key.secret:fakeSecret}")
    private String awsAcessKeySecret;
    @Value("${dynamodb.service.endpoint:http://localhost:8000/}")
    private String dynamoDBServiceEndPoint;
    @Value("${dynamodb.service.region:sa-east-1}")
    private String dynamoDBRegion;

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endPointConfiguration())
                .withCredentials(credentialsProvider())
                .build();
    }

    private AWSCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKeyId, awsAcessKeySecret)
        );
    }

    private AwsClientBuilder.EndpointConfiguration endPointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(dynamoDBServiceEndPoint, dynamoDBRegion);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setupDB(ApplicationReadyEvent event) {
        AmazonDynamoDB amazonDynamoDB = event.getApplicationContext().getBean(AmazonDynamoDB.class);
        DynamoDBMapper dynamoDBMapper = event.getApplicationContext().getBean(DynamoDBMapper.class);

        CreateTableRequest createTableTarefaRequest = dynamoDBMapper.generateCreateTableRequest(Tarefa.class);
        /*DeleteTableRequest deleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(Tarefa.class);
        amazonDynamoDB.deleteTable(deleteTableRequest);*/

        if (!amazonDynamoDB.listTables().getTableNames().contains(createTableTarefaRequest.getTableName())) {
            createTableTarefaRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            amazonDynamoDB.createTable(createTableTarefaRequest);
        }

        CreateTableRequest createTableQuadroRequest = dynamoDBMapper.generateCreateTableRequest(Quadro.class);

        if (!amazonDynamoDB.listTables().getTableNames().contains(createTableQuadroRequest.getTableName())) {
            createTableQuadroRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            amazonDynamoDB.createTable(createTableQuadroRequest);
        }
    }
}

package com.ada.dynamo.infrastructure.repository.dynamo;

import com.ada.dynamo.domain.model.CartaoTarefa;
import com.ada.dynamo.domain.repository.CartaoTarefaRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class DynamoDbCartaoTarefaRepository extends DynamoDbCrud<CartaoTarefa> implements CartaoTarefaRepository {
    public DynamoDbCartaoTarefaRepository() {
        super(CartaoTarefa.class, "CARTAO_TAREFA", "tipo");
    }

    @Override
    public CartaoTarefa moveCartaoTarefa(CartaoTarefa cartaoTarefa, String quadroDestino) {

        String hashKey = cartaoTarefa.getId();
        String hashKeyUpdated = quadroDestino + "#" + cartaoTarefa.getId().split("#")[1];
        cartaoTarefa.setId(hashKeyUpdated);
        save(cartaoTarefa);
        delete(hashKey);
        return cartaoTarefa;
    }

    private void executeTransactionWrite(TransactionWriteRequest transactionWriteRequest) {
        try {
            dynamoDBMapper.transactionWrite(transactionWriteRequest);
        } catch (DynamoDBMappingException ddbme) {
            System.err.println("Client side error in Mapper, fix before retrying. Error: " + ddbme.getMessage());
        } catch (ResourceNotFoundException rnfe) {
            System.err.println("One of the tables was not found, verify table exists before retrying. Error: " + rnfe.getMessage());
        } catch (InternalServerErrorException ise) {
            System.err.println("Internal Server Error, generally safe to retry with back-off. Error: " + ise.getMessage());
        } catch (TransactionCanceledException tce) {
            System.err.println("Transaction Canceled, implies a client issue, fix before retrying. Error: " + tce.getMessage());
        } catch (Exception ex) {
            System.err.println("An exception occurred, investigate and configure retry strategy. Error: " + ex.getMessage());
        }
    }
}

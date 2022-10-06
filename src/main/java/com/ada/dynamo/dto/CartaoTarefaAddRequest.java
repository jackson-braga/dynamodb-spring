package com.ada.dynamo.dto;

import com.ada.dynamo.model.Prioridade;
import com.ada.dynamo.util.autogenerate.LocalDateTimeAutoGenerate;
import com.ada.dynamo.util.converter.LocalDateTimeToStringConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class CartaoTarefaAddRequest {

    private String quadroId;
    private String colunaId;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime previsao;
    private LocalDateTime conclusao;
}

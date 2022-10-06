package com.ada.dynamo.dto.request;

import com.ada.dynamo.enums.Prioridade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartaoTarefaRequest {
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime criacao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime previsao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime conclusao;

}

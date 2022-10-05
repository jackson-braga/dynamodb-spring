package com.ada.dynamo.dto.request;

import com.ada.dynamo.enums.Prioridade;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartaoTarefaRequest {
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime criacao;
    private LocalDateTime previsao;
    private LocalDateTime conclusao;

}

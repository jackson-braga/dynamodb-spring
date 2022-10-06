package com.ada.dynamo.application.response;

import com.ada.dynamo.domain.model.Prioridade;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartaoTarefaResponse {
    private String id;
    private String tipo;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime criacao;
    private LocalDateTime previsao;
    private LocalDateTime conclusao;
}

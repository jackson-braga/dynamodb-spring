package com.ada.dynamo.application.request;

import com.ada.dynamo.domain.model.Prioridade;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCartaoTarefaRequest {
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime previsao;
}

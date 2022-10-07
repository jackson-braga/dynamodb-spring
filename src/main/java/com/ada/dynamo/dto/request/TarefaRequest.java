package com.ada.dynamo.dto.request;

import com.ada.dynamo.model.Prioridade;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaRequest {
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime previsao;
    private LocalDateTime conclusao;
}

package com.ada.dynamo.dto.request;

import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaRequest {
    private String id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime criacao;
    private LocalDateTime previsao;
    private LocalDateTime conclusao;
}

package com.ada.dynamo.dto.response;

import com.ada.dynamo.model.Prioridade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CartaoTarefaResponse {
    private String id;
    private String tipo;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime previsao;
    private LocalDateTime criacao;
    private LocalDateTime conclusao;
}

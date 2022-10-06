package com.ada.dynamo.dto.response;

import com.ada.dynamo.config.DynamoDBGenerateAtInsert;
import com.ada.dynamo.model.Prioridade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CartaoTarefaResponse {
    private UUID quadroId;
    private String tipo;
    private UUID colunaId;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private LocalDateTime previsao;
    private LocalDateTime criacao;
    private LocalDateTime conclusao;
}

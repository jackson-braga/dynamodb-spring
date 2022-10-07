package com.ada.dynamo.application.request;

import com.ada.dynamo.domain.model.Prioridade;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateCartaoTarefaRequest {

    @NotBlank
    private String descricao;

    @NotNull
    private Prioridade prioridade;

    @NotNull
    private LocalDateTime previsao;
}

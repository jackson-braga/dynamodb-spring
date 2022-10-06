package com.ada.dynamo.dto.request;

import com.ada.dynamo.model.Prioridade;
import lombok.Getter;
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
public class CartaoTarefaRequest {
    @NotBlank
    private UUID quadroId;
    @NotBlank
    private UUID colunaId;

    @NotEmpty
    @Length(min = 3, max = 255)
    private String titulo;
    @NotEmpty
    @Length(min = 3, max = 1000)
    private String descricao;
    @NotNull
    private Prioridade prioridade;
    @Future
    private LocalDateTime previsao;
}

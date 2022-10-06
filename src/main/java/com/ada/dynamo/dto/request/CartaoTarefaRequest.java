package com.ada.dynamo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CartaoTarefaRequest {
    @NotBlank
    private String colunaId;

    @NotBlank
    private String tarefaId;
}

package com.ada.dynamo.application.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCartaoTarefaRequest {

    @NotBlank
    private String tarefaId;

    @NotBlank
    private String colunaId;

    @NotBlank
    private String titulo;
}

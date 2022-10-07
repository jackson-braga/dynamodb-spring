package com.ada.dynamo.application.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateColunaRequest {

    @NotBlank
    private String quadroId;

    @NotBlank
    private String nome;

    @NotBlank
    private String cor;

    @NotNull
    private Integer ordem;

    private Integer limite;
}

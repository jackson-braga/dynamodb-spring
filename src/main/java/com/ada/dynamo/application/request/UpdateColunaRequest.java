package com.ada.dynamo.application.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateColunaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String cor;

    @NotNull
    private Integer ordem;

    @NotNull
    private Integer limite;
}

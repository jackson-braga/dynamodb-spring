package com.ada.dynamo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class QuadroRequest {
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    @NotNull(message = "Nome vazio")
    private String nome;
}

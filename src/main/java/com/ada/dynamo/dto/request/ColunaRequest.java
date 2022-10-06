package com.ada.dynamo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ColunaRequest {
    private String tipo;
    private String nome;
    private String cor; //#FFFFFF
    private Integer ordem;
    private Integer limite;
    @NotBlank
    private String quadroId;
}

package com.ada.dynamo.application.request;

import lombok.Data;

@Data
public class CreateColunaRequest {
    private String quadroId;
    private String nome;
    private String cor;
    private Integer ordem;
    private Integer limite;
}

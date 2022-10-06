package com.ada.dynamo.application.response;

import lombok.Data;

@Data
public class ColunaResponse {
    private String id;
    private String nome;
    private String cor;
    private Integer ordem;
    private Integer limite;
}

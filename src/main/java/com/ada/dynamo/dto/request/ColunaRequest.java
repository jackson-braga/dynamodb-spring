package com.ada.dynamo.dto.request;

import lombok.Data;

@Data
public class ColunaRequest {
    private String nome;
    private String cor; //#FFFFFF
    private Integer ordem;
    private Integer limite;
}

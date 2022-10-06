package com.ada.dynamo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class ColunaRequest {
    private String tipo;
    private String nome;
    private String cor; //#FFFFFF
    private Integer ordem;
    private Integer limite;
    private UUID quadroId;
}

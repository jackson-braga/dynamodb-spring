package com.ada.dynamo.application.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QuadroRequest {
    @NotBlank
    private String nome;
}

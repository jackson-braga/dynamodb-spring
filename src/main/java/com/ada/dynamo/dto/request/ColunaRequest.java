package com.ada.dynamo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ColunaRequest {
    private String nome;
    @Pattern(regexp = "^#[0-9A-F]{6}$", message = "A cor deve ser um hexadecimal com tamamho 6 e começar com #")
    private String cor; //#FFFFFF
    private Integer ordem;
    private Integer limite;
    @NotBlank
    private String quadroId;
}

package com.ada.dynamo.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColunaResponse {
    private String id;
    private String tipo;
    private String nome;
    private String cor; //#FFFFFF
    private Integer ordem;
    private Integer limite;

}



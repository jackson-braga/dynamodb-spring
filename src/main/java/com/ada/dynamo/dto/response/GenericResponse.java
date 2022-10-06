package com.ada.dynamo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GenericResponse {
    private String message;
    private long code;
}

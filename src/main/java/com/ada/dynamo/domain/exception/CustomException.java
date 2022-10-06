package com.ada.dynamo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomException {
    private List<String> message;
    private Integer statusCode;
}

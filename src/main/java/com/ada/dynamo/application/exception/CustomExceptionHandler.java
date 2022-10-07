package com.ada.dynamo.application.exception;

import com.ada.dynamo.domain.exception.ItemNaoExistenteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ItemNaoExistenteException.class})
    protected ResponseEntity<?> handleItemNotFound(RuntimeException ex, WebRequest request) {
        CustomException exceptionDetail = new CustomException(List.of(String.format("Elemento %s não encontrado", ex.getMessage())),
                HttpStatus.NOT_FOUND.value());

        return handleExceptionInternal(ex, exceptionDetail,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}

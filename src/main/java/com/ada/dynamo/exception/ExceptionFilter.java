package com.ada.dynamo.exception;

import com.ada.dynamo.dto.response.GenericResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionFilter {

    @Value("#{environment.getProperty('debug') != null && environment.getProperty('debug') != 'false'}")
    public boolean isDebug;

    @ExceptionHandler({
        ItemNaoEncontradoException.class
    })
    public ResponseEntity<GenericResponse> handlerItemNaoEncontradoException(ItemNaoEncontradoException ex) {
        var response = new GenericResponse(ex.getMessage(), ZonedDateTime.now().toEpochSecond());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({
        NullPointerException.class
    })
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {

        var genericResponse = new GenericResponse(ex.getMessage(), ZonedDateTime.now().toEpochSecond());
        return ResponseEntity.internalServerError().body(genericResponse);
    }
}

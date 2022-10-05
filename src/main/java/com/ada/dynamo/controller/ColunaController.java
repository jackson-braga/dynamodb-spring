package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.repository.ColunaRepository;
import com.ada.dynamo.service.ColunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/coluna")
@RequiredArgsConstructor
public class ColunaController {
    private final ColunaRepository repository;
    private final ColunaService service;

    @PostMapping("/{quadroId}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Coluna> addColuna(@PathVariable String quadroId, @RequestBody @Valid ColunaRequest colunaRequest) {
        return ResponseEntity.ok(service.adicionar(quadroId,colunaRequest));
    }
}

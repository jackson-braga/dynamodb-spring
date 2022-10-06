package com.ada.dynamo.controller;


import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.dto.response.CartaoTarefaResponse;
import com.ada.dynamo.service.CartaoTarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartao-tarefa")
@RequiredArgsConstructor
public class CartaoTarefaController {

    private final CartaoTarefaService service;

    @PostMapping()
    public ResponseEntity<CartaoTarefaResponse> createCartaoTarefa(@RequestBody @Valid CartaoTarefaRequest cartaoTarefaRequest) {
        return ResponseEntity.ok(service.create(cartaoTarefaRequest));
    }

    @GetMapping
    public ResponseEntity<Iterable<CartaoTarefaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = ("/{id}"))
    public ResponseEntity<CartaoTarefaResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

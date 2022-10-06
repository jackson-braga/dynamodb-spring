package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.model.CartaoTarefa;
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

    @PostMapping("{id}")
    public ResponseEntity<CartaoTarefa> addTarefa(@PathVariable(name ="id") String quadroColunaId, @RequestBody CartaoTarefaRequest cartaoTarefaRequest) {
        return ResponseEntity.ok(service.save(quadroColunaId, cartaoTarefaRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoTarefa> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<CartaoTarefa>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTarefa(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoTarefa> update(@PathVariable String id, @RequestBody @Valid CartaoTarefaRequest cartaoTarefaRequest) {
        return ResponseEntity.ok(service.update(id,cartaoTarefaRequest));
    }
}

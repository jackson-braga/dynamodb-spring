package com.ada.dynamo.controller;


import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.dto.response.CartaoTarefaResponse;
import com.ada.dynamo.service.CartaoTarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cartao-tarefa")
@RequiredArgsConstructor
public class CartaoTarefaController {

    private final CartaoTarefaService service;

    @PostMapping()
    public ResponseEntity<CartaoTarefaResponse> createCartaoTarefa(@RequestBody @Valid CartaoTarefaRequest cartaoTarefaRequest, UriComponentsBuilder uriComponentsBuilder) {
        CartaoTarefaResponse cartaoTarefaResponse = service.create(cartaoTarefaRequest);
        URI uri = uriComponentsBuilder.path("/api/cartao-tarefa").buildAndExpand(cartaoTarefaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(cartaoTarefaResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<CartaoTarefaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = ("/{id}"))
    public ResponseEntity<CartaoTarefaResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/quadro/{id}")
    public ResponseEntity<List<CartaoTarefaResponse>> findByQuadro(@PathVariable String id) {
        return ResponseEntity.ok(service.findByQuadro(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoTarefaResponse> update(@RequestBody CartaoTarefaRequest cartaoTarefaRequest, @PathVariable String id){
        return ResponseEntity.ok(service.update(cartaoTarefaRequest, id));
    }
}

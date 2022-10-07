package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.dto.response.TarefaResponse;
import com.ada.dynamo.service.CartaoTarefaService;
import com.ada.dynamo.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;
    private final CartaoTarefaService cartaoTarefaService;

    @PostMapping
    public ResponseEntity<TarefaResponse> create(@RequestBody @Valid TarefaRequest tarefaRequest, UriComponentsBuilder uriComponentsBuilder) {
        TarefaResponse tarefaResponse = service.create(tarefaRequest);
        URI uri = uriComponentsBuilder.path("/api/tarefa").buildAndExpand(tarefaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(tarefaResponse);
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> update(@RequestBody TarefaRequest tarefaRequest, @PathVariable String id) {
        var tarefaResponse = service.update(tarefaRequest, id);
        cartaoTarefaService.updateTarefa(tarefaResponse);
        return ResponseEntity.ok(tarefaResponse);
    }
}

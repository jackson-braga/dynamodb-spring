package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService service;

    @PostMapping()
    public ResponseEntity<Tarefa> addTarefa(@RequestBody @Valid CartaoTarefaRequest cartaoTarefaRequest) {
        return ResponseEntity.ok(service.save(cartaoTarefaRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Tarefa>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTarefa(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable String id, @RequestBody @Valid CartaoTarefaRequest cartaoTarefaRequest) {
        return ResponseEntity.ok(service.put(id,cartaoTarefaRequest));

    }

    //TODO ARRUMAR OS PUT FAZENDO VERIFICACAO ID

}

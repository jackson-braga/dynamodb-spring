package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.TarefaRequest;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import com.ada.dynamo.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;

    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(@RequestBody TarefaRequest tarefaRequest) {
        return ResponseEntity.ok(service.save(tarefaRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> show(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Tarefa>> index() {
        return ResponseEntity.ok(service.getAll());
    }
}

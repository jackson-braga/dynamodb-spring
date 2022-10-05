package com.ada.dynamo.controller;

import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaRepository repository;

    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(repository.save(tarefa, tarefa.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> show(@PathVariable UUID id) {
        return ResponseEntity.ok(repository.findById(id.toString()));
    }

    @GetMapping
    public ResponseEntity<Iterable<Tarefa>> index() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarefa(@PathVariable UUID id) {
        repository.deleteById(id.toString());
        return ResponseEntity.noContent().build();
    }
}

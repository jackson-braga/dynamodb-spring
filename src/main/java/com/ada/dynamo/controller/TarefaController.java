package com.ada.dynamo.controller;

import com.ada.dynamo.dto.response.TarefaResponse;
import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import com.ada.dynamo.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;
    private final TarefaRepository repository;

    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(repository.save(tarefa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> show(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Tarefa>> index() {
        return ResponseEntity.ok(repository.findAll());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteTarefa(@PathVariable UUID id) {
//        repository.deleteById(id.toString());
//        return ResponseEntity.noContent().build();
//    }
}

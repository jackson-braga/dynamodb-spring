package com.ada.dynamo.controller;

import com.ada.dynamo.model.Tarefa;
import com.ada.dynamo.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaRepository repository;

    //TODO: Parear alterações com as tarefas da tabela "quadros"
    //TODO: Adicionar função para alterar status da tarefa
    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(repository.save(tarefa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Tarefa>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping
    public ResponseEntity<Tarefa> updateTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(repository.update(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefa> delete(@PathVariable UUID id) {
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }

}

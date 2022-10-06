package com.ada.dynamo.controller;

import com.ada.dynamo.dto.CartaoTarefaAddRequest;
import com.ada.dynamo.dto.CartaoTarefaRequestById;
import com.ada.dynamo.model.CartaoTarefa;
import com.ada.dynamo.repository.CartaoTarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class CartaoTarefaController {
    private final CartaoTarefaRepository repository;

    //TODO: Completar CRUD das tarefas

     @PostMapping
    public ResponseEntity<CartaoTarefa> addTarefa(@RequestBody CartaoTarefaAddRequest tarefa) {
        return ResponseEntity.ok(repository.save(tarefa));
    }

    @GetMapping
    public ResponseEntity<CartaoTarefa> getById(@RequestBody CartaoTarefaRequestById dto){
        return ResponseEntity.ok(repository.findById(dto.getId()));
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<CartaoTarefa>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping
    public ResponseEntity<CartaoTarefa> update(@RequestBody CartaoTarefa tarefa) {
        return ResponseEntity.ok(repository.update(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartaoTarefa> delete(@RequestBody CartaoTarefaRequestById dto) {
        repository.delete(dto.getId());
        return ResponseEntity.noContent().build();
    }
}

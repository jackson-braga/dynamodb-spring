package com.ada.dynamo.controller;

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


    @PostMapping("/{quadroId}/{colunaId}")
    public ResponseEntity<CartaoTarefa> addTarefa(@PathVariable String quadroId,
                                                  @PathVariable String colunaId,
                                                  @RequestBody CartaoTarefa tarefa) {
        return ResponseEntity.ok(repository.save(quadroId, colunaId, tarefa));
    }

    @GetMapping("/{quadroId}/{colunaId}/{tarefaId}")
    public ResponseEntity<CartaoTarefa> getById(@PathVariable String quadroId,
                                                @PathVariable String colunaId,
                                                @PathVariable String tarefaId) {
        var id = quadroId + "#" + colunaId + "#" + tarefaId;
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<CartaoTarefa>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping
    public ResponseEntity<CartaoTarefa> update(@RequestBody CartaoTarefa tarefa) {
        return ResponseEntity.ok(repository.update(tarefa));
    }

    @PatchMapping("/{quadroId}/{colunaId}")
    public ResponseEntity<CartaoTarefa> changeCollumn(@PathVariable String quadroId,
                                                      @PathVariable String colunaId,
                                                      @RequestBody CartaoTarefa tarefa) {
        return ResponseEntity.ok(repository.changeCollumn(quadroId, colunaId, tarefa));
    }
    @DeleteMapping("/{quadroId}/{colunaId}/{tarefaId}")
    public ResponseEntity<CartaoTarefa> delete(@PathVariable String quadroId,
                                               @PathVariable String colunaId,
                                               @PathVariable String tarefaId) {

        var id = quadroId + "#" + colunaId + "#" + tarefaId;
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}

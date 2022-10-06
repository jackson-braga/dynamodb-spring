package com.ada.dynamo.controller;

import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.repository.ColunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/coluna")
@RequiredArgsConstructor
public class ColunaController {
    private final ColunaRepository repository;

    //TODO: Completar CRUD das colunas
    @PostMapping("/{quadroId}")
    public ResponseEntity<Coluna> addTarefa(@PathVariable String quadroId, @RequestBody Coluna coluna) {
        return ResponseEntity.ok(repository.save(quadroId, coluna));
    }

    @GetMapping("/{quadroId}/{colunaId}")
    public ResponseEntity<Coluna> getById(@PathVariable String quadroId,
                                          @PathVariable String colunaId) {
        var id = quadroId + "#"+colunaId;
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Coluna>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }


    @PutMapping
    public ResponseEntity<Coluna> updateColuna(@RequestBody Coluna coluna) {
        return ResponseEntity.ok(repository.update(coluna));
    }

    @DeleteMapping("/{quadroId}/{colunaId}")
    public ResponseEntity<Coluna> delete(@PathVariable String quadroId,
                                         @PathVariable String colunaId) {
        var id = quadroId + "#"+colunaId;
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}

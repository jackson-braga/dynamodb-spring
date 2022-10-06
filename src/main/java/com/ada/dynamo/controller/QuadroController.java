package com.ada.dynamo.controller;

import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quadro")
@RequiredArgsConstructor
public class QuadroController {
    private final QuadroRepository repository;

    @PostMapping
    public ResponseEntity<Quadro> addQuadro(@RequestBody Quadro quadro) {
        return ResponseEntity.ok(repository.save(quadro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quadro> getById(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Quadro>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping
    public ResponseEntity<Quadro> updateQuadro(@RequestBody Quadro tarefa) {
        return ResponseEntity.ok(repository.update(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quadro> delete(@PathVariable String id) {
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.ada.dynamo.controller;

import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequestMapping("/api/quadro")
@RequiredArgsConstructor
public class QuadroController {
    private final QuadroRepository repository;

    @PostMapping
    public ResponseEntity<Quadro> addQuadro(@RequestBody Quadro quadro){
        return ResponseEntity.ok(repository.save(quadro));
    }

    @GetMapping
    public ResponseEntity<Iterable<Quadro>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}

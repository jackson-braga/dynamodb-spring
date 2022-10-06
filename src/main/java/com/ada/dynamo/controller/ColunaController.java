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
}

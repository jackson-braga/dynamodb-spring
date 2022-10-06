package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.service.QuadroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quadro")
@RequiredArgsConstructor
public class QuadroController {
    private final QuadroService service;

    @PostMapping
    public ResponseEntity<Quadro> addQuadro(@RequestBody @Valid QuadroRequest quadroRequest){
        return ResponseEntity.ok(service.save(quadroRequest));
    }

    @GetMapping
    public ResponseEntity<List<Quadro>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quadro> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quadro> update(@PathVariable String id, @RequestBody @Valid QuadroRequest quadroRequest){
        return ResponseEntity.ok(service.update(id,quadroRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quadro> deleteQuadro(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

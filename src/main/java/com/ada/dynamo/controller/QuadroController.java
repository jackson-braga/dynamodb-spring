package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.dto.response.QuadroResponse;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.service.QuadroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quadro")
@RequiredArgsConstructor
public class QuadroController {

    private final QuadroService service;

    @PostMapping
    public ResponseEntity<QuadroResponse> addQuadro(@RequestBody QuadroRequest quadroRequest) {
        return ResponseEntity.ok(service.createQuadro(quadroRequest));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuadroResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuadroResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteQuadro(id);
        return ResponseEntity.noContent().build();
    }
}

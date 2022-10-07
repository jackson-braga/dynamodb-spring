package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.dto.response.QuadroResponse;
import com.ada.dynamo.service.QuadroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/quadro")
@RequiredArgsConstructor
public class QuadroController {

    private final QuadroService service;

    @PostMapping
    public ResponseEntity<QuadroResponse> create(@RequestBody QuadroRequest quadroRequest, UriComponentsBuilder uriComponentsBuilder) {
        QuadroResponse quadroResponse = service.create(quadroRequest);
        URI uri = uriComponentsBuilder.path("/api/quadro").buildAndExpand(quadroResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(quadroResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuadroResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuadroResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuadroResponse> update(@RequestBody QuadroRequest quadroRequest, @PathVariable String id) {
        return ResponseEntity.ok(service.update(quadroRequest, id));
    }
}

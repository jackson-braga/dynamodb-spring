package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.dto.response.CartaoTarefaResponse;
import com.ada.dynamo.dto.response.ColunaResponse;
import com.ada.dynamo.service.ColunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/coluna")
@RequiredArgsConstructor
public class ColunaController {

    private final ColunaService service;

    @PostMapping()
    public ResponseEntity<ColunaResponse> create(@RequestBody @Valid ColunaRequest colunaRequest, UriComponentsBuilder uriComponentsBuilder) {
        ColunaResponse colunaResponse = service.create(colunaRequest);
        URI uri = uriComponentsBuilder.path("/api/coluna").buildAndExpand(colunaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(colunaResponse);
    }
    @GetMapping
    public ResponseEntity<List<ColunaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = ("/{id}"))
    public ResponseEntity<ColunaResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/quadro/{id}")
    public ResponseEntity<List<ColunaResponse>> findByQuadro(@PathVariable String id) {
        return ResponseEntity.ok(service.findByQuadro(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ColunaResponse> update(@RequestBody @Valid ColunaRequest colunaRequest, @PathVariable String id) {
        return ResponseEntity.ok(service.update(colunaRequest, id));
    }
}

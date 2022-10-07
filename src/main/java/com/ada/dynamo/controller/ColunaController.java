package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.dto.response.ColunaResponse;
import com.ada.dynamo.service.ColunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/coluna")
@RequiredArgsConstructor
public class ColunaController {

    private final ColunaService service;

    @PostMapping()
    public ResponseEntity<ColunaResponse> createColuna(@RequestBody @Valid ColunaRequest colunaRequest, UriComponentsBuilder uriComponentsBuilder) {
        ColunaResponse colunaResponse = service.create(colunaRequest);
        URI uri = uriComponentsBuilder.path("/api/coluna").buildAndExpand(colunaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(colunaResponse);
    }
    @GetMapping
    public ResponseEntity<Iterable<ColunaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = ("/{id}"))
    public ResponseEntity<ColunaResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ColunaResponse> update(@RequestBody ColunaRequest colunaRequest, @PathVariable String id) {
        return ResponseEntity.ok(service.update(colunaRequest, id));
    }
}

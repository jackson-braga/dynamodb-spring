package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.dto.response.ColunaResponse;
import com.ada.dynamo.service.ColunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/coluna")
@RequiredArgsConstructor
public class ColunaController {

    private final ColunaService service;

    @PostMapping()
    public ResponseEntity<ColunaResponse> createColuna(@RequestBody @Valid ColunaRequest colunaRequest) {
        return ResponseEntity.ok(service.create(colunaRequest));
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
}

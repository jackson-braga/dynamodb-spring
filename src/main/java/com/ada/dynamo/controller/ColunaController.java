package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.model.Coluna;
import com.ada.dynamo.service.ColunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/coluna")
@RequiredArgsConstructor
public class
ColunaController {
    private final ColunaService service;

    @PostMapping("/{quadroId}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Coluna> addColuna(@PathVariable String quadroId, @RequestBody @Valid ColunaRequest colunaRequest) {
        return ResponseEntity.ok(service.save(quadroId,colunaRequest));
    }

    @GetMapping()
    public ResponseEntity<List<Coluna>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coluna> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteColuna(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coluna> update(@PathVariable String id, @RequestBody @Valid ColunaRequest colunaRequest) {
            return ResponseEntity.ok(service.update(id,colunaRequest));
    }
}

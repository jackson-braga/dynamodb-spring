package com.ada.dynamo.controller;

import com.ada.dynamo.dto.request.QuadroRequest;
import com.ada.dynamo.model.Quadro;
import com.ada.dynamo.repository.QuadroRepository;
import com.ada.dynamo.service.QuadroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/quadro")
@RequiredArgsConstructor
public class QuadroController {
    private final QuadroService service;

    @PostMapping
    public ResponseEntity<Quadro> addQuadro(@RequestBody @Valid QuadroRequest quadroRequest){
        return ResponseEntity.ok(service.save(quadroRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quadro> deleteQuadro(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.ada.dynamo.application.rest;

import com.ada.dynamo.application.mapper.QuadroMapper;
import com.ada.dynamo.application.request.QuadroRequest;
import com.ada.dynamo.application.response.QuadroResponse;
import com.ada.dynamo.domain.model.Quadro;
import com.ada.dynamo.domain.service.QuadroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quadro")
public class QuadroController {

    private final QuadroService quadroService;

    @GetMapping
    public ResponseEntity<List<QuadroResponse>> listar() {
        List<Quadro> quadros = quadroService.findAll();
        List<QuadroResponse> response = QuadroMapper.INSTANCE.quadroListToQuadroResponse(quadros);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{hashKey}")
    public ResponseEntity<QuadroResponse> getPorHashKey(@PathVariable String hashKey) {
        Quadro quadro = quadroService.getByHashKeyAndRangeKey(hashKey);
        QuadroResponse response = QuadroMapper.INSTANCE.quadroToQuadroResponse(quadro);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<QuadroResponse> cadastrar(@RequestBody @Valid QuadroRequest quadroRequest) {
        Quadro quadro = quadroService.save(quadroRequest);
        QuadroResponse response = QuadroMapper.INSTANCE.quadroToQuadroResponse(quadro);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{hashKey}")
    public ResponseEntity<QuadroResponse> atualizar(@PathVariable String hashKey, @RequestBody @Valid QuadroRequest quadroRequest) {
        Quadro quadro = quadroService.update(hashKey, quadroRequest);
        QuadroResponse response = QuadroMapper.INSTANCE.quadroToQuadroResponse(quadro);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hashKey}")
    public ResponseEntity<Void> apagar(@PathVariable String hashKey) {
        quadroService.delete(hashKey);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

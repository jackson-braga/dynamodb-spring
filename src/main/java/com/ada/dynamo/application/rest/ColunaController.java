package com.ada.dynamo.application.rest;

import com.ada.dynamo.application.mapper.ColunaMapper;
import com.ada.dynamo.application.request.CreateColunaRequest;
import com.ada.dynamo.application.request.UpdateColunaRequest;
import com.ada.dynamo.application.response.ColunaResponse;
import com.ada.dynamo.domain.model.Coluna;
import com.ada.dynamo.domain.service.ColunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/coluna")
@RequiredArgsConstructor
public class ColunaController {

    private final ColunaService colunaService;

    @GetMapping
    public ResponseEntity<List<ColunaResponse>> listar() {
        List<Coluna> colunaList = colunaService.findAll();
        List<ColunaResponse> response = ColunaMapper.INSTANCE.colunaListToColunaResponseList(colunaList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{hashKey}")
    public ResponseEntity<ColunaResponse> getPorHashKey(@PathVariable String hashKey) {
        Coluna coluna = colunaService.getByHashKeyAndRangeKey(hashKey);
        ColunaResponse response = ColunaMapper.INSTANCE.colunaToColunaResponse(coluna);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ColunaResponse> cadastrar(@RequestBody @Valid CreateColunaRequest createColunaRequest) {
        Coluna coluna = colunaService.save(createColunaRequest);
        System.out.println(coluna);
        ColunaResponse response = ColunaMapper.INSTANCE.colunaToColunaResponse(coluna);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{hashKey}")
    public ResponseEntity<ColunaResponse> atualizar(@PathVariable String hashKey,
                                                    @RequestBody @Valid UpdateColunaRequest updateColunaRequest) {
        Coluna coluna = colunaService.update(hashKey, updateColunaRequest);
        ColunaResponse response = ColunaMapper.INSTANCE.colunaToColunaResponse(coluna);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{hashKey}")
    public ResponseEntity<Void> apagar(@PathVariable String hashKey) {
        colunaService.delete(hashKey);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

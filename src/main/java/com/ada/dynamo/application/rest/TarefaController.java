package com.ada.dynamo.application.rest;
import com.ada.dynamo.application.mapper.TarefaMapper;
import com.ada.dynamo.application.request.TarefaRequest;
import com.ada.dynamo.application.response.TarefaResponse;
import com.ada.dynamo.domain.model.Tarefa;
import com.ada.dynamo.domain.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaResponse> cadastrar(@RequestBody @Valid TarefaRequest tarefaRequest) {
        Tarefa tarefa = tarefaService.save(tarefaRequest);
        TarefaResponse response = TarefaMapper.INSTANCE.tarefaToTarefaResponse(tarefa);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{hashKey}/{rangeKey}")
    public ResponseEntity<Tarefa> getPorHashKeyERangeKey(@PathVariable String hashKey, @PathVariable String rangeKey) {
        return ResponseEntity.ok(tarefaService.getByHashKeyAndRangeKey(hashKey, rangeKey));
    }

    @GetMapping
    public ResponseEntity<Iterable<Tarefa>> index() {
        return ResponseEntity.ok(tarefaService.findAll());
    }

    @DeleteMapping("/{hashKey}/{rangeKey}")
    public ResponseEntity<Void> apagar(@PathVariable String hashKey, @PathVariable String rangeKey) {
        tarefaService.delete(hashKey, rangeKey);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

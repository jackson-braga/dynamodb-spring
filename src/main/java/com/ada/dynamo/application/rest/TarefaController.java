package com.ada.dynamo.application.rest;
import com.ada.dynamo.application.mapper.TarefaMapper;
import com.ada.dynamo.application.request.TarefaRequest;
import com.ada.dynamo.application.response.TarefaResponse;
import com.ada.dynamo.domain.model.Tarefa;
import com.ada.dynamo.domain.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    @Lazy
    private final TarefaService tarefaService;

    @GetMapping("/{hashKey}/{rangeKey}")
    public ResponseEntity<TarefaResponse> getPorHashKeyERangeKey(@PathVariable String hashKey, @PathVariable String rangeKey) {
        Tarefa tarefa = tarefaService.getByHashKeyAndRangeKey(hashKey, rangeKey);
        TarefaResponse response = TarefaMapper.INSTANCE.tarefaToTarefaResponse(tarefa);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> index() {
        List<Tarefa> tarefaList = tarefaService.findAll();
        List<TarefaResponse> response = TarefaMapper.INSTANCE.tarefaListToTarefaResponseList(tarefaList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> cadastrar(@RequestBody @Valid TarefaRequest tarefaRequest) {
        Tarefa tarefa = tarefaService.save(tarefaRequest);
        TarefaResponse response = TarefaMapper.INSTANCE.tarefaToTarefaResponse(tarefa);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{hashKey}/{rangeKey}")
    public ResponseEntity<Void> concluirTarefa(@PathVariable String hashKey, @PathVariable String rangeKey) {
        tarefaService.concluirTarefa(hashKey, rangeKey);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{hashKey}")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable String hashKey,
                                                    @RequestBody @Valid TarefaRequest tarefaRequest) {
        Tarefa tarefa = tarefaService.update(hashKey, tarefaRequest);
        TarefaResponse response = TarefaMapper.INSTANCE.tarefaToTarefaResponse(tarefa);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{hashKey}/{rangeKey}")
    public ResponseEntity<Void> apagar(@PathVariable String hashKey, @PathVariable String rangeKey) {
        tarefaService.delete(hashKey, rangeKey);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

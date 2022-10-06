package com.ada.dynamo.application.rest;

import com.ada.dynamo.application.mapper.CartaoTarefaMapper;
import com.ada.dynamo.application.request.CreateCartaoTarefaRequest;
import com.ada.dynamo.application.request.UpdateCartaoTarefaRequest;
import com.ada.dynamo.application.response.CartaoTarefaResponse;
import com.ada.dynamo.domain.model.CartaoTarefa;
import com.ada.dynamo.domain.service.CartaoTarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cartao-tarefa")
@RequiredArgsConstructor
public class CartaoTarefaController {

    private final CartaoTarefaService cartaoTarefaService;

    @GetMapping
    public ResponseEntity<List<CartaoTarefaResponse>> listar() {
        List<CartaoTarefa> cartaoTarefaList = cartaoTarefaService.findAll();
        List<CartaoTarefaResponse> response = CartaoTarefaMapper.INSTANCE.cartaoTarefaListToCartaoTarefaResponseList(cartaoTarefaList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{hashKey}")
    public ResponseEntity<CartaoTarefaResponse> getPorHashKey(@PathVariable String hashKey) {
        CartaoTarefa cartaoTarefa = cartaoTarefaService.getByHashKeyAndRangeKey(hashKey);
        CartaoTarefaResponse response = CartaoTarefaMapper.INSTANCE.cartaoTarefaToCartaoTarefaResponse(cartaoTarefa);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<CartaoTarefaResponse> cadastrar(@RequestBody @Valid CreateCartaoTarefaRequest createCartaoTarefaRequest) {
        CartaoTarefa cartaoTarefa = cartaoTarefaService.save(createCartaoTarefaRequest);
        CartaoTarefaResponse response = CartaoTarefaMapper.INSTANCE.cartaoTarefaToCartaoTarefaResponse(cartaoTarefa);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{hashKey}")
    public ResponseEntity<CartaoTarefaResponse> atualizar(@PathVariable String hashKey,
                                                          @RequestBody @Valid UpdateCartaoTarefaRequest updateCartaoTarefaRequest) {
        CartaoTarefa cartaoTarefa = cartaoTarefaService.update(hashKey, updateCartaoTarefaRequest);
        CartaoTarefaResponse response = CartaoTarefaMapper.INSTANCE.cartaoTarefaToCartaoTarefaResponse(cartaoTarefa);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{hashKey}")
    public ResponseEntity<Void> apagar(@PathVariable String hashKey) {
        cartaoTarefaService.delete(hashKey);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/mover-cartao")
    public ResponseEntity<?> moverCartao(@RequestBody String cartaoTarefaId, @RequestBody String colunaId) {
        CartaoTarefa cartaoTarefa = cartaoTarefaService.moveCartaoTarefa(cartaoTarefaId, colunaId);
        return ResponseEntity.ok(cartaoTarefa);
    }
}

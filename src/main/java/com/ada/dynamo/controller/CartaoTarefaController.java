package com.ada.dynamo.controller;


import com.ada.dynamo.dto.request.CartaoTarefaRequest;
import com.ada.dynamo.dto.request.ColunaRequest;
import com.ada.dynamo.dto.response.ColunaResponse;
import com.ada.dynamo.repository.CartaoTarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cartaoTarefa")
@RequiredArgsConstructor
public class CartaoTarefaController {

    private final CartaoTarefaRepository repository;


}

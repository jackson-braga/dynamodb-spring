package com.ada.dynamo.controller;


import com.ada.dynamo.repository.CartaoTarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartapTarefa")
@RequiredArgsConstructor
public class CartaoTarefaController {

    private final CartaoTarefaRepository repository;

}

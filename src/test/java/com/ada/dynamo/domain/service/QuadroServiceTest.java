package com.ada.dynamo.domain.service;

import com.ada.dynamo.application.mapper.QuadroMapper;
import com.ada.dynamo.application.request.QuadroRequest;
import com.ada.dynamo.domain.model.Quadro;
import com.ada.dynamo.domain.repository.QuadroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class QuadroServiceTest {
    @InjectMocks
    private QuadroService quadroService;

    @Mock
    private QuadroRepository quadroRepository;
    private Quadro fakeQuadro;

    private QuadroRequest fakeQuadroRequest;

    private List<Quadro> fakeQuadroList = new ArrayList<>();

    QuadroMapper quadroMapper = QuadroMapper.INSTANCE;

    @BeforeEach
    void setup() {
        fakeQuadro = new Quadro();
        fakeQuadro.setId(UUID.randomUUID().toString());
        fakeQuadro.setTipo("QUADRO");
        fakeQuadro.setNome("Aprendizado Java");

        fakeQuadroRequest = new QuadroRequest();
        fakeQuadroRequest.setNome("Aprendizado Java");

        fakeQuadroList.add(fakeQuadro);
    }

    @Test
    void quadroCriadoComSucessoTeste() {
        Mockito.when(quadroRepository.save(Mockito.any())).thenReturn(fakeQuadro);
        Quadro quadro = quadroService.save(fakeQuadroRequest);
        Assertions.assertEquals(quadro, fakeQuadro);
    }

    @Test
    void resgatarQuadroComSucessoTeste() {
        Mockito.when(quadroRepository.getByHashKeyAndRangeKey(Mockito.any())).thenReturn(Optional.of(fakeQuadro));
        Quadro quadro = quadroService.getByHashKeyAndRangeKey(fakeQuadro.getId());
        Assertions.assertEquals(quadro, fakeQuadro);
    }

    @Test
    void resgatarTodosOsQuadrosComSucessoTeste() {
        Mockito.when(quadroRepository.findAll()).thenReturn(fakeQuadroList);
        List<Quadro> productList = quadroService.findAll();
        Assertions.assertEquals(productList.size(), fakeQuadroList.size());
    }

    @Test
    void atualizarQuadroComSucessoTeste() {
        String nomeAtualizado = "teste update";
        fakeQuadroRequest.setNome(nomeAtualizado);
        fakeQuadro.setNome(nomeAtualizado);
        Mockito.when(quadroRepository.update(Mockito.any())).thenReturn(fakeQuadro);
        Quadro quadro = quadroService.update(fakeQuadro.getId().toString(), fakeQuadroRequest);
        Assertions.assertEquals(quadro.getId(), fakeQuadro.getId());
        Assertions.assertEquals(quadro.getTipo(), "QUADRO");
        Assertions.assertEquals(quadro.getNome(), fakeQuadroRequest.getNome());
    }
}

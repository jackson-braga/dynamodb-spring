package com.ada.dynamo.domain.service;

import com.ada.dynamo.application.request.CreateColunaRequest;
import com.ada.dynamo.application.request.UpdateColunaRequest;
import com.ada.dynamo.domain.model.Coluna;
import com.ada.dynamo.domain.model.Quadro;
import com.ada.dynamo.domain.repository.ColunaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ColunaServiceTest {

    @InjectMocks
    private ColunaService colunaService;

    @Mock
    private ColunaRepository colunaRepository;

    private Coluna fakeColuna;
    private CreateColunaRequest fakeCreateColunaRequest;
    private UpdateColunaRequest fakeUpdateColunaRequest;
    private final List<Coluna> fakeColunaList = new ArrayList<>();

    private final String fakeQuadroId = UUID.randomUUID().toString();
    private final String fakeColunaId = UUID.randomUUID().toString();

    @BeforeEach
    private void setup() {
        fakeColuna = new Coluna();
        fakeColuna.setId(fakeQuadroId + "#" + fakeColunaId);
        fakeColuna.setTipo("COLUNA");
        fakeColuna.setNome("a fazer");
        fakeColuna.setOrdem(5);
        fakeColuna.setCor("azul");
        fakeColuna.setLimite(5);

        fakeColunaList.add(fakeColuna);

        fakeCreateColunaRequest = new CreateColunaRequest();
        fakeCreateColunaRequest.setQuadroId(fakeQuadroId);
        fakeCreateColunaRequest.setNome("a fazer");
        fakeCreateColunaRequest.setCor("azul");
        fakeCreateColunaRequest.setLimite(3);
        fakeCreateColunaRequest.setOrdem(5);

        fakeUpdateColunaRequest = new UpdateColunaRequest();
        fakeUpdateColunaRequest.setNome("alterando nome");
        fakeUpdateColunaRequest.setCor("preto");
        fakeUpdateColunaRequest.setOrdem(6);
        fakeUpdateColunaRequest.setLimite(5);
    }

    @Test
    void criarColunaComSucessoTeste() {
        Mockito.when(colunaRepository.save(Mockito.any())).thenReturn(fakeColuna);
        Coluna coluna = colunaService.save(fakeCreateColunaRequest);
        Assertions.assertEquals(coluna, fakeColuna);
    }

    @Test
    void resgatarColunaComSucessoTeste() {
        Mockito.when(colunaRepository.getByHashKeyAndRangeKey(Mockito.any())).thenReturn(Optional.of(fakeColuna));
        Coluna coluna = colunaService.getByHashKeyAndRangeKey(fakeColuna.getId());
        Assertions.assertEquals(coluna, fakeColuna);
    }

    @Test
    void resgatarTodasAsColunasComSucessoTeste() {
        Mockito.when(colunaService.findAll()).thenReturn(fakeColunaList);
        List<Coluna> productList = colunaService.findAll();
        Assertions.assertEquals(productList.size(), fakeColunaList.size());
    }

    @Test
    void AtualizarColunaComSucessoTeste() {
        String nomeAtualizado = "alterando nome";
        fakeColuna.setNome(nomeAtualizado);
        Mockito.when(colunaRepository.update(Mockito.any())).thenReturn(fakeColuna);
        Coluna coluna = colunaService.update(fakeColuna.getId(), fakeUpdateColunaRequest);
        Assertions.assertEquals(coluna.getId(), fakeColuna.getId());
        Assertions.assertEquals(coluna.getTipo(), "COLUNA");
        Assertions.assertEquals(coluna.getNome(), fakeUpdateColunaRequest.getNome());
    }

}

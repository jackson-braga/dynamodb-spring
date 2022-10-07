package com.ada.dynamo.domain.service;

import com.ada.dynamo.application.request.CreateCartaoTarefaRequest;
import com.ada.dynamo.application.request.TarefaRequest;
import com.ada.dynamo.application.request.UpdateCartaoTarefaRequest;
import com.ada.dynamo.domain.model.CartaoTarefa;
import com.ada.dynamo.domain.model.Coluna;
import com.ada.dynamo.domain.model.Prioridade;
import com.ada.dynamo.domain.model.Tarefa;
import com.ada.dynamo.domain.repository.CartaoTarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CartaoTarefaServiceTest {

    @InjectMocks
    private CartaoTarefaService cartaoTarefaService;

    @Mock
    private CartaoTarefaRepository cartaoTarefaRepository;

    @Mock
    private TarefaService tarefaService;
    private CartaoTarefa fakeCartaoTarefa;
    private CreateCartaoTarefaRequest fakeCreateCartaoTarefaRequest;
    private UpdateCartaoTarefaRequest fakeUpdateCartaoTarefaRequest;
    private final List<CartaoTarefa> fakeCartaoTarefaList = new ArrayList<>();

    private final String fakeQuadroId = UUID.randomUUID().toString();
    private final String fakeColunaId = UUID.randomUUID().toString();
    private final String fakeCartaoTarefaId = UUID.randomUUID().toString();

    private Tarefa fakeTarefa;

    @BeforeEach
    void setup() {
        LocalDateTime now = LocalDateTime.now();
        fakeCartaoTarefa = new CartaoTarefa();
        fakeCartaoTarefa.setId(fakeQuadroId + "#" + fakeColunaId + "#" + fakeCartaoTarefaId);
        fakeCartaoTarefa.setTitulo("criar crud");
        fakeCartaoTarefa.setDescricao("criando crud");
        fakeCartaoTarefa.setPrioridade(Prioridade.ALTA);
        fakeCartaoTarefa.setTipo("CARTAO_TAREFA");
        fakeCartaoTarefa.setCriacao(now);

        fakeCartaoTarefaList.add(fakeCartaoTarefa);

        fakeCreateCartaoTarefaRequest = new CreateCartaoTarefaRequest();
        fakeCreateCartaoTarefaRequest.setTarefaId(fakeCartaoTarefaId);
        fakeCreateCartaoTarefaRequest.setTitulo("criar crud");
        fakeCreateCartaoTarefaRequest.setColunaId(fakeColunaId);

        fakeUpdateCartaoTarefaRequest = new UpdateCartaoTarefaRequest();
        fakeUpdateCartaoTarefaRequest.setDescricao("alterando descricao");
        fakeUpdateCartaoTarefaRequest.setPrioridade(Prioridade.ALTA);
        fakeUpdateCartaoTarefaRequest.setPrevisao(now);

        fakeTarefa = new Tarefa();
        fakeTarefa.setId(fakeCartaoTarefaId);
        fakeTarefa.setTitulo("criar crud");
        fakeTarefa.setDescricao("criando crud");
        fakeTarefa.setPrioridade(Prioridade.ALTA);
        fakeTarefa.setCriacao(now);
    }

    @Test
    void criarCartaoTarefaComSucessoTeste() {
        Mockito.when(tarefaService.getByHashKeyAndRangeKey(Mockito.any(), Mockito.any())).thenReturn(fakeTarefa);
        Mockito.when(cartaoTarefaRepository.save(Mockito.any(CartaoTarefa.class))).thenReturn(fakeCartaoTarefa);
        CartaoTarefa cartaoTarefa = cartaoTarefaService.save(fakeCreateCartaoTarefaRequest);
        Assertions.assertEquals(cartaoTarefa, fakeCartaoTarefa);
    }

    @Test
    void resgatarCartaoTarefaComSucessoTeste() {
        Mockito.when(cartaoTarefaRepository.getByHashKeyAndRangeKey(Mockito.any())).thenReturn(Optional.of(fakeCartaoTarefa));
        CartaoTarefa cartaoTarefa = cartaoTarefaService.getByHashKeyAndRangeKey(fakeCartaoTarefa.getId());
        Assertions.assertEquals(cartaoTarefa, fakeCartaoTarefa);
    }

    @Test
    void resgatarTodosOsCartoesTarefasComSucessoTeste() {
        Mockito.when(cartaoTarefaService.findAll()).thenReturn(fakeCartaoTarefaList);
        List<CartaoTarefa> cartaoTarefaList = cartaoTarefaService.findAll();
        Assertions.assertEquals(cartaoTarefaList.size(), fakeCartaoTarefaList.size());
    }

    @Test
    void AtualizarCartaoTarefaComSucessoTeste() {
        String nomeAtualizado = "alterando descricao";
        fakeCartaoTarefa.setDescricao(nomeAtualizado);
        Mockito.when(cartaoTarefaRepository.update(Mockito.any())).thenReturn(fakeCartaoTarefa);
        fakeTarefa.setDescricao(nomeAtualizado);
        Mockito.when(tarefaService.update(Mockito.any(), Mockito.any(TarefaRequest.class))).thenReturn(fakeTarefa);
        CartaoTarefa cartaoTarefa = cartaoTarefaService.update(fakeCartaoTarefa.getId(), fakeUpdateCartaoTarefaRequest);
        Assertions.assertEquals(cartaoTarefa.getId(), fakeCartaoTarefa.getId());
        Assertions.assertEquals(cartaoTarefa.getTipo(), "CARTAO_TAREFA");
        Assertions.assertEquals(cartaoTarefa.getDescricao(), fakeUpdateCartaoTarefaRequest.getDescricao());
    }

}

package com.ada.dynamo.domain.service;

import com.ada.dynamo.application.mapper.ColunaMapper;
import com.ada.dynamo.application.request.CreateColunaRequest;
import com.ada.dynamo.application.request.UpdateColunaRequest;
import com.ada.dynamo.domain.exception.ItemNaoExistenteException;
import com.ada.dynamo.domain.model.Coluna;
import com.ada.dynamo.domain.repository.ColunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColunaService {

    private static final String TIPO = "COLUNA";

    private final ColunaRepository colunaRepository;

    public Coluna save(CreateColunaRequest createColunaRequest) {
        Coluna coluna = ColunaMapper.INSTANCE.createColunaRequestToColuna(createColunaRequest);
        coluna.setId(generateHashKey(createColunaRequest.getQuadroId()));
        coluna.setTipo(TIPO);

        return colunaRepository.save(coluna);
    }

    public Coluna getByHashKeyAndRangeKey(String hashKey) {
        return colunaRepository.getByHashKeyAndRangeKey(hashKey)
                .orElseThrow(() -> new ItemNaoExistenteException(Coluna.class));
    }

    public List<Coluna> findAll() {
        return colunaRepository.findAll();
    }

    public Coluna update(String hashKey, UpdateColunaRequest updateColunaRequest) {
        Coluna coluna = ColunaMapper.INSTANCE.updateColunaRequestToColuna(updateColunaRequest);
        coluna.setId(hashKey);
        coluna.setTipo(TIPO);

        return colunaRepository.update(coluna);
    }

    public void delete(String hashKey) {
        colunaRepository.delete(hashKey);
    }

    private String generateHashKey(String hashKeyQuadro) {
        return hashKeyQuadro.concat("#").concat(UUID.randomUUID().toString());
    }
}

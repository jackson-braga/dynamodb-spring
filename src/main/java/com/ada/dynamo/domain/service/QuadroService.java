package com.ada.dynamo.domain.service;

import com.ada.dynamo.application.mapper.QuadroMapper;
import com.ada.dynamo.application.request.QuadroRequest;
import com.ada.dynamo.domain.exception.ItemNaoExistenteException;
import com.ada.dynamo.domain.model.Quadro;
import com.ada.dynamo.domain.repository.QuadroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuadroService {

    private static final String TIPO = "QUADRO";

    private final QuadroRepository quadroRepository;

    public Quadro save(QuadroRequest quadroRequest) {
        Quadro quadro = QuadroMapper.INSTANCE.quadroRequestToQuadro(quadroRequest);
        quadro.setId(generateHashKey());
        quadro.setTipo(TIPO);
        return quadroRepository.save(quadro);
    }

    public Quadro getByHashKeyAndRangeKey(String hashKey) {
        return quadroRepository.getByHashKeyAndRangeKey(hashKey)
                .orElseThrow(ItemNaoExistenteException::new);
    }

    public List<Quadro> findAll() {
        return quadroRepository.findAll();
    }

    public Quadro update(String hashKey, QuadroRequest quadroRequest) {
        Quadro quadro = QuadroMapper.INSTANCE.quadroRequestToQuadro(quadroRequest);
        quadro.setId(hashKey);
        quadro.setId(TIPO);

        return quadroRepository.update(quadro);
    }

    public void delete(String hashKey) {
        quadroRepository.delete(hashKey);
    }

    private String generateHashKey() {
        return UUID.randomUUID().toString();
    }
}
